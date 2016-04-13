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
public final class Address extends Model {

    private String street = null, suite = null, city = null, zipcode = null;
    private Geo geo = null;

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    @Override
    public void updateByClass(Object object) {
        Address address = (Address) object;
        this.street = address.street;
        this.suite = address.suite;
        this.city = address.city;
        this.zipcode = address.zipcode;
        this.geo = address.geo;
    }

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        switch (parser) {
            case Connect.PARSER_JSON :
                JSONObject jsonObject = (JSONObject) object;
                street = jsonObject.optString(ApiConst.STREET_KEY, null);
                suite = jsonObject.optString(ApiConst.SUITE_KEY, null);
                city = jsonObject.optString(ApiConst.CITY_KEY, null);
                zipcode = jsonObject.optString(ApiConst.ZIPCODE_KEY, null);
                JSONObject jsonGeo = jsonObject.getJSONObject(ApiConst.GEO_KEY);
                geo = new Geo();
                geo.configure(jsonGeo);
                break;

            case Connect.PARSER_GSON :
                Gson gson = new Gson();
                JSONObject result = (JSONObject) object;
                Address address = gson.fromJson(result.toString(), Address.class);
                updateByClass(address);
                break;
        }
    }

    @Override
    public String toString() {
        return "Address{" +
                "street=" + street +
                ", suite=" + suite +
                ", city=" + city +
                ", zipcode=" + zipcode +
                ", " + geo.toString() +
                "}";
    }

    public String getLat() {
        return geo.getLat();
    }

    public String getLng() {
        return geo.getLng();
    }
}
