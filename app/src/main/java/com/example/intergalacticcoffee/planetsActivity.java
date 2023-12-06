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
import android.widget.Button;
import android.widget.Spinner;

import com.example.intergalacticcoffee.db.AppDatabase;
import com.example.intergalacticcoffee.db.UserDAO;
import com.example.intergalacticcoffee.resources.User;

public class planetsActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.intergalacticcoffee.mUserID";
    private static final String PREFENCES_KEY = "com.example.intergalacticcoffee.PREFERENCES_KEY";


    // Logout behavior ---------------------------------------------
    private int mUserId = -1;
    Button logoutButton;
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

        Spinner spinner = findViewById(R.id.Planet_spinner);
        logoutButton = findViewById(R.id.Logout_button);

        getDatabase();

        // Spinner ++++++++++++++++++++++++++++++++++++++++++++++
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        })
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