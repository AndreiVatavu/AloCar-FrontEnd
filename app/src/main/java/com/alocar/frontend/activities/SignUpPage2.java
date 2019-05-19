package com.alocar.frontend.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alocar.frontend.R;
import com.alocar.frontend.models.SignUpRequest;

import org.springframework.web.client.RestTemplate;


@Deprecated
public class SignUpPage2 extends AppCompatActivity {

    SignUpRequest signUpRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page2);
        signUpRequest = (SignUpRequest) getIntent().getExtras().getSerializable("personalDetails");
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

    public void signUp(View view) {

        new SignUpRequestTask(getApplicationContext()).execute();
    }

    class SignUpRequestTask extends AsyncTask<String, Void, Boolean> {

        private Context context;

        public SignUpRequestTask(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject("http://34.73.59.221:8080/signup", signUpRequest, String.class);
            return  response.equals("\"OK\"");
        }

        @Override
        protected void onPostExecute(Boolean ok) {
            super.onPostExecute(ok);
            if (ok) {
                Intent signUpPage2Intent = new Intent(context, MainActivity.class);
                context.startActivity(signUpPage2Intent);
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
