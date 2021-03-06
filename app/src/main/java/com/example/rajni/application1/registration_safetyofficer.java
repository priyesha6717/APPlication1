package com.example.rajni.application1;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.Spinner;
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

public class registration_safetyofficer extends AppCompatActivity {
    TextView lg;
    private static final String tag = "dob";
    private EditText Birthdate, Firstname, Middlename, Lastname;
    Spinner Gender;
    EditText EmployeeID, ContactNo, EmailID, Password, ConfirmPassword;
    Button submit, cancle2;
    String emailpattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String pwdpattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$";
    String contactpattern = "^(?=.*[0-9]).{9,11}";
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
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    String URL = "http://192.168.43.184/Android/setData1.php";
    JSONParser jsonParser=new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_safetyofficer);
        Birthdate = (EditText) findViewById(R.id.Birthdate2);
        Firstname = (EditText) findViewById(R.id.Firstname2);
        Middlename = (EditText) findViewById(R.id.Middlename2);
        Lastname = (EditText) findViewById(R.id.Lastname2);
        Gender = (Spinner) findViewById(R.id.Gender2);
        EmployeeID = (EditText) findViewById(R.id.EmployeeID2);
        EmailID = (EditText) findViewById(R.id.EmailID2);
        ContactNo = (EditText) findViewById(R.id.ContactNo2);
        Password = (EditText) findViewById(R.id.Password2);
        ConfirmPassword = (EditText) findViewById(R.id.ConfirmPassword2);
        cancle2 = (Button) findViewById(R.id.Cancle2);
        lg = (TextView) findViewById(R.id.Loginnow2);
        requestQueue = Volley.newRequestQueue(registration_safetyofficer.this);
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent i = new Intent(registration_safetyofficer.this, login2.class);
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
                DatePickerDialog dialog = new DatePickerDialog(registration_safetyofficer.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
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

            }
        });
        findViewById(R.id.sub2).setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(registration_safetyofficer.this, ServerResponse,
                                        Toast.LENGTH_LONG).show();
                                Intent i = new Intent(registration_safetyofficer.this, profile_safetyofficer.class);
                                startActivity(i);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                // Hiding the progress dialog after all task complete.

                                // Showing error message if something goes wrong.
                                Toast.makeText(registration_safetyofficer.this,
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
                        Volley.newRequestQueue(registration_safetyofficer.this);
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
        Gender1 = Gender.getSelectedItem().toString().trim();
        EmployeeID1 = EmployeeID.getText().toString().trim();
        EmailID1 = EmailID.getText().toString().trim();
        ContactNo1 = ContactNo.getText().toString().trim();
        Password1 = Password.getText().toString().trim();

    }

}



