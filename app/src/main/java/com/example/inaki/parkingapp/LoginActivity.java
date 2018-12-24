package com.example.inaki.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoginActivity extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_login, container, false);

        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);

        //Set an error if the password is more than 8 characters
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPasswordValid(passwordEditText.getText())){
                    passwordTextInput.setError("Password must contain at least 8 characters.");
                }else {
                    passwordTextInput.setError(null);//clear the error
                    //((NavigateInterface)getActivity()).navigateTo(new MapsActivity(),false);
                    Intent intent = new Intent(getActivity(),MapsActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });

        //  Clear the error once more than 8 characters are typed
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(isPasswordValid(passwordEditText.getText())){
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });

        // Snippet from "Navigate to the next Fragment" section goes here.

        return view;
    }

    private boolean isPasswordValid(@Nullable Editable text){
        return text != null && text.length() >= 8;
    }

}
