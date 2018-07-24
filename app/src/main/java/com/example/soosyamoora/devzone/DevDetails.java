package com.example.soosyamoora.devzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DevDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_details);

        Intent intent = getIntent();

        String contactName = intent.getStringExtra("name");
        TextView nameTextView = (TextView)findViewById(R.id.txtName);
        nameTextView.setText(contactName);

        String DtName = intent.getStringExtra("name");
        TextView DtV = (TextView)findViewById(R.id.detailName);
        DtV.setText(DtName);


        String contactImage = intent.getStringExtra("hotoo");
        ImageView contactImageView = (ImageView) findViewById(R.id.detailImage);
        Picasso.with(getApplicationContext())
                .load(contactImage)
                .resize(50, 50)
                .into(contactImageView);

        String Dphone = intent.getStringExtra("phone");
        TextView dtxthone = (TextView) findViewById(R.id.txtPhone);
        dtxthone.setText(Dphone);


        String Dword = intent.getStringExtra("area");
        TextView dtxtword = (TextView) findViewById(R.id.textWord);
        dtxtword.setText(Dword);

        String Demail = intent.getStringExtra("email");
        TextView DtxtEmail = (TextView) findViewById(R.id.txtEmail);
        DtxtEmail.setText(Demail);

        String Shub = intent.getStringExtra("hub");
        TextView txthubV = (TextView) findViewById(R.id.txtHub);
        txthubV.setText(Shub);

        String SArea = intent.getStringExtra("area");
        TextView txtarea = (TextView) findViewById(R.id.txtArea);
        txtarea.setText(SArea);

/**
        String Dgit = intent.getStringExtra("github");
        TextView Vgit = (TextView) findViewById(R.id.github);
        Vgit.setText(Dgit);




/**

 String Special = intent.getStringExtra("special");
 TextView txtspecial = (TextView) findViewById(R.id.txtSpecialization);
 txtspecial.setText(Special);
 */

    }
}
