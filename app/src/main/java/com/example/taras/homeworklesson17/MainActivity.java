package com.example.taras.homeworklesson17;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.taras.homeworklesson17.api.Api;
import com.example.taras.homeworklesson17.api.ApiConst;
import com.example.taras.homeworklesson17.api.Data;
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
import com.example.taras.homeworklesson17.fragments.CreateUserFragment;
import com.example.taras.homeworklesson17.fragments.PhotoListFragment;
import com.example.taras.homeworklesson17.fragments.PostListFragment;
import com.example.taras.homeworklesson17.fragments.TodoListFragment;
import com.example.taras.homeworklesson17.fragments.UserListFragment;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUserList();
    }

    private void initUserList() {
        Api.getUsers(new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                UserListResponse userListResponse = (UserListResponse) object;
                Data.userArrayList = userListResponse.getUsers();

                UserListFragment userListFragment = new UserListFragment(MainActivity.this);
                commitFragment(userListFragment, ApiConst.USER_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.this, "MainActivity->initUserList failure", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void commitFragment(Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rl_container_AM, fragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public boolean isFragmentOnTheTop(String fragment) {
        int stackSize = getSupportFragmentManager().getBackStackEntryCount();
        String topFragment = getSupportFragmentManager()
                .getBackStackEntryAt(stackSize - 1)
                .getName();
        return topFragment.equals(fragment);
    }

    public void openTodoList(final User user) {
        Api.getTodos(new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                TodoListResponse todoListResponse = (TodoListResponse) object;
                Data.todoArrayList = todoListResponse.getTodos();

                TodoListFragment todoListFragment = new TodoListFragment(MainActivity.this, user);
                commitFragment(todoListFragment, ApiConst.TODO_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.this, "MainActivity->openTodoList failure", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void openAlbumList(final User user) {
        Api.getAlbums(new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                AlbumListResponse albumListResponse = (AlbumListResponse) object;
                Data.albumArrayList = albumListResponse.getAlbums();

                AlbumListFragment albumListFragment = new AlbumListFragment(MainActivity.this, user);
                commitFragment(albumListFragment, ApiConst.ALBUM_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.this, "MainActivity->openAlbumList failure", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void openPostList(final User user) {
        Api.getPosts(new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                PostListResponse postListResponse = (PostListResponse) object;
                Data.postArrayList = postListResponse.getPosts();

                PostListFragment postListFragment = new PostListFragment(MainActivity.this, user);
                commitFragment(postListFragment, ApiConst.POST_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.this, "MainActivity->openPostList failure", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void openCommentListFragment(final Post post) {
        ConnectCallback connectCallback = new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                CommentListResponse commentListResponse = (CommentListResponse) object;
                Data.commentArrayList = commentListResponse.getComments();

                CommentListFragment commentListFragment = new CommentListFragment(MainActivity.this, post);
                commitFragment(commentListFragment, ApiConst.COMMENT_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.this, "MainActivity->openCommentList failure", Toast.LENGTH_LONG)
                        .show();
            }
        };

        Api.getComments(connectCallback);
    }

    public void openPhotoList(final int id) {
        ConnectCallback connectCallback = new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                PhotoListResponse photoListResponse = (PhotoListResponse) object;
                Data.photoArrayList = photoListResponse.getPhotos();

                PhotoListFragment photoListFragment = new PhotoListFragment(MainActivity.this, id);
                commitFragment(photoListFragment, ApiConst.PHOTO_LIST_FRAGMENT);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {
                Toast
                        .makeText(MainActivity.this, "MainActivity->openPhotoList failure", Toast.LENGTH_LONG)
                        .show();
            }
        };

        Api.getPhotos(connectCallback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CreateUserFragment createUserFragment = new CreateUserFragment(this);
        commitFragment(createUserFragment, ApiConst.CREATE_USER_FRAGMENT);
        return true;
    }

    public void showToast(String message) {
        Toast
                .makeText(this, message, Toast.LENGTH_LONG)
                .show();
    }
}
