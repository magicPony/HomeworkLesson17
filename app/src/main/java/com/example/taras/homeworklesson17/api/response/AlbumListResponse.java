package com.example.taras.homeworklesson17.api.response;

import com.example.taras.homeworklesson17.api.Connect;
import com.example.taras.homeworklesson17.api.interfaces.ModelResponse;
import com.example.taras.homeworklesson17.api.models.Album;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by taras on 11.04.16.
 */
public class AlbumListResponse implements ModelResponse {

    private ArrayList<Album> albums;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();
        JSONArray results = (JSONArray) object;

        switch (parser) {
            case Connect.PARSER_JSON:
                albums = new ArrayList<>();

                for (int i = 0; i < results.length(); i++) {
                    Album album = new Album();
                    album.configure(results.getJSONObject(i));
                    albums.add(album);
                }

                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                albums = gson.fromJson(results.toString(), new TypeToken<ArrayList<Album>>(){}.getType());
                break;
        }
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
