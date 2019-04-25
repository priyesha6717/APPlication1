package com.example.rajni.application1;

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

public class profile1 extends Fragment {

    TextView name, employee, email, contact, logout,contactus,aboutus;

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
        logout = (TextView) getView().findViewById(R.id.logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        name = (TextView) getView().findViewById(R.id.name);
        email = (TextView) getView().findViewById(R.id.email);
        employee = (TextView) getView().findViewById(R.id.employee);
        contact = (TextView) getView().findViewById(R.id.phone);
        contactus = (TextView) getView().findViewById(R.id.contact);
        aboutus = (TextView) getView().findViewById(R.id.abus);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), activity_contactus.class);
                startActivity(intent);
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutUs.class);
                startActivity(intent);
            }
        });
        User user = SharedPrefManager.getInstance(getActivity()).getUser();

        //setting the values to the textviews
        name.setText(user.getFirstname());
        employee.setText(user.getEmployeeID());
        email.setText(user.getEmailID());
        contact.setText(user.getContactNo());


//        getJSON("http://192.168.43.184/Android/getData.php");

    }

//    private void getJSON(final String urlWebService) {
//        class GetJSON extends AsyncTask<Void, Void, String> {
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                Log.i("keya", "post");
//                Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//
//                try {
//
//                    loadIntoListView(s);
//                    Log.i("keya", "loadIntoListView after");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                try {
//                    URL url = new URL(urlWebService);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//                    BufferedReader bufferedReader = new BufferedReader(new
//                            InputStreamReader(con.getInputStream()));
//                    String json;
//                    while ((json = bufferedReader.readLine()) != null) {
//                        sb.append(json + "\n");
//                    }
//                    return sb.toString().trim();
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//            protected Map<String,String>getParams()
//            {
//                Map<String,String> params = new HashMap<String, String>();
//
//                login1.Bean bean = new login1.Bean();
//                String value = bean.getValue();
//                Log.i("keyaaa",value);
//                params.put("EmployeeID",value);
//
//                return params;
//            }
//        }
//
//        GetJSON getJSON = new GetJSON();
//        getJSON.execute();
//    }
//
//
//    private void loadIntoListView(String json) throws JSONException {
//////        JSONArray jsonArray = new JSONArray(json);
//////        String[] registration = new String[jsonArray.length()];
//////        String[] registration1 = new String[jsonArray.length()];
//////        String[] registration2 = new String[jsonArray.length()];
//////        String[] registration3 = new String[jsonArray.length()];
//////
//////        for (int i = 0; i < jsonArray.length(); i++) {
////            JSONObject obj = new JSONObject();
//////            registration[i] = obj.getString("Firstname");
//////            registration1[i] = obj.getString("EmailID");
//////            registration2[i] = obj.getString("EmployeeID");
//////            registration3[i] = obj.getString("ContactNo");
//////            name.setText(registration[i]);
//////            email.setText(registration1[i]);
//////            employee.setText(registration2[i]);
//////            contact.setText(registration3[i]);
////        name.setText(obj.getString("Firstname"));
////        email.setText(obj.getString("EmailID"));
////        employee.setText(obj.getString("EmployeeID"));
////        contact.setText(obj.getString("ContactNo"));
//        JSONObject jsonObj = new JSONObject(json);
//        JSONArray peoples = jsonObj.getJSONArray("result");
//
//        for (int i = 0; i < peoples.length(); i++) {
//            JSONObject c = peoples.getJSONObject(i);
//
//
//            String Firstname = (c.getString("Firstname"));
//            String EmailID = (c.getString("EmailID"));
//            String EmployeeID = (c.getString("EmployeeID"));
//            String ContactNo = (c.getString("ContactNo"));
//
//            name.setText(Firstname);
//            email.setText(EmailID);
//            employee.setText(EmployeeID);
//            contact.setText(ContactNo);
//
//        }
//
//
//    }
}


