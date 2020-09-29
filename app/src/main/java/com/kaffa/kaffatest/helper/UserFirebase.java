package com.kaffa.kaffatest.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserFirebase {

    public static FirebaseUser getUsuarioAtual() {
        FirebaseAuth user = ConfigFirebase.getFirebaseAuth();
        return user.getCurrentUser();
    }
}
