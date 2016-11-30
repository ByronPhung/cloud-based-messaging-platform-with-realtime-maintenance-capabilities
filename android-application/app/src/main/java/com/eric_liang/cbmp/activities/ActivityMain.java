/*
MIT License

Copyright (c) 2016 Eric Liang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.eric_liang.cbmp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.eric_liang.cbmp.R;
import com.eric_liang.cbmp.adapters.ContactsAdapter;
import com.eric_liang.cbmp.models.Contact;

import io.realm.Realm;
import io.realm.RealmResults;

public class ActivityMain extends AppCompatActivity {

    Realm mRealm;
    RecyclerView rvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContacts = (RecyclerView) findViewById(R.id.rv_contacts);

        //Realm Initialization
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Contact c = realm.createObject(Contact.class);
                c.setName("John");
                Contact c2 = realm.createObject(Contact.class);
                c2.setName("Eric");
            }
        });
        RealmResults<Contact> contactResults= mRealm.where(Contact.class).findAll();

        // Initialize contacts
        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(this, contactResults);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}
