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
import com.example.taras.homeworklesson17.api.EventHandler;
import com.example.taras.homeworklesson17.api.models.User;

public final class UserListFragment extends Fragment implements View.OnClickListener {

    private LinearLayout linearLayout;

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
        tvTitle.setText(MainActivity.getInstance().getString(R.string.users));

        for (User user : Data.userArrayList) {
            addUser(user);
        }

        linearLayout.invalidate();
    }

    private void addUser(User user) {
        LinearLayout llUserCard = (LinearLayout) View.inflate(MainActivity.getInstance(), R.layout.card_layout, null);
        TextView tvUsername;
        tvUsername = (TextView) llUserCard.findViewById(R.id.tv_field_CL);

        llUserCard.setTag(user.getId());
        tvUsername.setText(user.getName());
        linearLayout.addView(llUserCard);

        llUserCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!EventHandler.isFragmentOnTheTop(ApiConst.USER_LIST_FRAGMENT)) {
            return;
        }

        ShowUserFragment showUserFragment = new ShowUserFragment((int) v.getTag());
        EventHandler.commitFragment(showUserFragment, ApiConst.SHOW_USER_FRAGMENT);
    }
}
