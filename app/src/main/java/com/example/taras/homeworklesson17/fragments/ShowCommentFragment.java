package com.example.taras.homeworklesson17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taras.homeworklesson17.MainActivity;
import com.example.taras.homeworklesson17.R;
import com.example.taras.homeworklesson17.api.Data;
import com.example.taras.homeworklesson17.api.models.Comment;

/**
 * Created by taras on 13.04.16.
 */
public class ShowCommentFragment extends Fragment {

    private MainActivity mainActivity;
    private Comment comment;

    public ShowCommentFragment(MainActivity mainActivity, int id) {
        this.mainActivity = mainActivity;
        comment = Data.findCommentById(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_comment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvPostId, tvId, tvName, tvEmail, tvBody;
        tvPostId = (TextView) view.findViewById(R.id.tv_post_id_FSC);
        tvId = (TextView) view.findViewById(R.id.tv_id_FSC);
        tvName = (TextView) view.findViewById(R.id.tv_name_FSC);
        tvEmail = (TextView) view.findViewById(R.id.tv_email_FSC);
        tvBody = (TextView) view.findViewById(R.id.tv_body_FSC);

        tvPostId.setText(Integer.toString(comment.getPostId()));
        tvId.setText(Integer.toString(comment.getId()));
        tvName.setText(comment.getName());
        tvEmail.setText(comment.getEmail());
        tvBody.setText(comment.getBody());
    }
}
