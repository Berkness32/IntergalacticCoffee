package com.example.intergalacticcoffee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intergalacticcoffee.resources.cartRecyclerAdapter;
import com.example.intergalacticcoffee.resources.recyclerAdapter;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Integer> cart;
    private Button BackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mRecyclerView = findViewById(R.id.Cart_recyclerView);
        BackButton = findViewById(R.id.back_button);

        Bundle cartBundle = getIntent().getExtras();
        cart = cartBundle.getIntegerArrayList("cart");

        setAdapter();

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(backButton(getApplicationContext(), cart));
            }
        });
    }

    private void setAdapter() {
        cartRecyclerAdapter adapter = new cartRecyclerAdapter(cart);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    public static Intent backButton(Context context, ArrayList<Integer> cart) {
        Intent intent = new Intent(context, planetsActivity.class);
        Bundle cartBundle = new Bundle();
        cartBundle.putIntegerArrayList("cart", cart);
        intent.putExtras(cartBundle);
        return intent;
    }
}