package com.example.taras.homeworklesson17.api.response;

import com.example.taras.homeworklesson17.api.Connect;
import com.example.taras.homeworklesson17.api.interfaces.ModelResponse;
import com.example.taras.homeworklesson17.api.models.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by taras on 11.04.16.
 */
public class PostListResponse implements ModelResponse {

    private ArrayList<Post> posts;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        JSONArray results = (JSONArray) object;
        switch (parser) {
            case Connect.PARSER_JSON:
                posts = new ArrayList<>();

                for (int i = 0; i < results.length(); i++) {
                    Post post = new Post();
                    post.configure(results.getJSONObject(i));
                    posts.add(post);
                }

                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                posts = gson.fromJson(results.toString(), new TypeToken<ArrayList<Post>>(){}.getType());
                break;
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
