package com.example.rajni.application1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class home4 extends Fragment {

    TextView t11,t22,t33;
    Button b1,b2,b3;
    EditText e11,e22,e33;
    String EmployeeID,Password;
    private SharedPreferences mpref;
    SharedPreferences sp;
    String URL = "http://172.20.10.4/Android/getData1.php";
    JSONParser jsonParser = new JSONParser();
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home4,container,false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        t11 = (TextView) getView().findViewById(R.id.emp11);
        t22 = (TextView) getView().findViewById(R.id.emp22);
        t33 = (TextView) getView().findViewById(R.id.emp33);
        e11 = (EditText)getView().findViewById(R.id.e1);
        e22 = (EditText)getView().findViewById(R.id.e2);
        e33 = (EditText)getView().findViewById(R.id.e3);
        b1 = (Button)getView().findViewById(R.id.bb1);
        b2 = (Button)getView().findViewById(R.id.bb2);
        b3 = (Button)getView().findViewById(R.id.bb3);

        EmployeeID = e11.getText().toString().trim();
        //Password = "12345";

        t11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                t11.setVisibility(View.GONE);
                e11.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
            }
        });

        t22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                t22.setVisibility(View.GONE);
                e22.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
            }
        });

        t33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                t33.setVisibility(View.GONE);
                e33.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                home4.AttemptLogin1 attemptLogin = new home4.AttemptLogin1();
                attemptLogin.execute(EmployeeID, "");

                Intent i = new Intent(getActivity(),show11.class);
                startActivity(i);
            }
        });
    }
    private class AttemptLogin1 extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {

            String Password = args[1];
            String EmployeeID = args[0];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("EmployeeID", EmployeeID));
            params.add(new BasicNameValuePair("Password", Password));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);
            return json;

        }

        protected void onPostExecute(JSONObject obj) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {

                if (obj != null) {
                    Toast.makeText(getActivity(),obj.getString("message"),Toast.LENGTH_LONG).show();
                    JSONObject userJson = obj.getJSONObject("user");

                    User user = new User(
                            userJson.getString("Firstname"),
                            userJson.getString("Middlename"),
                            userJson.getString("Lastname"),
                            userJson.getString("Birthdate"),
                            userJson.getString("Gender"),
                            userJson.getString("EmployeeID"),
                            userJson.getString("EmailID"),
                            userJson.getString("ContactNo"),
                            userJson.getString("Password")
                    );


                    SharedPrefManager.getInstance(getActivity()).userLogin(user);
                    //starting the profile activity
                    startActivity(new Intent(getActivity(), profile_emp.class));

                }
                else{
                    Toast.makeText(getActivity(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}


