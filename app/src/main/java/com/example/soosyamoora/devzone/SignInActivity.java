package com.example.soosyamoora.devzone;

//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.facebook.AccessToken;
//import com.facebook.AccessTokenTracker;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.FacebookSdk;
//import com.facebook.Profile;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FacebookAuthProvider;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//
//public class SignInActivity extends AppCompatActivity {
//
//    private FirebaseAuth.AuthStateListener mAuthListener;
//    private FirebaseAuth mAuth;
//    LoginButton loginButton;
//    CallbackManager mCallbackManager;
//    String TAG ="Hey";
//    String name;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_in);
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        mCallbackManager = CallbackManager.Factory.create();
//        loginButton.setReadPermissions("email");
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                Log.i(TAG,"Hello"+loginResult.getAccessToken().getToken());
//                //  Toast.makeText(MainActivity.this, "Token:"+loginResult.getAccessToken(), Toast.LENGTH_SHORT).show();
//
//                handleFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
//
//
//        mAuthListener = new FirebaseAuth.AuthStateListener(){
//
//
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//
//                if (user!=null){
//                    name = user.getDisplayName();
//                    Toast.makeText(SignInActivity.this,""+user.getDisplayName(),Toast.LENGTH_LONG).show();
//                }else {
//                    Toast.makeText(SignInActivity.this,"something went wrong",Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        };
//
//    }
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Pass the activity result back to the Facebook SDK
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void handleFacebookAccessToken(AccessToken token) {
//        Log.d(TAG, "handleFacebookAccessToken:" + token);
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if (task.isSuccessful()) {
//                            Log.w(TAG, "signInWithCredential", task.getException());
//                            Toast.makeText(SignInActivity.this, "Success",
//                                    Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(SignInActivity.this, "Authentication error",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//
//
//                    }
//                });
//    }
//}




import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import static android.widget.Toast.*;

public class SignInActivity extends AppCompatActivity{
    private static final String TAG = "";
    private static final String EMAIL = "email";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private ProgressBar progressBar;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);




           progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
//        login Button  callback registration
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                setResult(RESULT_OK);
                finish();
                  handleFacebookAccessToken(loginResult.getAccessToken());
                launchMainActivity();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }


        });
        mAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };


    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
//        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!= null){
            String Name = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            Uri photoUrl = currentUser.getPhotoUrl();
            String uid = currentUser.getUid();

        }
    }

//    The medthod that shows after succesfull login and launch the main activity

    private void updateUI(){
        Toast.makeText(SignInActivity.this, "You're Logged in", LENGTH_LONG).show();
        launchMainActivity();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

//        forward result to call back manager for login button

    private void handleFacebookAccessToken(AccessToken token) {
        progressBar.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            makeText(SignInActivity.this, "Authentication failed.",
                                    LENGTH_SHORT).show();
                        }
//                      setting login button to invisible when changing intent
                        progressBar.setVisibility(View.VISIBLE);
                        loginButton.setVisibility(View.GONE);
                    }
                });
    }

        private void launchMainActivity(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
}
