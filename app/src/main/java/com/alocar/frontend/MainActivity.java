package com.alocar.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    ImageView logo;
    ImageView banner;
    TextView notMember;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);
        banner = findViewById(R.id.banner);
        notMember = findViewById(R.id.not_member);
        signUp = findViewById(R.id.sign_up);
        attachKeyboardListeners(findViewById(R.id.login_screen));
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
        logo.setVisibility(View.VISIBLE);
        banner.setVisibility(View.VISIBLE);
        notMember.setVisibility(View.VISIBLE);
        signUp.setVisibility(View.VISIBLE);
    }

    public void signUp(View view) {
        Intent signUpIntent = new Intent(this, SignUpPage1.class);
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
}
