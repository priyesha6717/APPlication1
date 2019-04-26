package com.example.rajni.application1;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class show1 extends Fragment {

    TextView name, employee, email, contact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile1, container, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), login.class));
        }
        name = (TextView) getView().findViewById(R.id.name);
        email = (TextView) getView().findViewById(R.id.email);
        employee = (TextView) getView().findViewById(R.id.employee);
        contact = (TextView) getView().findViewById(R.id.phone);

        User user = SharedPrefManager.getInstance(getActivity()).getUser();

        //setting the values to the textviews
        name.setText(user.getFirstname());
        employee.setText(user.getEmployeeID());
        email.setText(user.getEmailID());
        contact.setText(user.getContactNo());


//        getJSON("http://192.168.43.184/Android/getData.php");

    }
}




