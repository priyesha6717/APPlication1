package com.example.rajni.application1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class login3 extends AppCompatActivity implements TextWatcher,CompoundButton.OnCheckedChangeListener {
    EditText EmployeeID, Password;
    Button login;
    TextView reg,fpwd;
    Spanned text;
    CheckBox cb;
    private SharedPreferences mpref;
    SharedPreferences sp;
    private static final String PREF_Name="prefsfile";
    SharedPreferences.Editor editor;
    String URL = "http://192.168.43.184/Android/getData1.php";

    JSONParser jsonParser = new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        EmployeeID = (EditText)findViewById(R.id.uid3);
        Password = (EditText)findViewById(R.id.pwd3);
        cb = (CheckBox)findViewById(R.id.rm3);
        mpref = getSharedPreferences(PREF_Name,MODE_PRIVATE);
        editor=mpref.edit();
        if(mpref.getBoolean("pref_check",false))
            cb.setChecked(true);
        else
            cb.setChecked(false);
        EmployeeID.setText(mpref.getString("pref_name",""));
        Password.setText(mpref.getString("pref_pwd",""));
        EmployeeID.addTextChangedListener( this);
        Password.addTextChangedListener(this);
        cb.setOnCheckedChangeListener( this);
        sp = getSharedPreferences("login",MODE_PRIVATE);
//        if(sp.getBoolean("logged",false)){
//            goToActiviy();
//        }
        login = (Button)findViewById(R.id.login3);
        reg = (TextView)findViewById(R.id.reg3);
        fpwd = (TextView)findViewById(R.id.fpwd3);


        text= Html.fromHtml("<a href='https://www.google.co.in//'>Forget your password?</a>");
        fpwd.setMovementMethod(LinkMovementMethod.getInstance());
        fpwd.setText(text);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(login3.this,registration_admin.class);
                startActivity(i3);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(EmployeeID.getText().toString().trim().length()==0){
                    EmployeeID.setError("Enter your userID");
                    EmployeeID.requestFocus();
                }
                if(Password.getText().toString().trim().length()==0){
                    Password.setError("Enter your password");
                    Password.requestFocus();
                }
                else{
                    AttemptLogin_3 attemptLogin = new AttemptLogin_3();
                    attemptLogin.execute(EmployeeID.getText().toString(), Password.getText().toString(), "");
//                    Intent intent1=new Intent(login1.this,profile_doctor.class);
//                    startActivity(intent1);
//                    sp.edit().putBoolean("logged",true).apply();
                }
                Intent intent1=new Intent(login3.this,profile_admin1.class);
                startActivity(intent1);
            }
        });
    }
//    public void goToActiviy(){
//        Intent i = new Intent(this,profile_doctor.class);
//        startActivity(i);
//    }
    private class AttemptLogin_3 extends AsyncTask<String, String, JSONObject> {

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

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    JSONObject userJson = result.getJSONObject("user");

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


                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                    //starting the profile activity
                    startActivity(new Intent(getApplicationContext(), profile_admin1.class));

                }
                else{
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        abc();;
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        abc();
    }
    private void abc(){
        if(cb.isChecked()){
            editor.putString("pref_name",EmployeeID.getText().toString().trim());
            editor.putString("pref_pwd",Password.getText().toString().trim());
            editor.putBoolean("pref_check",true);
            editor.apply();
//            Toast.makeText(getApplicationContext(),"Setting have been saved",Toast.LENGTH_SHORT).show();

        }else{
            editor.remove("pref_name");
            editor.remove("pref_pwd");
            editor.putBoolean("pref_check",false);
            editor.apply();

        }
    }
}
