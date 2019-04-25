package com.example.rajni.application1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class profile2 extends Fragment {
    TextView set1,logout ;
    TextView name, employee, email, contact,  contactus, aboutus;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanseState){
        return inflater.inflate(R.layout.profile2,container,false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        if (!SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), login1.class));
        }

        logout = (TextView)getView().findViewById(R.id.logout1);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), login1.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        name = (TextView) getView().findViewById(R.id.name1);
        email = (TextView) getView().findViewById(R.id.email1);
        employee = (TextView) getView().findViewById(R.id.employee1);
        contact = (TextView) getView().findViewById(R.id.contact1);
        contactus = (TextView) getView().findViewById(R.id.contactus1);
        aboutus = (TextView) getView().findViewById(R.id.abus1);
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


    }
}
