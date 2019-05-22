package com.alocar.frontend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alocar.frontend.R;
import com.alocar.frontend.listeners.RetrofitListener;
import com.alocar.frontend.models.ErrorObject;
import com.alocar.frontend.models.UserCredentials;
import com.alocar.frontend.recycleview.Contact;
import com.alocar.frontend.retrofit.ApiServiceProvider;
import com.alocar.frontend.retrofit.response.GenericResponse;
import com.alocar.frontend.retrofit.response.LoginResponse;
import com.alocar.frontend.retrofit.response.LoginStatusCode;
import com.alocar.frontend.util.SessionUtil;

import java.util.List;

public class MainActivity extends BaseActivity implements RetrofitListener {

    ImageView logo;
    ImageView banner;
    TextView notMember;
    TextView signUp;
    boolean doubleBackToExitPressedOnce = false;
    ApiServiceProvider apiServiceProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);
        banner = findViewById(R.id.banner);
        notMember = findViewById(R.id.already_member);
        signUp = findViewById(R.id.sign_in);
        attachKeyboardListeners(findViewById(R.id.login_screen));
        apiServiceProvider = ApiServiceProvider.getInstance(this);
    }

    @Override
    protected void onShowKeyboard(int keyboardHeight) {
        logo.setVisibility(View.GONE);
        banner.setVisibility(View.INVISIBLE);
        notMember.setVisibility(View.GONE);
        signUp.setVisibility(View.GONE);
    }

    @Override
    protected void onHideKeyboard() {
        logo.post(new Runnable() {
            @Override
            public void run() {
                logo.setVisibility(View.VISIBLE);
                banner.setVisibility(View.VISIBLE);
                notMember.setVisibility(View.VISIBLE);
                signUp.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void signUp(View view) {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
    }

    public void showPassword(View view) {
        ImageView eye = findViewById(R.id.visibility);
        EditText password = findViewById(R.id.password);

        if (password.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            eye.setImageDrawable(getDrawable(R.drawable.ic_visibility_off));
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            eye.setImageDrawable(getDrawable(R.drawable.ic_visibility));
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        password.setSelection(password.getText().length());
    }

    public void login(View view) {
        UserCredentials credentials = new UserCredentials();
        credentials.setEmail(((TextView)findViewById(R.id.email)).getText().toString());
        credentials.setPassword(((TextView)findViewById(R.id.password)).getText().toString());
        if (credentials.valid()) {
            apiServiceProvider.login(credentials, this);
        }
    }

    @Override
    public void onResponseSuccess(GenericResponse responseBody, int apiFlag) {
        if (responseBody.getCode() == LoginStatusCode.OK.getStatusCode()) {
            SessionUtil.setAuthToken(((LoginResponse)responseBody).getAuthToken());
            Intent messengerIntent = new Intent(this, MessengerActivity.class);
            startActivity(messengerIntent);
            finish();
        } else {
            TextView errorField = findViewById(R.id.errorField);
            errorField.setVisibility(View.VISIBLE);
            errorField.setText(responseBody.getMessage());
        }
    }

    @Override
    public void onResponseSuccess(List<Contact> responseBody, int apiFlag) {

    }

    @Override
    public void onResponseError(ErrorObject errorObject, Throwable throwable, int apiFlag) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }


}
