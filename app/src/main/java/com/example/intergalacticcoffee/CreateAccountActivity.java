package com.example.intergalacticcoffee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.intergalacticcoffee.db.AppDatabase;
import com.example.intergalacticcoffee.db.UserDAO;
import com.example.intergalacticcoffee.resources.User;

import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.intergalacticcoffee.resources.mUserID";
    private int mUserID = -1;

    TextView caTitle;

    EditText caUsername;
    EditText caPassword;
    RadioButton caRebel;
    RadioButton caEmpire;
    Button caSubmit;
    TextView caDisplay;

    UserDAO caUserDAO;

    List<User> caUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        caUsername = findViewById(R.id.username_editTextText);
        caPassword = findViewById(R.id.password_editTextTextPassword);
        caRebel = findViewById(R.id.Rebel_RadioButton);
        caEmpire = findViewById(R.id.Empire_RadioButton);
        caSubmit = findViewById(R.id.createAccount_button);

        getDatabase();

        caSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = getValuesFromDisplay();
                caUserDAO.insert(user);

                Intent intent = new Intent(CreateAccountActivity.this, planetsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDatabase() {
        caUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDAO();
    }

    private User getValuesFromDisplay() {
        String username = "No User";
        String password = "Empty";
        boolean rebel = false;

        username = caUsername.getText().toString();
        password = caPassword.getText().toString();
        if (caRebel.isActivated()) {
            rebel = true;
        }

        User user = new User(username, password, rebel);
        return user;
    }

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

}