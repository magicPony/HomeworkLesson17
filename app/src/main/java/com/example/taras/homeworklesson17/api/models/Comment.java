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
public final class Comment extends Model {

    private int postId = -1, id = -1;
    private String name = null, email = null, body = null;

    @Override
    public void updateByClass(Object object) {
        Comment comment = (Comment) object;
        this.postId = comment.postId;
        this.id = comment.id;
        this.name = comment.name;
        this.email = comment.email;
        this.body = comment.body;
    }

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;
                postId = jsonObject.optInt(ApiConst.POST_ID_KEY, -1);
                id = jsonObject.optInt(ApiConst.ID_KEY, -1);
                name = jsonObject.optString(ApiConst.NAME_KEY, null);
                email = jsonObject.optString(ApiConst.EMAIL_KEY, null);
                body = jsonObject.optString(ApiConst.BODY_KEY, null);
                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                JSONObject results = (JSONObject) object;
                Post post = gson.fromJson(results.toString(), Post.class);
                updateByClass(post);
                break;
        }
    }

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", id=" + id +
                ", name=" + name +
                ", email=" + email +
                "body=" + body +
                "}";
    }
}
