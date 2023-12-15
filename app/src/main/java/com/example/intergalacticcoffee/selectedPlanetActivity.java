package com.example.intergalacticcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intergalacticcoffee.resources.BountyHunter;

import java.util.ArrayList;

public class selectedPlanetActivity extends AppCompatActivity {

    ArrayList<String> mCoffeeNames;

    TextView PlanetTitle;
    TextView PlanetDescription;
    TextView CoffeeName1;
    Button AddToCart1;
    TextView CoffeeName2;
    Button AddToCart2;
    Button BackButton;
    Button CartButton;

    private BountyHunter mBountyHunter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_planet);

        PlanetTitle = findViewById(R.id.PlanetTitle_textView);
        PlanetDescription = findViewById(R.id.PlanetDescription_textView);
        CoffeeName1 = findViewById(R.id.CoffeeName1_textView);
        AddToCart1 = findViewById(R.id.addToCart1_button);
        CoffeeName2 = findViewById(R.id.CoffeeName2_textView);
        AddToCart2 = findViewById(R.id.addToCart2_button);
        BackButton = findViewById(R.id.back_button);
        CartButton = findViewById(R.id.Cart_button);


        // Get the bundle
        Bundle bundle = getIntent().getExtras();
        // Extract the data…
        mCoffeeNames = bundle.getStringArrayList("coffeeNames");
        String planetName = bundle.getString("PlanetName");
        String planetDescription = bundle.getString("PlanetDescription");

        ArrayList<Integer> cart = bundle.getIntegerArrayList("cart");

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(backButton(getApplicationContext(), cart));
            }
        });

        PlanetTitle.setText(planetName);
        PlanetDescription.setText(planetDescription);
        CoffeeName1.setText(mCoffeeNames.get(0));
        CoffeeName2.setText(mCoffeeNames.get(1));

        CartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cartButton(getApplicationContext(), cart));
            }
        });

        AddToCart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.add(0);
            }
        });

        AddToCart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.add(1);
            }
        });

    }

    public static Intent backButton(Context context, ArrayList<Integer> cart) {
        Intent intent = new Intent(context, planetsActivity.class);
        Bundle cartBundle = new Bundle();
        cartBundle.putIntegerArrayList("cart", cart);
        intent.putExtras(cartBundle);
        return intent;
    }

    public static Intent cartButton(Context context, ArrayList<Integer> cart) {
        if (cart.get(0) == 2) {
            cart.remove(0);
        }

        Intent intent = new Intent(context, CartActivity.class);
        Bundle cartBundle = new Bundle();
        cartBundle.putIntegerArrayList("cart", cart);
        intent.putExtras(cartBundle);
        return intent;
    }

}