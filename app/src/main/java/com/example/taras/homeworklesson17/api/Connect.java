package com.example.taras.homeworklesson17.api;

import android.util.Log;

import com.example.taras.homeworklesson17.api.interfaces.ConnectCallback;
import com.example.taras.homeworklesson17.api.interfaces.ModelResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Created by taras on 11.04.16.
 */
public final class Connect {
    public static final int PARSER_GSON = 10;
    public static final int PARSER_JSON = 11;
    private static final String LOG_TAG = Connect.class.getName();

    private static Connect _instance;
    private AsyncHttpClient client;
    private int mParser = PARSER_JSON;

    public static Connect getInstance() {
        if (_instance == null) {
            _instance = new Connect();
            _instance.client = new AsyncHttpClient();
        }

        return _instance;
    }

    public void getRequestWithParam(String url, RequestParams requestParams,final ModelResponse modelResponse , final ConnectCallback callback) {
        Log.d(LOG_TAG,ApiConst.URL_SERVER+url);
        client.get(ApiConst.URL_SERVER+url,requestParams,new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        parseData(response, modelResponse, callback);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        parseData(response, modelResponse, callback);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        callback.onFailure(throwable,errorResponse.toString());
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        callback.onFailure(throwable, errorResponse.toString());
                    }

                }

        );
    }

    private void parseData(Object jsonObject, ModelResponse modelObject, ConnectCallback callback) {
        if (null != modelObject) {
            try {
                try {
                    modelObject.configure(jsonObject);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                callback.onSuccess(modelObject);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }



    public int getParser() {
        return mParser;
    }


    public void getRequest(String url,final ModelResponse modelResponse , final ConnectCallback callback) {
        getRequestWithParam(url, null, modelResponse, callback);
    }
}