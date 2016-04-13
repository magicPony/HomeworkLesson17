package com.example.taras.homeworklesson17.api.response;

import com.example.taras.homeworklesson17.api.Connect;
import com.example.taras.homeworklesson17.api.interfaces.ModelResponse;
import com.example.taras.homeworklesson17.api.models.Comment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by taras on 11.04.16.
 */
public class CommentListResponse implements ModelResponse {

    private ArrayList<Comment> comments;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        JSONArray results = (JSONArray) object;
        switch (parser) {
            case Connect.PARSER_JSON:
                comments = new ArrayList<>();

                for (int i = 0; i < results.length(); i++) {
                    Comment comment = new Comment();
                    comment.configure(results.getJSONObject(i));
                    comments.add(comment);
                }

                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                comments = gson.fromJson(results.toString(), new TypeToken<ArrayList<Comment>>(){}.getType());
                break;
        }
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
