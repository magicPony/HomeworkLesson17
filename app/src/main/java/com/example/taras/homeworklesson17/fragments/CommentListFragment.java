package com.example.taras.homeworklesson17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taras.homeworklesson17.MainActivity;
import com.example.taras.homeworklesson17.R;
import com.example.taras.homeworklesson17.api.ApiConst;
import com.example.taras.homeworklesson17.api.Data;
import com.example.taras.homeworklesson17.api.models.Comment;
import com.example.taras.homeworklesson17.api.models.Post;

/**
 * Created by taras on 13.04.16.
 */
public class CommentListFragment extends Fragment implements View.OnClickListener {

    private MainActivity mainActivity;
    private Post post;
    private LinearLayout linearLayout;

    public CommentListFragment(MainActivity mainActivity, Post post) {
        this.mainActivity = mainActivity;
        this.post = post;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_LL);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title_LL);
        tvTitle.setText("Comments");

        for (Comment comment : Data.commentArrayList)
            if (comment.getPostId() == post.getId()) {
                addComment(comment);
            }

        linearLayout.invalidate();
    }

    private void addComment(Comment comment) {
        LinearLayout llComment = (LinearLayout) View.inflate(mainActivity, R.layout.card_layout, null);
        TextView tvEmail = (TextView) llComment.findViewById(R.id.tv_field_CL);

        llComment.setTag(comment.getId());
        tvEmail.setText(comment.getEmail());
        linearLayout.addView(llComment);

        llComment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!mainActivity.isFragmentOnTheTop(ApiConst.COMMENT_LIST_FRAGMENT)) {
            return;
        }

        ShowCommentFragment showCommentFragment = new ShowCommentFragment(mainActivity, (int) v.getTag());
        mainActivity.commitFragment(showCommentFragment, ApiConst.SHOW_COMMENT_FRAGMENT);
    }
}
