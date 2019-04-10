package com.alocar.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SignUpPage2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page2);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void goBack(View view) {
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }

    public void signUpPage1(View view) {
        Intent signUpPage1Intent = new Intent(this, SignUpPage1.class);
        startActivity(signUpPage1Intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
