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

import com.android.volley.AuthFailureError;
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

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class registration_emp1 extends AppCompatActivity {
    TextView lg;
    Button next, cancle;
    private static final String tag = "dob";
    private EditText dob, fname, mname, lname;
    Spinner gender;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    String URL = "http://192.168.0.104/Android/index1.php";

    JSONParser jsonParser=new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_emp1);
        dob = (EditText) findViewById(R.id.dob);
        fname = (EditText) findViewById(R.id.fname);
        mname = (EditText) findViewById(R.id.mname);
        lname = (EditText) findViewById(R.id.lname);
        gender = (Spinner) findViewById(R.id.gender);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(registration_emp1.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                Log.d(tag, "onDataset:mm/dd/yyyy:" + i1 + "/" + i2 + "/" + i);
                String date = i1 + "/" + i2 + "/" + i;
                dob.setText(date);
            }
        };
        lg = (TextView) findViewById(R.id.login);
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(registration_emp1.this, login.class);
                startActivity(i);
            }
        });
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AttemptLogin attemptLogin= new AttemptLogin();
                attemptLogin.execute(fname.getText().toString(),mname.getText().toString(),lname.getText().toString()
                ,dob.getText().toString(),gender.toString());

                if (fname.getText().toString().trim().length() == 0) {
                    fname.setError("Enter your First Name");
                    fname.requestFocus();
                } else if (mname.getText().toString().trim().length() == 0) {
                    mname.setError("Enter your Middle Name");
                    mname.requestFocus();
                } else if (lname.getText().toString().trim().length() == 0) {
                    lname.setError("Enter your Last Name");
                    lname.requestFocus();
                } else if (dob.getText().toString().trim().length() == 0) {
                    dob.setError("Enter your BirthDate");
                    dob.requestFocus();
                } else {
                    Intent intent = new Intent(registration_emp1.this, registration_emp2.class);
                    startActivity(intent);
                }
            }
        });
        cancle = (Button) findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname.setText("");
                mname.setText("");
                lname.setText("");
                dob.setText("");
            }
        });

    }

    class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {



            String fname = args[0];
            String mname = args[1];
            String lname= args[2];
            String dob = args[3];
            String gender = args[4];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("Firstname", fname));
            params.add(new BasicNameValuePair("Middlename", mname));
            params.add(new BasicNameValuePair("Lastname", lname));
            params.add(new BasicNameValuePair("Birtdate", dob));
            params.add(new BasicNameValuePair("Gender", gender));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}
