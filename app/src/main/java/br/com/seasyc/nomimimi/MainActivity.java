package br.com.seasyc.nomimimi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import br.com.seasyc.nomimimi.config.FirebaseSettings;
import br.com.seasyc.nomimimi.util.Factory;

public class MainActivity extends AppCompatActivity {
    /*
    *
    * icon
    * Ícones feitos por <a href="https://www.flaticon.com/br/autores/darius-dan" title="Darius Dan">Darius Dan</a> from <a href="https://www.flaticon.com/br/" title="Flaticon"> www.flaticon.com</a>
    * */

    private FloatingActionButton newPost;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        firebaseAuth = FirebaseSettings.getFirebaseAuth();
        firebaseUser = firebaseAuth.getCurrentUser();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_likeds, R.id.navigation_account)
                .build();

        newPost = findViewById(R.id.newPostFB);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Factory.verifysUserIsLogged()) {
                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    Factory.toastMessage(MainActivity.this, getString(R.string.erro_no_user));
                }else {
                    startActivity(new Intent(getApplicationContext(), NewPost.class));
                }
            }
        });
    }


}
