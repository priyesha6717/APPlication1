package com.example.rajni.application1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class home4 extends Fragment {

    TextView t11,t22,t33;
    Button b1,b2,b3;
    EditText e11,e22,e33;

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
                Intent i = new Intent(getActivity(),show1.class);
                startActivity(i);
            }
        });
    }
}
