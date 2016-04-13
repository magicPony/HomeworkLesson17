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
import com.example.taras.homeworklesson17.api.ApiConst;
import com.example.taras.homeworklesson17.api.Data;
import com.example.taras.homeworklesson17.api.models.Post;

/**
 * Created by taras on 12.04.16.
 */
public class ShowPostFragment extends Fragment implements View.OnClickListener {

    private Post post;
    private MainActivity mainActivity;

    public ShowPostFragment(MainActivity mainActivity, int id) {
        this.mainActivity = mainActivity;
        this.post = Data.findPostById(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_post, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvUserId, tvId, tvTitle, tvBody;
        tvUserId = (TextView) view.findViewById(R.id.tv_user_id_FSP);
        tvId = (TextView) view.findViewById(R.id.tv_id_FSP);
        tvTitle = (TextView) view.findViewById(R.id.tv_title_FSP);
        tvBody = (TextView) view.findViewById(R.id.tv_body_FSP);

        tvUserId.setText(Integer.toString(post.getUserId()));
        tvId.setText(Integer.toString(post.getId()));
        tvTitle.setText(post.getTitle());
        tvBody.setText(post.getBody());

        view.findViewById(R.id.btn_comments_FSP).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!mainActivity.isFragmentOnTheTop(ApiConst.SHOW_POST_FRAGMENT)) {
            return;
        }

        mainActivity.openCommentListFragment(post);
    }
}
