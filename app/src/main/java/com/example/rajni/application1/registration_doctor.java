package com.example.rajni.application1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class registration_doctor extends AppCompatActivity {
    TextView lg;
    private static final String tag = "dob";
    private EditText Birthdate, Firstname, Middlename, Lastname;

    EditText Gender;
    EditText EmployeeID, ContactNo, EmailID, Password, ConfirmPassword;
    Button submit, cancle2;
    RequestQueue requestQueue;
    String Firstname1;
    String Middlename1;
            String Lastname1;
            String Birtdate1;
            String Gender1;
            String EmployeeID1;
            String EmailID1 ;
            String ContactNo1;
            String Password1;
    String emailpattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String pwdpattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$";
    String contactpattern = "^(?=.*[0-9]).{9,11}";
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    String URL = "http://192.168.43.184/Android/setData1.php";
    JSONParser jsonParser = new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_doctor);
        Birthdate = (EditText) findViewById(R.id.Birthdate1);
        Firstname = (EditText) findViewById(R.id.Firstname1);
        Middlename = (EditText) findViewById(R.id.Middlename1);
        Lastname = (EditText) findViewById(R.id.Lastname1);
        Gender = (EditText) findViewById(R.id.Gender1);
        EmployeeID = (EditText) findViewById(R.id.EmployeeID1);
        EmailID = (EditText) findViewById(R.id.EmailID1);
        ContactNo = (EditText) findViewById(R.id.ContactNo1);
        Password = (EditText) findViewById(R.id.Password1);
        ConfirmPassword = (EditText) findViewById(R.id.ConfirmPassword1);
        cancle2 = (Button) findViewById(R.id.Cancle1);

        lg = (TextView) findViewById(R.id.Loginnow1);
        requestQueue = Volley.newRequestQueue(registration_doctor.this);

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent i = new Intent(registration_doctor.this, login1.class);
                startActivity(i);
            }
        });
        Birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(registration_doctor.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                Log.d(tag, "onDataset:yyyy/mm/dd:" + i + "/" + i1 + "/" + i2);
                String date = i + "/" + i1 + "/" + i2;
                Birthdate.setText(date);
            }
        };
        cancle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmailID.setText("");
                EmployeeID.setText("");
                ContactNo.setText("");
                Password.setText("");
                ConfirmPassword.setText("");
                Firstname.setText("");
                Middlename.setText("");
                Lastname.setText("");
                Birthdate.setText("");
                Gender.setText("");
            }
        });

        findViewById(R.id.sub1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetValueFromEditText();
                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                // Hiding the progress dialog after all task complete.

                                // Showing response message coming from server.
                                Toast.makeText(registration_doctor.this, ServerResponse,
                                        Toast.LENGTH_LONG).show();
                                Intent i = new Intent(registration_doctor.this, profile_doctor.class);
                                startActivity(i);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                // Hiding the progress dialog after all task complete.

                                // Showing error message if something goes wrong.
                                Toast.makeText(registration_doctor.this,
                                        volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();
                        // Adding All values to Params.
                        params.put("Firstname", Firstname1);
                        params.put("Middlename", Middlename1);
                        params.put("Lastname", Lastname1);
                        params.put("Birthdate", Birtdate1);
                        params.put("Gender", Gender1);
                        params.put("EmployeeID", EmployeeID1);
                        params.put("EmailID", EmailID1);
                        params.put("ContactNo", ContactNo1);
                        params.put("Password", Password1);
                        return params;
                    }
                };
                // Creating RequestQueue.
                RequestQueue requestQueue =
                        Volley.newRequestQueue(registration_doctor.this);
                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);

                }


        });


    }
    public void GetValueFromEditText(){

          Firstname1 = Firstname.getText().toString().trim();
          Middlename1 = Middlename.getText().toString().trim();
          Lastname1 = Lastname.getText().toString().trim();
          Birtdate1 = Birthdate.getText().toString().trim();
          Gender1 = Gender.getText().toString().trim();
          EmployeeID1 = EmployeeID.getText().toString().trim();
          EmailID1 = EmailID.getText().toString().trim();
          ContactNo1 = ContactNo.getText().toString().trim();
          Password1 = Password.getText().toString().trim();

    }
}



//    private class AttemptLogin1 extends AsyncTask<String, String, JSONObject> {
//
//        @Override
//
//        protected void onPreExecute() {
//
//            super.onPreExecute();
//
//        }
//
//        @Override
//
//        protected JSONObject doInBackground(String... args) {
//
//            String Firstname = args[0];
//            String Middlename = args[1];
//            String Lastname = args[2];
//            String Birtdate = args[3];
//            String Gender = args[4];
//            String EmployeeID = args[5];
//            String EmailID = args[6];
//            String ContactNo = args[7];
//            String Password = args[8];
//            ArrayList params = new ArrayList();
//            params.add(new BasicNameValuePair("Firstname", Firstname));
//            params.add(new BasicNameValuePair("Middlename", Middlename));
//            params.add(new BasicNameValuePair("Lastname", Lastname));
//            params.add(new BasicNameValuePair("Birtdate", Birtdate));
//            params.add(new BasicNameValuePair("Gender", Gender));
//            params.add(new BasicNameValuePair("EmployeeID", EmployeeID));
//            params.add(new BasicNameValuePair("EmailID", EmailID));
//            params.add(new BasicNameValuePair("ContactNo", ContactNo));
//            params.add(new BasicNameValuePair("Password", Password));
//
//
//            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);
//
//
//            return json;
//
//        }
//
//        protected void onPostExecute(JSONObject result) {
//
//            // dismiss the dialog once product deleted
//            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
//
//            try {
//                if (result != null) {
//                    if (result.getString("message").equals("Successfully registered the user")) {
//                        Intent intent = new Intent(registration_doctor.this, profile_doctor.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }






