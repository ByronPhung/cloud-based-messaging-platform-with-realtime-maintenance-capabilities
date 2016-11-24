package com.eric_liang.cbmp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eric_liang.cbmp.R;
import com.eric_liang.cbmp.models.Contact;

import java.util.List;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;


/**
 * Created by Eric Liang on 11/14/2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvContactName;

        //Setup the contact list view items gui
        public ViewHolder(View itemView) {
            super(itemView);

            tvContactName = (TextView) itemView.findViewById(R.id.tv_contactName);
        }

        //Store contacts in a list
        private List<Contact> mContacts;
        private Context mContext;

        // Pass in the contact array into the constructor
        public ContactsAdapter(Context context, List<Contact> contacts) {
            mContacts = contacts;
            mContext = context;
        }

        // Easy access to the context object in the recyclerview
        private Context getContext() {
            return mContext;
        }

        // Usually involves inflating a layout from XML and returning the holder
        @Override
        public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.contact, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, int position) {
            // Get the data model based on position
            Contact contact = mContacts.get(position);

            // Set item views based on your views and data model
            TextView textView = viewHolder.tvContactName;
            textView.setText(contact.getName());
        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return mContacts.size();
        }

    }
}
