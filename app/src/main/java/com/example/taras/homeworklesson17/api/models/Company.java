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
public final class Company extends Model {

    private String name = null, catchPhrase = null, bs = null;

    @Override
    public void updateByClass(Object object) {
        Company company = (Company) object;
        this.name = company.name;
        this.catchPhrase = company.catchPhrase;
        this.bs = company.bs;
    }

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        switch (parser) {
            case Connect.PARSER_JSON :
                JSONObject jsonObject = (JSONObject) object;
                name = jsonObject.optString(ApiConst.NAME_KEY, null);
                catchPhrase = jsonObject.optString(ApiConst.CATCH_PHRASE_KEY, null);
                bs = jsonObject.optString(ApiConst.BS_KEY, null);
                break;

            case Connect.PARSER_GSON :
                Gson gson = new Gson();
                JSONObject result = (JSONObject) object;
                Company company = gson.fromJson(result.toString(), Company.class);
                updateByClass(company);
                break;
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "name=" + name +
                ", catchPhrase=" + catchPhrase +
                ", bs=" + bs +
                "}";
    }

    public String getName() {
        return name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public String getBs() {
        return bs;
    }
}
