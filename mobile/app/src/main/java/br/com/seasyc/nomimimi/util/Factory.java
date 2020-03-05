package br.com.seasyc.nomimimi.util;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.seasyc.nomimimi.config.FirebaseSettings;

public class Factory {

    public static void toastMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static boolean verifysUserIsLogged() {

        FirebaseAuth firebaseAuth = FirebaseSettings.getFirebaseAuth();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null){
            return false;
        }
        return true;
    }

}
