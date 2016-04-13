package com.example.taras.homeworklesson17.api.models;

import com.example.taras.homeworklesson17.api.ApiConst;
import com.example.taras.homeworklesson17.api.Connect;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by taras on 11.04.16.
 */
public final class Todo extends Model {

    private int userId = -1, id = -1;
    private String title = null;
    private boolean completed = false;

    @Override
    public void updateByClass(Object object) {
        Todo todo = (Todo) object;
        this.userId = todo.userId;
        this.id = todo.id;
        this.title = todo.title;
        this.completed = todo.completed;
    }

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;
                userId = jsonObject.optInt(ApiConst.USER_ID_KEY, -1);
                id = jsonObject.optInt(ApiConst.ID_KEY, -1);
                title = jsonObject.optString(ApiConst.TITLE_KEY, null);
                completed = jsonObject.optBoolean(ApiConst.COMPLETED_KEY, false);
                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                JSONObject results = (JSONObject) object;
                Todo todo = gson.fromJson(results.toString(), Todo.class);
                updateByClass(todo);
                break;
        }
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title=" + title +
                ", completed=" + completed +
                "}";
    }
}
