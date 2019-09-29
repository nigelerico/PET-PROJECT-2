package com.sourcey.materiallogindemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sourcey.materiallogindemo.LoginActivity;
import com.sourcey.materiallogindemo.R;

import static com.sourcey.materiallogindemo.LoginActivity.*;

public class ProfileFragment extends Fragment {

    private CardView cv2;
    String nama, email;

    TextView txt_username;
    TextView txt_email;
    SharedPreferences sharedPreferences;


    public static final String my_shared_preferences = "my_shared_preferences";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        email =sharedPreferences.getString(TAG_EMAIL,null);
        nama =sharedPreferences.getString(TAG_NAMA,null);

        txt_email = (TextView) view.findViewById(R.id.txt_email);
        txt_username = (TextView) view.findViewById(R.id.txt_username);

        txt_email.setText(email);
        txt_username.setText(nama);

        cv2 = (CardView) view.findViewById(R.id.cv2);

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(TAG_NAMA, null);
                editor.putString(TAG_EMAIL, null);
                editor.commit();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        return view;
    }

}
