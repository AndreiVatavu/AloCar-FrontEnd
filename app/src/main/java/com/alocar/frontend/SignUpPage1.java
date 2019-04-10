package com.alocar.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
        startActivity(signUpPage2Intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
