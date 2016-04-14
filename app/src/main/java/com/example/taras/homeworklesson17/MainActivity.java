package com.example.taras.homeworklesson17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.taras.homeworklesson17.api.ApiConst;
import com.example.taras.homeworklesson17.api.Connect;
import com.example.taras.homeworklesson17.api.EventHandler;
import com.example.taras.homeworklesson17.api.FragmentHandler;
import com.example.taras.homeworklesson17.fragments.CreateUserFragment;

public final class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connect.initMainActivity(this);
        FragmentHandler.initUserList();
    }

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CreateUserFragment createUserFragment = new CreateUserFragment();
        EventHandler.commitFragment(createUserFragment, ApiConst.CREATE_USER_FRAGMENT);
        return true;
    }
}