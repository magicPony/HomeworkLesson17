package com.example.taras.homeworklesson17.api;

import com.example.taras.homeworklesson17.api.models.Album;
import com.example.taras.homeworklesson17.api.models.Comment;
import com.example.taras.homeworklesson17.api.models.Photo;
import com.example.taras.homeworklesson17.api.models.Post;
import com.example.taras.homeworklesson17.api.models.Todo;
import com.example.taras.homeworklesson17.api.models.User;

import java.util.ArrayList;

/**
 * Created by taras on 12.04.16.
 */
public class Data {
    public static ArrayList<User> userArrayList;
    public static ArrayList<Post> postArrayList;
    public static ArrayList<Todo> todoArrayList;
    public static ArrayList<Album> albumArrayList;
    public static ArrayList<Comment> commentArrayList;
    public static ArrayList<Photo> photoArrayList;

    public static User findUserById(int id) {
        for (User user : userArrayList)
            if (user.getId() == id) {
                return user;
            }

        return null;
    }

    public static Post findPostById(int id) {
        for (Post post : postArrayList)
            if (post.getId() == id) {
                return post;
            }

        return null;
    }

    public static Comment findCommentById(int id) {
        for (Comment comment : commentArrayList)
            if (comment.getId() == id) {
                return comment;
            }

        return null;
    }

    public static Album findAlbumById(int id) {
        for (Album album : albumArrayList)
            if (album.getId() == id) {
                return album;
            }

        return null;
    }
}
