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
import com.example.taras.homeworklesson17.api.models.Post;
import com.example.taras.homeworklesson17.api.models.User;

/**
 * Created by taras on 12.04.16.
 */
public class PostListFragment extends Fragment implements View.OnClickListener {

    private MainActivity mainActivity;
    private User user;
    private LinearLayout linearLayout;

    public PostListFragment(MainActivity mainActivity, User user) {
        this.mainActivity = mainActivity;
        this.user = user;
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
        tvTitle.setText(getString(R.string.posts));

        for (Post post : Data.postArrayList)
            if (post.getUserId() == user.getId()) {
                addPost(post);
            }

        linearLayout.invalidate();
    }

    private void addPost(Post post) {
        LinearLayout llPost = (LinearLayout) View.inflate(mainActivity, R.layout.card_layout, null);
        TextView tvTitle = (TextView) llPost.findViewById(R.id.tv_field_CL);

        llPost.setTag(post.getId());
        tvTitle.setText(post.getTitle());
        linearLayout.addView(llPost);

        llPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!mainActivity.isFragmentOnTheTop(ApiConst.POST_LIST_FRAGMENT)) {
            return;
        }

        ShowPostFragment showPostFragment = new ShowPostFragment(mainActivity, (int) v.getTag());
        mainActivity.commitFragment(showPostFragment, ApiConst.SHOW_POST_FRAGMENT);
    }
}
