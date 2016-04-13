package com.example.taras.homeworklesson17.api;

import com.example.taras.homeworklesson17.api.interfaces.ConnectCallback;
import com.example.taras.homeworklesson17.api.models.Post;
import com.example.taras.homeworklesson17.api.response.AlbumListResponse;
import com.example.taras.homeworklesson17.api.response.CommentListResponse;
import com.example.taras.homeworklesson17.api.response.PhotoListResponse;
import com.example.taras.homeworklesson17.api.response.PostListResponse;
import com.example.taras.homeworklesson17.api.response.TodoListResponse;
import com.example.taras.homeworklesson17.api.response.UserListResponse;
import com.loopj.android.http.RequestParams;

/**
 * Created by taras on 11.04.16.
 */
public final class Api {
    public static void getPosts(ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConst.POSTS, new PostListResponse(), callback);
    }

    public static void getComments(ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConst.COMMENTS, new CommentListResponse(), callback);
    }

    public static void getAlbums(ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConst.ALBUMS, new AlbumListResponse(), callback);
    }

    public static void getPhotos(ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConst.PHOTOS, new PhotoListResponse(), callback);
    }

    public static void getTodos(ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConst.TODOS, new TodoListResponse(), callback);
    }

    public static void getUsers(ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConst.USERS, new UserListResponse(), callback);
    }

    public static void getPostId(int id, ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConst.POST_ID + id, new Post(), callback);
    }

    public static void getPost(int userId, ConnectCallback callback) {
        RequestParams requestParams = new RequestParams(ApiConst.USER_ID_KEY, userId);
        Connect.getInstance().getRequestWithParam(ApiConst.POSTS, requestParams, new PostListResponse(), callback);
    }
}
