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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class registration_safetyofficer extends AppCompatActivity {
    TextView lg;
    private static final String tag = "dob";
    private EditText Birthdate, Firstname, Middlename, Lastname;
    EditText Gender;
    EditText EmployeeID, ContactNo, EmailID, Password, ConfirmPassword;
    Button submit, cancle2;
    String emailpattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String pwdpattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$";
    String contactpattern = "^(?=.*[0-9]).{9,11}";
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    String URL = "http://192.168.43.184/Android/index1.php";
    JSONParser jsonParser=new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_safetyofficer);
        Birthdate = (EditText) findViewById(R.id.Birthdate2);
        Firstname = (EditText) findViewById(R.id.Firstname2);
        Middlename = (EditText) findViewById(R.id.Middlename2);
        Lastname = (EditText) findViewById(R.id.Lastname2);
        Gender = (EditText) findViewById(R.id.Gender2);
        EmployeeID = (EditText) findViewById(R.id.EmployeeID2);
        EmailID = (EditText) findViewById(R.id.EmailID2);
        ContactNo = (EditText) findViewById(R.id.ContactNo2);
        Password = (EditText) findViewById(R.id.Password2);
        ConfirmPassword = (EditText) findViewById(R.id.ConfirmPassword2);
        cancle2 = (Button) findViewById(R.id.Cancle2);
        lg = (TextView) findViewById(R.id.Loginnow2);
//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            finish();
//            startActivity(new Intent(this, profile_emp.class));
//            return;
//        }
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
                Gender.setText("");
            }
        });
        findViewById(R.id.sub2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
//                registerUser();

                if (Firstname.getText().toString().trim().length() == 0) {
                    Firstname.setError("Enter your First Name");
                    Firstname.requestFocus();
                } else if (Middlename.getText().toString().trim().length() == 0) {
                    Middlename.setError("Enter your Middle Name");
                    Middlename.requestFocus();
                } else if (Lastname.getText().toString().trim().length() == 0) {
                    Lastname.setError("Enter your Last Name");
                    Lastname.requestFocus();
                } else if (Birthdate.getText().toString().trim().length() == 0) {
                    Birthdate.setError("Enter your BirthDate");
                    Birthdate.requestFocus();
                } else {
                    AttemptLogin2 attemptLogin = new AttemptLogin2();
                    attemptLogin.execute(Firstname.getText().toString(),Middlename.getText().toString(),Lastname.getText().toString(),Birthdate.getText().toString(),Gender.getText().toString(),
                            EmployeeID.getText().toString(),EmailID.getText().toString(),ContactNo.getText().toString(),Password.getText().toString());
//                    Intent intent = new Intent(registration_safetyofficer.this, profile_emp.class);
//                    startActivity(intent);
                }

            }
        });


    }


    private class AttemptLogin2 extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {

            String Firstname = args[0];
            String Middlename = args[1];
            String Lastname = args[2];
            String Birtdate = args[3];
            String Gender = args[4];
            String EmployeeID = args[5];
            String EmailID = args[6];
            String ContactNo = args[7];
            String Password = args[8];
            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("Firstname", Firstname));
            params.add(new BasicNameValuePair("Middlename", Middlename));
            params.add(new BasicNameValuePair("Lastname",Lastname));
            params.add(new BasicNameValuePair("Birtdate", Birtdate));
            params.add(new BasicNameValuePair("Gender", Gender));
            params.add(new BasicNameValuePair("EmployeeID",EmployeeID));
            params.add(new BasicNameValuePair("EmailID", EmailID));
            params.add(new BasicNameValuePair("ContactNo", ContactNo));
            params.add(new BasicNameValuePair("Password",Password));


            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    if (result.getString("message").equals("Successfully registered the user")) {
                        Intent intent = new Intent(registration_safetyofficer.this, profile_emp.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

}



