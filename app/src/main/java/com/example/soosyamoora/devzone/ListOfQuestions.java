package com.example.soosyamoora.devzone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class ListOfQuestions extends AppCompatActivity {

    List<QuestionEntity> mList;
    ArrayList<QuestionEntity> arrayList;
    RecyclerView recyclerView;
    QuestionsListAdapter questionsListAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_questions);

        mList = new ArrayList<>();
        recyclerView = findViewById(R.id.questions_list);
        arrayList = new ArrayList<>();
        questionsListAdapter = new QuestionsListAdapter(mList, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(questionsListAdapter);

        db.collection("ASKED QUESTIONS").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED || doc.getType() == DocumentChange.Type.MODIFIED) {
                        String question_type, question, answer;

                        question = doc.getDocument().getString("Question Type");
                        question_type = doc.getDocument().getString("Question");
                        answer = doc.getDocument().getString("ANSWER");

                        if (!question.isEmpty() && !question_type.isEmpty() && !answer.isEmpty()) {
                            QuestionEntity QE = new QuestionEntity(question_type, question, answer);
                            mList.add(QE);
                        }

                        questionsListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
