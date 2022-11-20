package ca.cmpt276.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.SharedPreferencesUtils;

public class CalculatePlayerScore extends AppCompatActivity {
    private static int numPlayers;
    private static final String EXTRA_NUM_PLAYERS = "Number of Players";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_score);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Compute Player Scores");
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferencesUtils.getConfigManagerToSharedPreferences(this);
        extractIDFromIntent();
    }

    private void extractIDFromIntent() {
        Intent intent = getIntent();
        numPlayers = intent.getIntExtra(EXTRA_NUM_PLAYERS,0);
    }

    public static Intent makeIntent(Context context, int numPlayers){
        Intent intent = new Intent(context, CalculatePlayerScore.class);
        intent.putExtra(EXTRA_NUM_PLAYERS, numPlayers);
        return intent;
    }

}
