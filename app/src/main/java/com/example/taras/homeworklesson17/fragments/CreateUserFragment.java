package com.example.taras.homeworklesson17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.taras.homeworklesson17.MainActivity;
import com.example.taras.homeworklesson17.R;
import com.example.taras.homeworklesson17.api.EventHandler;

/**
 * Created by taras on 13.04.16.
 */
public class CreateUserFragment extends Fragment implements View.OnClickListener {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_user_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_register_CUL).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText etName, etUsername, etEmail, etStreet, etSuite, etCity, etZipcode, etLat, etLng, etPhone, etWebsite, etCompanyName, etCompanyCatchPhrase, etCompanyBs;

        etName = (EditText) view.findViewById(R.id.et_name_CUL);
        etUsername = (EditText) view.findViewById(R.id.et_username_CUL);
        etEmail = (EditText) view.findViewById(R.id.et_email_CUL);
        etStreet = (EditText) view.findViewById(R.id.et_street_CUL);
        etSuite = (EditText) view.findViewById(R.id.et_suite_CUL);
        etCity = (EditText) view.findViewById(R.id.et_city_CUL);
        etZipcode = (EditText) view.findViewById(R.id.et_zipcode_CUL);
        etLat = (EditText) view.findViewById(R.id.et_lat_CUL);
        etLng = (EditText) view.findViewById(R.id.et_lng_CUL);
        etPhone = (EditText) view.findViewById(R.id.et_phone_CUL);
        etWebsite = (EditText) view.findViewById(R.id.et_website_CUL);
        etCompanyName = (EditText) view.findViewById(R.id.et_company_name_CUL);
        etCompanyCatchPhrase = (EditText) view.findViewById(R.id.et_company_catch_phrase_CUL);
        etCompanyBs = (EditText) view.findViewById(R.id.et_company_bs_CUL);

        boolean isFieldsFilled = true;

        if (etName.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etUsername.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etEmail.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etStreet.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etSuite.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etCity.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etZipcode.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etLat.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etLng.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etPhone.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etWebsite.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etCompanyName.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etCompanyCatchPhrase.getText().toString().length() == 0) {
            isFieldsFilled = false;
        } else if (etCompanyBs.getText().toString().length() == 0) {
            isFieldsFilled = false;
        }

        if (!isFieldsFilled) {
            String message = MainActivity.getInstance().getString(R.string.fields_should_be_filled);
            EventHandler.showToast(message);
            return;
        }
    }
}
