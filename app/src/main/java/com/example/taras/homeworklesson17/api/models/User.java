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
public final class User extends Model {

    private int id = -1;
    private String name = null, username = null, email = null, phone = null, website = null;
    private Address address = null;
    private Company company = null;

    @Override
    public void updateByClass(Object object) {
        User user = (User) object;
        this.id = user.id;
        this.name = user.name;
        this.username = user.username;
        this.email = user.username;
        this.phone = user.phone;
        this.website = user.website;
        this.address = user.address;
        this.company = user.company;
    }

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        switch (parser) {
            case Connect.PARSER_GSON :
                JSONObject jsonObject = (JSONObject) object;
                this.id = jsonObject.optInt(ApiConst.ID_KEY, -1);
                this.name = jsonObject.optString(ApiConst.NAME_KEY, null);
                this.username = jsonObject.optString(ApiConst.USERNAME_KEY, null);
                this.email = jsonObject.optString(ApiConst.EMAIL_KEY, null);
                this.phone = jsonObject.optString(ApiConst.PHONE_KEY, null);
                this.website = jsonObject.optString(ApiConst.WEBSITE_KEY, null);

                JSONObject jsonAddress = jsonObject.getJSONObject(ApiConst.ADDRESS_KEY);
                address = new Address();
                address.configure(jsonAddress);

                JSONObject jsonCompany = jsonObject.getJSONObject(ApiConst.COMPANY_KEY);
                company = new Company();
                company.configure(jsonCompany);
                break;

            case Connect.PARSER_JSON :
                Gson gson = new Gson();
                JSONObject results = (JSONObject) object;
                User user = gson.fromJson(results.toString(), User.class);
                updateByClass(user);
                break;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", username=" + username +
                ", email=" + email +
                ", " + address.toString() +
                ", phone=" + phone +
                ", website=" + website +
                ", " + company.toString() +
                "}";
    }

    public String getStreet() {
        return address.getStreet();
    }

    public String getSuite() {
        return address.getSuite();
    }

    public String getCity() {
        return address.getCity();
    }

    public String getZipcode() {
        return address.getZipcode();
    }

    public String getLat() {
        return address.getLat();
    }

    public String getLng() {
        return address.getLng();
    }

    public String getCompanyName() {
        return company.getName();
    }

    public String getCatchPhrase() {
        return company.getCatchPhrase();
    }

    public String getBs() {
        return company.getBs();
    }
}
