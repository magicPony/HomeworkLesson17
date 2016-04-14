package com.example.taras.homeworklesson17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taras.homeworklesson17.MainActivity;
import com.example.taras.homeworklesson17.R;
import com.example.taras.homeworklesson17.api.ApiConst;
import com.example.taras.homeworklesson17.api.Data;
import com.example.taras.homeworklesson17.api.models.User;

public class ShowUserFragment extends Fragment implements View.OnClickListener {

    private MainActivity mainActivity;
    private User user;

    public ShowUserFragment(MainActivity mainActivity, int userId) {
        this.mainActivity = mainActivity;
        user = Data.findUserById(userId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvName, tvUsername, tvEmail, tvPhone, tvWebsite, tvStreet, tvSuite, tvCity, tvZipcode, tvLat, tvLng, tvCompanyName, tvCatchPhrase, tvBs;

        tvName = (TextView) view.findViewById(R.id.tv_name_FSU);
        tvUsername = (TextView) view.findViewById(R.id.tv_username_FSU);
        tvEmail = (TextView) view.findViewById(R.id.tv_email_FSU);
        tvPhone = (TextView) view.findViewById(R.id.tv_phone_FSU);
        tvWebsite = (TextView) view.findViewById(R.id.tv_website_FSU);
        tvStreet = (TextView) view.findViewById(R.id.tv_street_FSU);
        tvSuite = (TextView) view.findViewById(R.id.tv_suite_FSU);
        tvCity = (TextView) view.findViewById(R.id.tv_city_FSU);
        tvZipcode = (TextView) view.findViewById(R.id.tv_zipcode_FSU);
        tvLat = (TextView) view.findViewById(R.id.tv_lat_FSU);
        tvLng = (TextView) view.findViewById(R.id.tv_lng_FSU);
        tvCompanyName = (TextView) view.findViewById(R.id.tv_company_name_FSU);
        tvCatchPhrase = (TextView) view.findViewById(R.id.tv_catch_phrase_FSU);
        tvBs = (TextView) view.findViewById(R.id.tv_bs_FSU);

        tvName.setText(user.getName());
        tvUsername.setText(user.getUsername());
        tvEmail.setText(user.getEmail());
        tvPhone.setText(user.getPhone());
        tvWebsite.setText(user.getWebsite());
        tvStreet.setText(user.getStreet());
        tvSuite.setText(user.getSuite());
        tvCity.setText(user.getCity());
        tvZipcode.setText(user.getZipcode());
        tvLat.setText(user.getLat());
        tvLng.setText(user.getLng());
        tvCompanyName.setText(user.getCompanyName());
        tvCatchPhrase.setText(user.getCatchPhrase());
        tvBs.setText(user.getBs());

        view.findViewById(R.id.btn_todos_FSU).setOnClickListener(this);
        view.findViewById(R.id.btn_albums_FSU).setOnClickListener(this);
        view.findViewById(R.id.btn_posts_FSU).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!mainActivity.isFragmentOnTheTop(ApiConst.SHOW_USER_FRAGMENT)) {
            return;
        }

        switch (v.getId()) {
            case R.id.btn_todos_FSU :
                mainActivity.openTodoList(user);
                break;

            case R.id.btn_albums_FSU :
                mainActivity.openAlbumList(user);
                break;

            case R.id.btn_posts_FSU :
                mainActivity.openPostList(user);
                break;
        }
    }
}
