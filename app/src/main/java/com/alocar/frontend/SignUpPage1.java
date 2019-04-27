package com.alocar.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.alocar.frontend.request.SignUpRequest;

public class SignUpPage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page1);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }

    public void goBack(View view) {
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }

    public void signUpPage2(View view) {
        Intent signUpPage2Intent = new Intent(this, SignUpPage2.class);
        SignUpRequest signUpRequest = new SignUpRequest.SignUpRequestBuilder()
                .withFirstName(((EditText)findViewById(R.id.first_name)).getText().toString())
                .withLastName(((EditText)findViewById(R.id.last_name)).getText().toString())
                .withEmailAddress(((EditText)findViewById(R.id.email)).getText().toString())
                .withPhoneNumber(((EditText)findViewById(R.id.phone_no)).getText().toString())
                .withPassword(((EditText)findViewById(R.id.password)).getText().toString())
                .build();
        signUpPage2Intent.putExtra("personalDetails", signUpRequest);
        startActivity(signUpPage2Intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
