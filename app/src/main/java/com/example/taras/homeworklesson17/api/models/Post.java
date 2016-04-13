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

public final class Post extends Model {

    private int userId = -1, id = -1;
    private String title = null, body = null;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();
        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;
                if (jsonObject.has(ApiConst.USER_ID_KEY) && !jsonObject.isNull(ApiConst.USER_ID_KEY)) {
                    userId = jsonObject.getInt("userId");
                }

                id = jsonObject.optInt(ApiConst.ID_KEY,100);

                if (jsonObject.has("body") && !jsonObject.isNull("body")) {
                    body = jsonObject.getString("body");
                }

                title = jsonObject.optString(ApiConst.TITLE_KEY);
                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                JSONObject results = (JSONObject) object;
                Post post = gson.fromJson(results.toString(), Post.class);
                updateByClass(post);
                break;
        }
    }

    @Override
    public void updateByClass(Object object) {
        Post post = (Post) object;
        this.id = post.id;
        this.userId = post.userId;
        this.body = post.body;
        this.title = post.title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

