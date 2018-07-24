package com.example.soosyamoora.devzone;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.devcommtext);
          text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListDevCommActivity();
            }
        });
//        CardView cardViewDev = findViewById(R.id.eventCard);
//            cardViewDev.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View v) {
//                    openEventList();
//                }
//            });
    }

    public void openListDevCommActivity() {
        Intent intent = new Intent(this, ListDevComm.class);
        startActivity(intent);
    }

    public void openEventList(){
        Intent intent = new Intent (this, EventActivity.class );
        startActivity(intent);
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this,SignInActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}