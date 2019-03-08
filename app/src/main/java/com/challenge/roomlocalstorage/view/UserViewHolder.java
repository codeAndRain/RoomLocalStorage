package com.challenge.roomlocalstorage.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.challenge.roomlocalstorage.R;
import com.challenge.roomlocalstorage.data.entities.User;

class UserViewHolder extends RecyclerView.ViewHolder {
    TextView fullNameTextView;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        fullNameTextView = itemView.findViewById(R.id.full_name_texview);
    }
    public void bind(User user) {
        if (user != null) {
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            fullNameTextView.setText(firstName.concat(" ").concat(lastName));
        }
    }
}
