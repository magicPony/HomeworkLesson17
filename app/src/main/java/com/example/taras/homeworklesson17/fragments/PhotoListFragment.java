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
import com.example.taras.homeworklesson17.api.Data;
import com.example.taras.homeworklesson17.api.models.Photo;

/**
 * Created by taras on 13.04.16.
 */
public class PhotoListFragment extends Fragment {

    private int albumId;
    private LinearLayout linearLayout;

    public PhotoListFragment(int albumId) {
        this.albumId = albumId;
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
        tvTitle.setText(MainActivity.getInstance().getString(R.string.photos));

        for (Photo photo : Data.photoArrayList)
            if (photo.getAlbumId() == albumId) {
                addPhoto(photo);
            }

        linearLayout.invalidate();
    }

    private void addPhoto(Photo photo) {
        LinearLayout llPhoto = (LinearLayout) View.inflate(MainActivity.getInstance(), R.layout.photo_layout, null);
        TextView tvTitle, tvUrl;

        tvTitle = (TextView) llPhoto.findViewById(R.id.tv_title_PL);
        tvUrl = (TextView) llPhoto.findViewById(R.id.tv_url_PL);

        tvTitle.setText(photo.getTitle());
        tvUrl.setText(photo.getUrl());

        linearLayout.addView(llPhoto);
    }
}
