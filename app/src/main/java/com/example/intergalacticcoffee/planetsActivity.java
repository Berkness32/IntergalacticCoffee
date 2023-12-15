package com.example.intergalacticcoffee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.intergalacticcoffee.db.AppDatabase;
import com.example.intergalacticcoffee.db.UserDAO;
import com.example.intergalacticcoffee.resources.BountyHunter;
import com.example.intergalacticcoffee.resources.Planet;
import com.example.intergalacticcoffee.resources.User;

import java.util.ArrayList;

public class planetsActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.intergalacticcoffee.mUserID";
    private static final String PREFENCES_KEY = "com.example.intergalacticcoffee.PREFERENCES_KEY";


    // Logout behavior ---------------------------------------------
    private int mUserId = -1;
    Button logoutButton;
    Button cartButton;
    UserDAO userDAO;
    private User mUser;
    private SharedPreferences mPreferences = null;
    // -------------------------------------------------------------

    // start of main
    // -------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);

        Planet planet = new Planet();
        BountyHunter bountyHunter = new BountyHunter();

        Spinner planetSpinner = findViewById(R.id.Planet_spinner);
        Spinner bountySpinner = findViewById(R.id.bounty_spinner);
        logoutButton = findViewById(R.id.Logout_button);
        cartButton = findViewById(R.id.Cart_button);

        getDatabase();

        Bundle cartBundle = getIntent().getExtras();
        ArrayList<Integer> cart = cartBundle.getIntegerArrayList("cart");

        // Planet Spinner ------------------------------------
        ArrayList<String> planetList = planet.getAllPlanetNames();
        planetList.add(0, "Select a planet");
        ArrayAdapter<String> planetAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, planetList);
        planetAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        planetSpinner.setAdapter(planetAdapter);

        // Bounty Spinner ------------------------------------
        ArrayList<String> bountyList = bountyHunter.getNames();
        bountyList.add(0, "Select a bounty");
        ArrayAdapter<String> bountyAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bountyList);
        bountyAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        bountySpinner.setAdapter(bountyAdapter);

        // Cart Button ------------------------------------------
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cartButton(getApplicationContext(), cart));
            }
        });

        // Spinner ++++++++++++++++++++++++++++++++++++++++++++++
        // How to Implement Spinner in Android by Codes Easy
        planetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String item = parent.getItemAtPosition(position).toString();
                    //Create the bundle
                    Bundle planetBundle = new Bundle();
                    //Add your data to bundle
                    planetBundle.putStringArrayList("coffeeNames", planet.getAllCoffeeNames());
                    planetBundle.putString("PlanetName", item); // if this doesn't work, replace item with: planet.getPlanetName(position)
                    planetBundle.putString("PlanetDescription", planet.getPlanetDescription(position - 1));

                    planetBundle.putIntegerArrayList("cart", cart);

                    Intent intent = new Intent(getApplicationContext(), selectedPlanetActivity.class);
                    startActivity(intent.putExtras(planetBundle));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bountySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String bountyItem = parent.getItemAtPosition(position).toString();
                    Bundle bountyBundle = new Bundle();
                    bountyBundle.putString("Title", bountyItem);
                    bountyBundle.putString("Description", bountyHunter.getDescription(position - 1));
                    bountyBundle.putInt("ID", position - 1);

                    bountyBundle.putIntegerArrayList("cart", cart);

                    Intent bountyIntent = new Intent(getApplicationContext(), BountySelectedActivity.class);
                    startActivity(bountyIntent.putExtras(bountyBundle));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++

        // Logging in --------------------------------------------
        loginUser(mUserId);
        addUserToPreferences(mUserId);
        // -------------------------------------------------------

        // Logout button =========================================
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        // =======================================================
    }
    // end of main
    // -------------------------------------------------------------------------------------------

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);

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

    public void getDatabase(){
        userDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDAO();
    }

    // Logout Button ----------------------------------------------------------
    private void logoutUser() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setMessage(R.string.logout);

        alertBuilder.setPositiveButton(getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearUserFromIntent();
                        clearUserFromPref();
                        mUserId = -1;
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        // checkForUser(); Since Login page is required.
                        // (which I need to change)
                    }
                });
        alertBuilder.setNegativeButton(getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //We don't really need to do anything here.

                    }
                });

        alertBuilder.create().show();

    }

    private void clearUserFromIntent() {
        getIntent().putExtra(USER_ID_KEY, -1);
    }

    private void clearUserFromPref() {
        addUserToPreference(-1);
    }

    // Logging in -----------------------------------------------
    private void addUserToPreference(int userId) {
        if (mPreferences == null) {
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, userId);
        editor.apply();
    }

    private void loginUser(int userId) {
        mUser = userDAO.getUserByID(userId);
        addUserToPreference(userId);
        invalidateOptionsMenu();
    }

    private void addUserToPreferences(int userId) {
        if (mPreferences == null) {
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, userId);
        editor.apply();
    }

    private void getPrefs() {
        mPreferences = this.getSharedPreferences(PREFENCES_KEY, Context.MODE_PRIVATE);
    }
    // ------------------------------------------------------------------------------
}