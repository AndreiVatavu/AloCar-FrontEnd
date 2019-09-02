package com.alocar.frontend.activities;

import android.se.omapi.Session;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alocar.frontend.R;
import com.alocar.frontend.recycleview.Chat;
import com.alocar.frontend.recycleview.Contact;
import com.alocar.frontend.recycleview.adapter.MessageAdapter;
import com.alocar.frontend.util.SessionUtil;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Message extends AppCompatActivity {

    private Toolbar toolbar;

    ImageButton btnSend;
    EditText textSend;
    MessageAdapter messageAdapter;
    List<Chat> mChat;

    RecyclerView recyclerView;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        toolbar = (Toolbar) findViewById(R.id.messageToolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final Contact contactInfo = (Contact) getIntent().getSerializableExtra("user");

        setTitle(contactInfo.getName());

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnSend = findViewById(R.id.btn_send);
        textSend = findViewById(R.id.text_send);



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = textSend.getText().toString();
                if (!msg.equals("")) {
                    sendMessage(String.valueOf(contactInfo.getUserId()), msg);
                    textSend.setText("");
                }
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Chats");

        readMessage(contactInfo.getUserId(), "https://api.androidhive.info/json/images/johnny.jpg");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendMessage(String receiver, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", SessionUtil.getUid());
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);

        reference.child("Chats").push().setValue(hashMap);
    }

    private void readMessage(final String senderId, final String imageUrl) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");
        mChat = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat == null) {
                        continue;
                    }
                    if (chat.getReceiver().equals(SessionUtil.getUid()) && chat.getSender().equals(senderId) ||
                            chat.getReceiver().equals(senderId) && chat.getSender().equals(SessionUtil.getUid())) {
                        mChat.add(chat);
                    }

                    messageAdapter = new MessageAdapter(Message.this, mChat, imageUrl);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
