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
public final class Album extends Model {

    private int userId = -1, id = -1;
    private String title = null;


    @Override
    public void updateByClass(Object object) {
        Album album = (Album) object;
        this.userId = album.userId;
        this.id = album.id;
        this.title = album.title;
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
                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                JSONObject results = (JSONObject) object;
                Album album = gson.fromJson(results.toString(), Album.class);
                updateByClass(album);
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

    @Override
    public String toString() {
        return "Album{" +
                "userId=" + userId +
                ", id=" + id +
                ", title=" + title +
                "}";
    }
}
