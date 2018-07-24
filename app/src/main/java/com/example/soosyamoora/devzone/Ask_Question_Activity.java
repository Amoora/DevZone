//package com.example.soosyamoora.devzone;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreSettings;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Ask_Question_Activity extends AppCompatActivity {
//
//    Spinner question_type;
//    Button ask_button;
//    EditText question_editText;
//    FirebaseFirestore firestoreRef;
//    FirebaseFirestoreSettings settings;
//    ArrayAdapter<CharSequence> adapter;
//    Map<String, Object> firestoreMap;
//    String question_type_string, question_string;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ask_question_activity);
//
//        question_type = findViewById(R.id.question_type_spinner);
//        question_editText = findViewById(R.id.question_editText);
//        ask_button = findViewById(R.id.ask_button);
//        settings = new FirebaseFirestoreSettings.Builder()
//                .setPersistenceEnabled(true)
//                .build();
//        firestoreRef = FirebaseFirestore.getInstance();
//        firestoreMap = new HashMap<>();
//
//        adapter = ArrayAdapter.createFromResource(this, R.array.question_types,
//                android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        question_type.setAdapter(adapter);
//
//        question_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                question_type_string = parent.getItemAtPosition(position).toString().trim();
//            }
//        });
//
//        ask_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (question_editText == null) {
//                    Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
//                } else {
//                    question_string = question_editText.getText().toString();
//                    Toast.makeText(getApplicationContext(),
//                            "you asked " + question_string + " that is a type of " + question_type_string,
//                            Toast.LENGTH_LONG).show();
//
//                    firestoreMap.put("Question Type", question_type_string);
//                    firestoreMap.put("Question", question_string);
//
//                    firestoreRef.collection("ASKED QUESTIONS")
//                            .document().set(firestoreMap)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(getApplicationContext(),
//                                                "Your Question is sent, you will be notified when answered, Thanks",
//                                                Toast.LENGTH_LONG).show();
//
//                                        question_editText.setText("");
//                                    } else {
//                                        Toast.makeText(getApplicationContext(),
//                                                "Your Question is not sent please connect to a network, Thanks",
//                                                Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            });
//                }
//            }
//        });
//    }
//}
