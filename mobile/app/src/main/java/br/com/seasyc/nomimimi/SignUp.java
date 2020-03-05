package br.com.seasyc.nomimimi;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.util.Date;

import br.com.seasyc.nomimimi.config.FirebaseSettings;
import br.com.seasyc.nomimimi.model.User;
import br.com.seasyc.nomimimi.util.Factory;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private TextInputLayout username, email, password;
    private Button signup;
    private User user;
    private Date now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        signup = findViewById(R.id.create);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUser();
                if (verifyUser())
                    createUser();
            }
        });
    }


    private void setUser() {
        user = new User();
        now = new Date();
        user.setName(username.getEditText().getText().toString());
        user.setEmail(email.getEditText().getText().toString());
        user.setPassword(password.getEditText().getText().toString());
        user.setCreated_at(now);
    }

    private boolean verifyUser() {
        if (user.getName().isEmpty()){
            username.setError(getString(R.string.erro_empty));
            if (user.getName().length() <= 10){
                username.setError(getString(R.string.erro_invalid));
            }
        }
        if (user.getEmail().isEmpty()){
            username.setError(getString(R.string.erro_empty));
            if (user.getEmail().length() <= 15){
                username.setError(getString(R.string.erro_invalid));
            }
        }
        if (user.getPassword().isEmpty()){
            username.setError(getString(R.string.erro_empty));
            if (user.getPassword().length() < 8){
                username.setError(getString(R.string.erro_invalid));
            }
        }
        return true;
    }

    private void createUser() {
        firebaseAuth = FirebaseSettings.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Factory.toastMessage(SignUp.this, getString(R.string.signup_create_success));
                            firebaseUser = task.getResult().getUser();
                            user.setId(firebaseUser.getUid());
                            user.save();
                            Factory.toastMessage(SignUp.this, firebaseUser.getUid());
                            startActivity(new Intent(SignUp.this, MainActivity.class));
                            finish();
                        }else{
                                try {
                                    throw  task.getException();
                                } catch (FirebaseAuthEmailException e) {
                                    Factory.toastMessage(SignUp.this,
                                            getString(R.string.signup_create_fail_email_exist));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Factory.toastMessage(SignUp.this,String.valueOf(task.getException()));
                                }

                        }
                    }
                });
    }




}
