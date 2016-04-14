package com.example.taras.homeworklesson17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taras.homeworklesson17.MainActivity;
import com.example.taras.homeworklesson17.R;
import com.example.taras.homeworklesson17.api.ApiConst;
import com.example.taras.homeworklesson17.api.Data;
import com.example.taras.homeworklesson17.api.EventHandler;
import com.example.taras.homeworklesson17.api.FragmentHandler;
import com.example.taras.homeworklesson17.api.models.Album;
import com.example.taras.homeworklesson17.api.models.User;

/**
 * Created by taras on 12.04.16.
 */
public class AlbumListFragment extends Fragment implements View.OnClickListener {

    private User user;
    private LinearLayout linearLayout;

    public AlbumListFragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_LL);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title_LL);
        tvTitle.setText(MainActivity.getInstance().getString(R.string.albums));

        for (Album album : Data.albumArrayList)
            if (album.getUserId() == user.getId()) {
                addAlbum(album);
            }

        linearLayout.invalidate();
    }

    private void addAlbum(Album album) {
        LinearLayout llAlbum = (LinearLayout) View.inflate(MainActivity.getInstance(), R.layout.card_layout, null);
        TextView tvTitle = (TextView) llAlbum.findViewById(R.id.tv_field_CL);

        llAlbum.setTag(album.getId());
        tvTitle.setText(album.getTitle());
        linearLayout.addView(llAlbum);

        llAlbum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!EventHandler.isFragmentOnTheTop(ApiConst.ALBUM_LIST_FRAGMENT)) {
            return;
        }

        FragmentHandler.openPhotoList((int) v.getTag());
    }
}
