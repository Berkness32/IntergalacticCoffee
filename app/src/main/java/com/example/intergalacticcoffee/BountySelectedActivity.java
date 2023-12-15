package com.example.intergalacticcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.intergalacticcoffee.resources.BountyHunter;

public class BountySelectedActivity extends AppCompatActivity {

    TextView BountyTitle;
    TextView BountyDescription;
    TextView UserResult;
    TextView EnemyRoll;
    TextView EnemyResult;
    TextView BooleanResult;
    Button BackButton;
    BountyHunter Bounty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounty_selected);

        BountyTitle = findViewById(R.id.BountyTitle_textView);
        BountyDescription = findViewById(R.id.BountyDescription_textView);
        UserResult = findViewById(R.id.UserResult_textView);
        EnemyRoll = findViewById(R.id.EnemyRoll_textView);
        EnemyResult = findViewById(R.id.EnemyResult_textView);
        BooleanResult = findViewById(R.id.Boolean_textView);

        BackButton = findViewById(R.id.back_button);

        Bounty = new BountyHunter();

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(backButton(getApplicationContext()));
            }
        });

        Bundle bundle = getIntent().getExtras();
        String bountyTitle = bundle.getString("Title");
        String bountyDescription = bundle.getString("Description");
        int id = bundle.getInt("ID");
        int diceResult = Bounty.rollDice();

        BountyTitle.setText(bountyTitle);
        BountyDescription.setText(bountyDescription);
        UserResult.setText(diceResult + ""); // Might need to be toString();
        EnemyRoll.setText(bountyTitle);
        EnemyResult.setText(Bounty.getDifficulty(id) + ""); // Might need to be toString();
        BooleanResult.setText(Bounty.isSuccess(diceResult, id));
    }

    public static Intent backButton(Context context) {
        Intent intent = new Intent(context, planetsActivity.class);
        return intent;
    }
}