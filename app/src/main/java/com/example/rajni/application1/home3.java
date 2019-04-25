package com.example.rajni.application1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class home3 extends Fragment {
    String Hemoglobin1;
    String Heartbit1;
    String Cholesterol1;
    String Sugarlevel1;
    String BPup1;
    String EmployeeID1;
    String BPdown1;
    String Bloodgroup1;
    String weight1;
    String Height1;
    EditText Hemoglobin, Heartbit, Cholesterol, Sugarlevel,Weight,Height;
    EditText BPup, EmployeeID, BPdown ;
    Spinner Bloodgroup;
    Button update;
    RequestQueue requestQueue;
    String URL = "http://192.168.43.184/Android/healthdata.php";
    JSONParser jsonParser=new JSONParser();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanseState){
        return inflater.inflate(R.layout.home3,container,false);

    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EmployeeID = (EditText) getActivity().findViewById(R.id.safetyemployee);
         Weight= (EditText) getActivity().findViewById(R.id.weight);
        Height = (EditText) getActivity().findViewById(R.id.height);
        Hemoglobin = (EditText) getActivity().findViewById(R.id.hemo2);
        Heartbit = (EditText) getActivity().findViewById(R.id.heart2);
        Cholesterol = (EditText) getActivity().findViewById(R.id.choles2);
        Sugarlevel = (EditText) getActivity().findViewById(R.id.suger2);
        BPup = (EditText) getActivity().findViewById(R.id.bpup2);
        BPdown = (EditText) getActivity().findViewById(R.id.bpdown2);
        Bloodgroup = (Spinner) getActivity().findViewById(R.id.blood);
        update = (Button) getActivity().findViewById(R.id.updaterecord);
       getActivity().findViewById(R.id.updaterecord).setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(getActivity(), ServerResponse,
                                        Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                // Hiding the progress dialog after all task complete.

                                // Showing error message if something goes wrong.
                                Toast.makeText(getActivity(),
                                        volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();
                        // Adding All values to Params.
                        params.put("EmployeeID", EmployeeID1);
                        params.put("Weight", weight1);
                        params.put("Height", Height1);
                        params.put("Hemoglobin", Hemoglobin1);
                        params.put("Heartbit", Heartbit1);
                        params.put("Cholesterol", Cholesterol1);
                        params.put("Sugarlevel", Sugarlevel1);
                        params.put("BPup", BPup1);
                        params.put("BPdown", BPdown1);
                        params.put("Bloodgroup", Bloodgroup1);
                        return params;
                    }
                };
                // Creating RequestQueue.
                RequestQueue requestQueue =
                        Volley.newRequestQueue(getActivity());
                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);

            }


        });


    }

    public void GetValueFromEditText(){
        EmployeeID1 = EmployeeID.getText().toString().trim();
        weight1 = Weight.getText().toString().trim();
        Height1 = Height.toString().trim();
        Hemoglobin1 = Hemoglobin.getText().toString().trim();
        Heartbit1 = Heartbit.getText().toString().trim();
        Cholesterol1 = Cholesterol.getText().toString().trim();
        Sugarlevel1 = Sugarlevel.getText().toString().trim();
        BPup1 = BPup.getText().toString().trim();
        BPdown1 = BPdown.getText().toString().trim();
        Bloodgroup1 = Bloodgroup.getSelectedItem().toString().trim();

    }
}


