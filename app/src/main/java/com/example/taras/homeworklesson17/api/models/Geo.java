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
public final class Geo extends Model {

    private String lat = null, lng = null;

    @Override
    public void updateByClass(Object object) {
        Geo geo = (Geo) object;
        this.lat = geo.lat;
        this.lng = geo.lng;
    }

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;
                lat = jsonObject.optString(ApiConst.LAT_KEY, null);
                lng = jsonObject.optString(ApiConst.LNG_KEY, null);
                break;


            case Connect.PARSER_GSON:
                Gson gson = new Gson();
                JSONObject results = (JSONObject) object;
                Geo geo = gson.fromJson(results.toString(), Geo.class);
                updateByClass(geo);
                break;
        }
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
