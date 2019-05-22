package com.alocar.frontend.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.alocar.frontend.R;
import com.alocar.frontend.recycleview.Contact;

public class Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Contact contactInfo = (Contact) getIntent().getSerializableExtra("user");
        TextView editText = findViewById(R.id.textView2);
        editText.setText(contactInfo.getUserId());
    }
}
