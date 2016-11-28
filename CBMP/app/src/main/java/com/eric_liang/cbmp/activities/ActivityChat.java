package com.eric_liang.cbmp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.eric_liang.cbmp.R;
import com.eric_liang.cbmp.adapters.MessagesAdapter;
import com.eric_liang.cbmp.models.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class ActivityChat extends AppCompatActivity {

    private EditText etMessageInput;
    private ImageButton btnSendMessage;
    private Date timestamp;
    RecyclerView rvMessages;
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        rvMessages = (RecyclerView) findViewById(R.id.rv_messages);
        etMessageInput = (EditText) findViewById(R.id.et_message_input);
        btnSendMessage = (ImageButton) findViewById(R.id.btn_send_message);

        mRealm = Realm.getDefaultInstance();

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(etMessageInput.getText().toString());
            }
        });

        RealmResults<Message> messagesResults= mRealm.where(Message.class).findAll();

        MessagesAdapter messagesAdapter = new MessagesAdapter(this, messagesResults);
        rvMessages.setAdapter(messagesAdapter);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
    }

    public void sendMessage(final String message) {
        timestamp = new Date();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Message newMessage = realm.createObject(Message.class);
                newMessage.setSender("Eric");
                newMessage.setMessage(message);
                newMessage.setTimestamp(timestamp);
            }
        });
    }
}
