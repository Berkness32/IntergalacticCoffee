package com.example.intergalacticcoffee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intergalacticcoffee.db.AppDatabase;
import com.example.intergalacticcoffee.db.UserDAO;
import com.example.intergalacticcoffee.resources.User;
import com.example.intergalacticcoffee.resources.recyclerAdapter;

import java.util.List;

/**
 * There is no confirmation that this file works yet.
 * @version Alpha
 */

public class tableActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    Button logoutButton;
    AppDatabase appDatabase;
    UserDAO userDAO;
    List<User> userList;

    // Logout behavior ---------------------------------------------
    private static final String USER_ID_KEY = "com.example.intergalacticcoffee.mUserID";
    private int mUserId = -1;
    private User mUser;
    private SharedPreferences mPreferences = null;
    private static final String PREFENCES_KEY = "com.example.intergalacticcoffee.PREFERENCES_KEY";
    // --------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        mRecyclerView = findViewById(R.id.table_recyclerView);

        logoutButton = findViewById(R.id.Logout_button);

        getDatabase();
        userList = userDAO.getUsers();
        userList.toArray();

        setAdapter();

        // Logout behavior -----------------------------------------
        loginUser(mUserId);
        addUserToPreferences(mUserId);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        // -----------------------------------------------------------
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(userList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

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

    private void addUserToPreference(int userId) {
        if (mPreferences == null) {
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, userId);
        //Thanks Yasha
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
        //Thanks Yasha
        editor.apply();
    }

    private void getPrefs() {
        mPreferences = this.getSharedPreferences(PREFENCES_KEY, Context.MODE_PRIVATE);
    }
    // ------------------------------------------------------------------------------
}

