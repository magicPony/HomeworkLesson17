package com.example.taras.homeworklesson17.api;

import android.widget.Toast;

import com.example.taras.homeworklesson17.MainActivity;
import com.example.taras.homeworklesson17.R;
import com.example.taras.homeworklesson17.api.interfaces.ConnectCallback;
import com.example.taras.homeworklesson17.api.models.Post;
import com.example.taras.homeworklesson17.api.models.User;
import com.example.taras.homeworklesson17.api.response.AlbumListResponse;
import com.example.taras.homeworklesson17.api.response.CommentListResponse;
import com.example.taras.homeworklesson17.api.response.PhotoListResponse;
import com.example.taras.homeworklesson17.api.response.PostListResponse;
import com.example.taras.homeworklesson17.api.response.TodoListResponse;
import com.example.taras.homeworklesson17.api.response.UserListResponse;
import com.example.taras.homeworklesson17.fragments.AlbumListFragment;
import com.example.taras.homeworklesson17.fragments.CommentListFragment;
import com.example.taras.homeworklesson17.fragments.PhotoListFragment;
import com.example.taras.homeworklesson17.fragments.PostListFragment;
import com.example.taras.homeworklesson17.fragments.TodoListFragment;
import com.example.taras.homeworklesson17.fragments.UserListFragment;

/**
 * Created by taras on 15.04.16.
 */
public class FragmentHandler {

    public static void initUserList() {
        Api.getUsers(new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                EventHandler.showToast(MainActivity.getInstance().getString(R.string.loading_finished));
                UserListResponse userListResponse = (UserListResponse) object;
                Data.userArrayList = userListResponse.getUsers();

                UserListFragment userListFragment = new UserListFragment();
                EventHandler.commitFragment(userListFragment, ApiConst.USER_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.getInstance(), "MainActivity->initUserList failure", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public static void openTodoList(final User user) {
        Api.getTodos(new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                EventHandler.showToast(MainActivity.getInstance().getString(R.string.loading_finished));
                TodoListResponse todoListResponse = (TodoListResponse) object;
                Data.todoArrayList = todoListResponse.getTodos();

                TodoListFragment todoListFragment = new TodoListFragment(user);
                EventHandler.commitFragment(todoListFragment, ApiConst.TODO_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.getInstance(), "MainActivity->openTodoList failure", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public static void openAlbumList(final User user) {
        Api.getAlbums(new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                EventHandler.showToast(MainActivity.getInstance().getString(R.string.loading_finished));
                AlbumListResponse albumListResponse = (AlbumListResponse) object;
                Data.albumArrayList = albumListResponse.getAlbums();

                AlbumListFragment albumListFragment = new AlbumListFragment(user);
                EventHandler.commitFragment(albumListFragment, ApiConst.ALBUM_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.getInstance(), "MainActivity->openAlbumList failure", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public static void openPostList(final User user) {
        Api.getPosts(new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                EventHandler.showToast(MainActivity.getInstance().getString(R.string.loading_finished));
                PostListResponse postListResponse = (PostListResponse) object;
                Data.postArrayList = postListResponse.getPosts();

                PostListFragment postListFragment = new PostListFragment(user);
                EventHandler.commitFragment(postListFragment, ApiConst.POST_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.getInstance(), "MainActivity->openPostList failure", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public static void openCommentList(final Post post) {
        ConnectCallback connectCallback = new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                EventHandler.showToast(MainActivity.getInstance().getString(R.string.loading_finished));
                CommentListResponse commentListResponse = (CommentListResponse) object;
                Data.commentArrayList = commentListResponse.getComments();

                CommentListFragment commentListFragment = new CommentListFragment(post);
                EventHandler.commitFragment(commentListFragment, ApiConst.COMMENT_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.getInstance(), "MainActivity->openCommentList failure", Toast.LENGTH_SHORT)
                        .show();
            }
        };

        Api.getComments(connectCallback);
    }

    public static void openPhotoList(final int id) {
        ConnectCallback connectCallback = new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                EventHandler.showToast(MainActivity.getInstance().getString(R.string.loading_finished));
                PhotoListResponse photoListResponse = (PhotoListResponse) object;
                Data.photoArrayList = photoListResponse.getPhotos();

                PhotoListFragment photoListFragment = new PhotoListFragment(id);
                EventHandler.commitFragment(photoListFragment, ApiConst.PHOTO_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.getInstance(), "MainActivity->openPhotoList failure", Toast.LENGTH_SHORT)
                        .show();
            }
        };

        Api.getPhotos(connectCallback);
    }
}
