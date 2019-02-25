package com.example.rajni.application1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class registration_emp2 extends AppCompatActivity {
    EditText employeeid,contactno,emailid,password2,cpassword2;
    Button submit,cancle2;
    String emailpattern="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String pwdpattern= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$";
    String contactpattern = "^(?=.*[0-9]).{9,11}";

    String URL = "http://192.168.0.104/Android/index1.php";

    JSONParser jsonParser=new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_emp2);
        employeeid=(EditText)findViewById(R.id.eid);
        emailid=(EditText)findViewById(R.id.email);
        contactno=(EditText)findViewById(R.id.phnno);
        password2=(EditText)findViewById(R.id.password);
        cpassword2=(EditText)findViewById(R.id.cpassword);
        submit=(Button) findViewById(R.id.submit);
        cancle2=(Button) findViewById(R.id.cancle2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AttemptLogin1 attemptLogin= new AttemptLogin1();
                attemptLogin.execute(employeeid.getText().toString(),emailid.getText().toString(),contactno.getText().toString()
                        ,password2.getText().toString());

                if(employeeid.getText().toString().trim().length()==0){
                    employeeid.setError("Enter your ID");
                    employeeid.requestFocus();
                }
                else if(emailid.getText().toString().trim().length()==0  ){
                    emailid.setError("Enter your EmailID");
                    emailid.requestFocus();
                }
               else if(!emailid.getText().toString().trim().matches(emailpattern)){
                    emailid.setError("InValid EmailID");
                    emailid.requestFocus();
                }
               else if(contactno.getText().toString().trim().length()==0){
                    contactno.setError("Enter your Contact No.");
                    contactno.requestFocus();
                }
                else if(!contactno.getText().toString().trim().matches(contactpattern)){
                    contactno.setError("InValid Contact No.");
                    contactno.requestFocus();
                }
               else if(password2.getText().toString().trim().length()==0){
                    password2.setError("Enter your Password");
                    password2.requestFocus();
                }
                else if(!password2.getText().toString().trim().matches(pwdpattern)){
                    password2.setError("InValid Password");
                    password2.requestFocus();
                }
               else if(cpassword2.getText().toString().trim().length()==0){
                    cpassword2.setError("Confirm your Password");
                    cpassword2.requestFocus();
                }
                else if(!cpassword2.getText().toString().trim().matches(String.valueOf(password2)) ){
                    cpassword2.setError("Password not matching");
                    cpassword2.requestFocus();
                }

                else {
                    Intent intent=new Intent(registration_emp2.this,profile_emp.class);
                    startActivity(intent);
                }
            }
        });
        cancle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailid.setText("");
                employeeid.setText("");
                contactno.setText("");
                password2.setText("");
                cpassword2.setText("");
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

            String eid = args[0];
            String email = args[1];
            String contact= args[2];
            String pass = args[3];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("EmployeeID", eid));
            params.add(new BasicNameValuePair("EmailID", email));
            params.add(new BasicNameValuePair("Contact No.", contact));
            params.add(new BasicNameValuePair("Password", pass));

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
