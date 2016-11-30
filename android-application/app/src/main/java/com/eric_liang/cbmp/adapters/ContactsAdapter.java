package com.eric_liang.cbmp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eric_liang.cbmp.R;
import com.eric_liang.cbmp.activities.ActivityChat;
import com.eric_liang.cbmp.activities.ActivityMain;
import com.eric_liang.cbmp.models.Contact;

import java.util.List;

import io.realm.RealmResults;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;


/**
 * Created by Eric Liang on 11/14/2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private RealmResults<Contact> mContactItems;
    private Context context;


    public ContactsAdapter(Context context, RealmResults<Contact> contactResults) {
        mInflater = LayoutInflater.from(context);
        mContactItems = contactResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.contact, parent, false);
        context = view.getContext();
        view.setClickable(true);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityChat.class);
                context.startActivity(intent);
            }
        });
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = mContactItems.get(position);
        holder.tvContactName.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return mContactItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvContactName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContactName = (TextView) itemView.findViewById(R.id.tv_contactName);
        }
    }
}
