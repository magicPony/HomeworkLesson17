package com.example.taras.homeworklesson17.api.interfaces;

/**
 * Created by taras on 11.04.16.
 */
public interface ConnectCallback {
    void onSuccess(Object object);

    void onFailure(Throwable throwable, String errorMessage);
}
