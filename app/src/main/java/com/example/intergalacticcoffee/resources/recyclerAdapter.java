package com.example.intergalacticcoffee.resources;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intergalacticcoffee.R;
import com.example.intergalacticcoffee.db.UserDAO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is for using a recyclerView.
 * @link https://www.youtube.com/watch?v=__OMnFR-wZU&t=222s
 * @version Beta
 */
public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private List<User> userList; // parameter so this is getting passed in.

    public recyclerAdapter(List<User> userList) {
        this.userList = userList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userTxt;

        public MyViewHolder(final View view){
            // This is just how it populates the display. This part should be fine.

            super(view);
            userTxt = view.findViewById(R.id.TableUsernameFromDB_textView);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String name = userList.get(position).getUsername();
        holder.userTxt.setText(name);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
