package br.com.seasyc.nomimimi.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import br.com.seasyc.nomimimi.R;
import br.com.seasyc.nomimimi.SignIn;
import br.com.seasyc.nomimimi.config.FirebaseSettings;
import br.com.seasyc.nomimimi.model.User;
import br.com.seasyc.nomimimi.util.Factory;

public class AccountFragment extends Fragment {

    private TextView email, name, date, posts;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);

        email = root.findViewById(R.id.user_email);
        name = root.findViewById(R.id.user_username);

        firebaseAuth = FirebaseSettings.getFirebaseAuth();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseSettings.getDatabaseReference().child("users").child(firebaseUser.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot u : dataSnapshot.getChildren()){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (!Factory.verifysUserIsLogged()){
            startActivity(new Intent(getContext(), SignIn.class));
        }else{
            email.setText(getString(R.string.profile_email) + " " +firebaseUser.getEmail());
        }

        return root;
    }

}