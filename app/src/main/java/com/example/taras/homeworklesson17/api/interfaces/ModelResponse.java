package com.example.taras.homeworklesson17.api.interfaces;

import org.json.JSONException;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Created by taras on 11.04.16.
 */
public interface ModelResponse {
    void configure(Object object) throws JSONException, ParseException, IllegalAccessException, InstantiationException, InvocationTargetException;
}
