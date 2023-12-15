package com.example.intergalacticcoffee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intergalacticcoffee.db.AppDatabase;
import com.example.intergalacticcoffee.db.UserDAO;
import com.example.intergalacticcoffee.resources.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.intergalacticcoffee.mUserID";
    private static final String PREFERENCES_KEY = "com.example.intergalacticcoffee.PREFERENCES_KEY";
    private int mUserID = -1;
    UserDAO mUserDAO;

    TextView mLoginTitle;

    EditText mLoginUsername;
    EditText mLoginPassword;

    private String mUsernameString;
    private String mPasswordString;

    private User mUser;

    Button mLoginSubmit;
    Button mCreateAccount;

    private ArrayList<Integer> cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginUsername = findViewById(R.id.username_editTextText);
        mLoginPassword = findViewById(R.id.password_editTextTextPassword);

        cart = new ArrayList<>();
        cart.add(2);

        getDatabase();
        // checkForUsers(); // I think this is buggy

        mCreateAccount = findViewById(R.id.createAccount_button);
        mLoginSubmit = findViewById(R.id.login_button);

        mLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getValuesFromDisplay();
                if (checkForUserInDB()) {
                    if (!validatePassword()) {
                        // Troubleshooting move
                        Toast.makeText(getApplicationContext(), mPasswordString + ": " + mUser.getPassword(), Toast.LENGTH_SHORT).show();
                    } else {
                        if (mUser.getUsername().equals("berk")) {
                            Intent intent = new Intent(MainActivity.this, tableActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(MainActivity.this, planetsActivity.class);
                            Bundle cartBundle = new Bundle();
                            cartBundle.putIntegerArrayList("cart", cart);
                            startActivity(intent.putExtras(cartBundle));
                        }
                    }
                }
            }
        });

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkForUsers() {
        // Do we have a user in the intent?     35:13 Adding users to GymLog
        mUserID = getIntent().getIntExtra(USER_ID_KEY, -1);

        // Do we have a user in the preferences?
        if (mUserID != -1){
            return;
        }
        // He fixes preferences @ ->                        1:12:00 Adding user to GymLog         !!!!
        SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        mUserID = preferences.getInt(USER_ID_KEY, -1);

        if (mUserID != -1){
            return;
        }

        // Do we have any users at all?
        List<User> users = mUserDAO.getUsers();
        if (users.size() <= 0) {
            Intent intent = CreateAccountActivity.intentFactory(this);
            startActivity(intent);
        }
    }

    public void getDatabase(){
        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDAO();
    }

    private void getValuesFromDisplay() {
        mUsernameString = mLoginUsername.getText().toString();
        mPasswordString = mLoginPassword.getText().toString();
    }

    private boolean validatePassword() {
        return (mUser.getPassword().equals(mPasswordString));
    }

    private boolean checkForUserInDB(){
        mUser = mUserDAO.getUserByUsername(mUsernameString);
        if (mUser == null) {
            Toast.makeText(this, "no user " + mUsernameString + " found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}