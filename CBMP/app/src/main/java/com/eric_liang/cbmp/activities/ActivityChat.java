package com.eric_liang.cbmp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.eric_liang.cbmp.R;

import io.realm.Realm;

public class ActivityChat extends AppCompatActivity {

    private EditText etMessageInput;
    private ImageButton btnSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        etMessageInput = (EditText) findViewById(R.id.et_message_input);
        btnSendMessage = (ImageButton) findViewById(R.id.btn_send_message);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.commitTransaction();
    }
}
