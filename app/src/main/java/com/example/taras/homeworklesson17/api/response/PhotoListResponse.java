package com.example.taras.homeworklesson17.api.response;

import com.example.taras.homeworklesson17.api.Connect;
import com.example.taras.homeworklesson17.api.interfaces.ModelResponse;
import com.example.taras.homeworklesson17.api.models.Photo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by taras on 11.04.16.
 */
public class PhotoListResponse implements ModelResponse {

    private ArrayList<Photo> photos;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        JSONArray results = (JSONArray) object;
        switch (parser) {
            case Connect.PARSER_JSON:
                photos = new ArrayList<>();

                for (int i = 0; i < results.length(); i++) {
                    Photo photo = new Photo();
                    photo.configure(results.getJSONObject(i));
                    photos.add(photo);
                }

                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                photos = gson.fromJson(results.toString(), new TypeToken<ArrayList<Photo>>(){}.getType());
                break;
        }
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }
}
