package com.example.intergalacticcoffee.resources;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intergalacticcoffee.R;

import java.util.ArrayList;

public class cartRecyclerAdapter extends RecyclerView.Adapter<cartRecyclerAdapter.MyViewHolder> {
    private ArrayList<Integer> cart; // parameter so this is getting passed in.
    private String cartCoffeeName;
    private double cartCoffeePrice;
    private Planet mPlanet;

    public cartRecyclerAdapter(ArrayList<Integer> cart) {
        this.cart = cart;
        this.mPlanet = new Planet();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView coffeeName;
        private TextView coffeePrice;

        public MyViewHolder(final View view){
            // This is just how it populates the display. This part should be fine.

            super(view);
            coffeeName = view.findViewById(R.id.CartItem_textView);
            coffeePrice = view.findViewById(R.id.itemPrice_textView);
        }
    }

    @NonNull
    @Override
    public cartRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_items, parent, false);
        return new cartRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int coffeeIndex = cart.get(position);
        cartCoffeeName = mPlanet.getCoffeeName(coffeeIndex);
        cartCoffeePrice = mPlanet.getCoffeePrice(coffeeIndex);
        holder.coffeeName.setText(cartCoffeeName);
        holder.coffeePrice.setText(cartCoffeePrice + "");
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }


}

