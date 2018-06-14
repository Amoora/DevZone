package com.example.soosyamoora.devzone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Ask_Question_Activity extends AppCompatActivity {

    Spinner question_type;
    Button ask_button;
    EditText question_editText;
    ArrayAdapter<CharSequence> adapter;
    String question_type_string, question_string;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_question_activity);

        question_type = findViewById(R.id.question_type_spinner);
        question_editText = findViewById(R.id.question_editText);
        ask_button = findViewById(R.id.ask_button);

        adapter = ArrayAdapter.createFromResource(this, R.array.question_types,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        question_type.setAdapter(adapter);

        question_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                question_type_string = parent.getItemAtPosition(position).toString().trim();
            }
        });

        ask_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_string = question_editText.getText().toString();
                Toast.makeText(getApplicationContext(), "you asked " + question_string + " that is a type of " + question_type_string, Toast.LENGTH_LONG).show();
            }
        });
    }
}
