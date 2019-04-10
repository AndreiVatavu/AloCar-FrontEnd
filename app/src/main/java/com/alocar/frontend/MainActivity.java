package com.alocar.frontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signUp(View view) {
        Intent signUpIntent = new Intent(this, SignUpPage1.class);
        startActivity(signUpIntent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
    }
}
