package com.example.taras.homeworklesson17.api.models;

import com.example.taras.homeworklesson17.api.interfaces.GsonModel;
import com.example.taras.homeworklesson17.api.interfaces.ModelResponse;

import org.json.JSONException;

import java.io.Serializable;
import java.text.ParseException;

/**
 * Created by taras on 11.04.16.
 */
public class Model implements ModelResponse, Serializable, GsonModel {

    @Override
    public void updateByClass(Object object) {
    }

    @Override
    public void configure(Object object) throws JSONException, ParseException {
    }
}
