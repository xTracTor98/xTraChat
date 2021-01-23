package com.xtrachat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.xtrachat.R;
import com.xtrachat.model.users.Users;
import com.xtrachat.view.chats.ChatsActivity;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<Users> list;
    private Context context;

    public ContactsAdapter(List<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_contact_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Users user = list.get(position);

        holder.username.setText(user.getUserName());
        holder.desc.setText(user.getBro());

        Glide.with(context).load(user.getImageProfile()).into(holder.imageProfile);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ChatsActivity.class)
                        .putExtra("userID", user.getUserID())
                        .putExtra("userName", user.getUserName())
                        .putExtra("userProfile", user.getImageProfile()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageProfile;
        private TextView username, desc;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            imageProfile = (CircularImageView)itemView.findViewById(R.id.image_profile);
            username = (TextView)itemView.findViewById(R.id.tv_username);
            desc = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }
}
