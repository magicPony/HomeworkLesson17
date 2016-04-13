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
public final class Photo extends Model {

    private int albumId = -1, id = -1;
    private String title = null, url = null, thumbnailUrl = null;

    @Override
    public void updateByClass(Object object) {
        Photo photo = (Photo) object;
        this.albumId = photo.albumId;
        this.id = photo.id;
        this.title = photo.title;
        this.url = photo.url;
        this.thumbnailUrl = photo.thumbnailUrl;
    }

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;
                albumId = jsonObject.optInt(ApiConst.ALBUM_ID_KEY, -1);
                id = jsonObject.optInt(ApiConst.ID_KEY, -1);
                title = jsonObject.optString(ApiConst.TITLE_KEY, null);
                url = jsonObject.optString(ApiConst.URL_KEY, null);
                thumbnailUrl = jsonObject.optString(ApiConst.THUMBNAIL_URL_KEY, null);
                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                JSONObject results = (JSONObject) object;
                Photo photo = gson.fromJson(results.toString(), Photo.class);
                updateByClass(photo);
                break;
        }
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "albumId=" + albumId +
                ", id=" + id +
                ", title=" + title +
                ", url=" + url +
                ", thumbnailUrl=" + thumbnailUrl +
                "}";
    }
}
