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
import com.example.taras.homeworklesson17.api.Data;
import com.example.taras.homeworklesson17.api.models.Todo;
import com.example.taras.homeworklesson17.api.models.User;

/**
 * Created by taras on 12.04.16.
 */
public class TodoListFragment extends Fragment {

    private MainActivity mainActivity;
    private User user;
    private LinearLayout linearLayout;

    public TodoListFragment(MainActivity mainActivity, User user) {
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
        tvTitle.setText("Todos");

        for (Todo todo : Data.todoArrayList)
            if (todo.getUserId() == user.getId()) {
                addTodo(todo);
            }

        linearLayout.invalidate();
    }

    private void addTodo(Todo todo) {
        LinearLayout llTodo = (LinearLayout) View.inflate(mainActivity, R.layout.todo_layout, null);
        TextView tvUserId, tvId, tvTitle, tvCompleted;

        tvUserId = (TextView) llTodo.findViewById(R.id.tv_user_id_TL);
        tvId = (TextView) llTodo.findViewById(R.id.tv_id_TL);
        tvTitle = (TextView) llTodo.findViewById(R.id.tv_title_TL);
        tvCompleted = (TextView) llTodo.findViewById(R.id.tv_completed_TL);

        tvUserId.setText(Integer.toString(todo.getUserId()));
        tvId.setText(Integer.toString(todo.getId()));
        tvTitle.setText(todo.getTitle());
        tvCompleted.setText(todo.isCompleted() ? "true" : "false");

        linearLayout.addView(llTodo);
    }
}
