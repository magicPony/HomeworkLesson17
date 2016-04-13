package com.example.taras.homeworklesson17.api.response;

import com.example.taras.homeworklesson17.api.Connect;
import com.example.taras.homeworklesson17.api.interfaces.ModelResponse;
import com.example.taras.homeworklesson17.api.models.Todo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by taras on 11.04.16.
 */

public class TodoListResponse implements ModelResponse {

    private ArrayList<Todo> todos;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        JSONArray results = (JSONArray) object;
        switch (parser) {
            case Connect.PARSER_JSON:
                todos = new ArrayList<>();

                for (int i = 0; i < results.length(); i++) {
                    Todo todo = new Todo();
                    todo.configure(results.getJSONObject(i));
                    todos.add(todo);
                }

                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                todos = gson.fromJson(results.toString(), new TypeToken<ArrayList<Todo>>(){}.getType());
                break;
        }
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }
}

