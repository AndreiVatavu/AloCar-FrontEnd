package com.alocar.frontend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alocar.frontend.R;
import com.alocar.frontend.listeners.RetrofitListener;
import com.alocar.frontend.models.ErrorObject;
import com.alocar.frontend.models.SignUpRequest;
import com.alocar.frontend.recycleview.Contact;
import com.alocar.frontend.retrofit.ApiServiceProvider;
import com.alocar.frontend.retrofit.response.GenericResponse;
import com.alocar.frontend.retrofit.response.SignUpStatusCode;
import com.alocar.frontend.util.Utils;

import java.util.List;

public class SignUpActivity extends BaseActivity implements RetrofitListener {

    ApiServiceProvider apiServiceProvider;
    TextView alreadyMember;
    TextView signIn;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        alreadyMember = findViewById(R.id.already_member);
        signIn = findViewById(R.id.sign_in);
        logo = findViewById(R.id.logo);
        attachKeyboardListeners(findViewById(R.id.sign_up_layout));

        apiServiceProvider = ApiServiceProvider.getInstance(this);
    }

    @Override
    protected void onShowKeyboard(int keyboardHeight) {
        alreadyMember.setVisibility(View.GONE);
        signIn.setVisibility(View.GONE);
        logo.setVisibility(View.GONE);
    }

    @Override
    protected void onHideKeyboard() {
        alreadyMember.setVisibility(View.VISIBLE);
        signIn.setVisibility(View.VISIBLE);
        logo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }

    public void goBack(View view) {
        onBackPressed();
    }

    public void showPassword(View view) {
        ImageView eye = findViewById(R.id.visibility1);
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

    public void showConfirmPassword(View view) {
        ImageView eye = findViewById(R.id.visibility2);
        EditText password = findViewById(R.id.confirm_password);

        if (password.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            eye.setImageDrawable(getDrawable(R.drawable.ic_visibility_off));
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            eye.setImageDrawable(getDrawable(R.drawable.ic_visibility));
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        password.setSelection(password.getText().length());
    }

    public void finish(View view) {
        Intent messengerIntent = new Intent(this, MessengerActivity.class);
        startActivity(messengerIntent);
        finish();
    }

    public void signUp(View view) {
        SignUpRequest signUpRequest = new SignUpRequest.SignUpRequestBuilder()
                .withFirstName(((EditText)findViewById(R.id.first_name)).getText().toString())
                .withLastName(((EditText)findViewById(R.id.last_name)).getText().toString())
                .withEmailAddress(((EditText)findViewById(R.id.email)).getText().toString())
                .withPhoneNumber(((EditText)findViewById(R.id.phone_no)).getText().toString())
                .withPassword(((EditText)findViewById(R.id.password)).getText().toString())
                .build();
        if (signUpRequest.getPassword().equals(((TextView)findViewById(R.id.confirm_password)).getText().toString())) {
            apiServiceProvider.signUp(signUpRequest, this);
        } else {
            TextView errorField = findViewById(R.id.errorField);
            errorField.setVisibility(View.VISIBLE);
            errorField.setText("Passwords don't match");
        }
    }

    @Override
    public void onResponseSuccess(GenericResponse responseBody, int apiFlag) {
        SignUpStatusCode signUpStatusCode = SignUpStatusCode.getSignUpStatusCode(responseBody.getCode());
        if (signUpStatusCode == SignUpStatusCode.OK) {
            ConstraintLayout success = findViewById(R.id.success_registration);
            ScrollView form = findViewById(R.id.form);
            ImageView welcome = findViewById(R.id.welcome);

            Utils.hideSoftKeyboard(this, getCurrentFocus());
            form.setVisibility(View.GONE);
            alreadyMember.setVisibility(View.GONE);
            signIn.setVisibility(View.GONE);

            success.setVisibility(View.VISIBLE);
            welcome.setVisibility(View.VISIBLE);
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
        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
    }
}
