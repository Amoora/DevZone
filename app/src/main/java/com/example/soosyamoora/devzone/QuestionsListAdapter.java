package com.example.soosyamoora.devzone;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class QuestionsListAdapter extends RecyclerView.Adapter<QuestionsListAdapter.ViewHolder> {

    public List<QuestionEntity> mList;
    public Context context;

    public QuestionsListAdapter(List<QuestionEntity> mList, Context context) {
        this.context = context;
        this.mList = mList;
    }


    @NonNull
    @Override
    public QuestionsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_model, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionsListAdapter.ViewHolder holder, int position) {
        holder.question_type.setText(mList.get(position).getQuestion_type());
        holder.question.setText(mList.get(position).getQuestion());
        holder.answer.setText(mList.get(position).getAnswer());
        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.setAction(Intent.ACTION_SEND);
                mIntent.setType("text/plain");
                mIntent.putExtra(Intent.EXTRA_TEXT, holder.question.getText().toString() + "\n\n" +
                        holder.answer.getText().toString());
                context.startActivity(Intent.createChooser(mIntent, "Share to"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView question_type, question, answer;
        public ImageView shareButton;
        View mView;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            question_type = mView.findViewById(R.id.question_type_placeholder);
            question = mView.findViewById(R.id.question);
            answer = mView.findViewById(R.id.question_answer);

            shareButton = mView.findViewById(R.id.share_button);
        }
    }
}
