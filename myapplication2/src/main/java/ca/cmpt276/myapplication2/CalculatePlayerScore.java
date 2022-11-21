package ca.cmpt276.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ca.cmpt276.myapplication2.model.SharedPreferencesUtils;

public class CalculatePlayerScore extends AppCompatActivity {
    private static int numPlayers;
    private ArrayList<Integer> PlayerScores;
    private int currPlayer = 0;
    private boolean mutex_locked=false;

    private static final String EXTRA_NUM_PLAYERS = "Number of Players";
    private static final String EXTRA_SCORES_LIST = "Scores of Players";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_score);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Compute Player Scores");
        extractIDFromIntent();

        if (PlayerScores.size() < numPlayers){
            while (PlayerScores.size() < numPlayers) {
                PlayerScores.add(0);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferencesUtils.getConfigManagerToSharedPreferences(this);
        setSaveButton();
        populatePlayerSpinner();
        setupScoreCallOnFill();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return true;
    }

    private void setSaveButton() {
        Button btn = findViewById(R.id.submitScoreBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // find a way to save the score list back to the object

                finish();
            }
        });
    }

    private int getScore(){
        int score;
        EditText Score = findViewById(R.id.editScoreTextEdit);
        String ScoreStr = Score.getText().toString();
        try {
            score = Integer.parseInt(ScoreStr);
        }catch(NumberFormatException except){
            Toast.makeText(CalculatePlayerScore.this, "Score must be an integer", Toast.LENGTH_SHORT)
                    .show();
            return 0;
        }
        return score;
    }

    private void populatePlayerSpinner(){
        ArrayList <String> players = new ArrayList<>();
        for(int i = 0 ; i < numPlayers;i++){
            players.add(String.format("Player %d",i));
        }
        Spinner spinner = findViewById(R.id.playerNumberSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                players);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mutex_locked=true;
                PlayerScores.set(currPlayer,getScore());
                if (i < numPlayers) {
                    EditText et = findViewById(R.id.editScoreTextEdit);
                    et.setText(String.format("%d",PlayerScores.get(i)));
                }
                currPlayer=i;
                mutex_locked=false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }


    private void setupScoreCallOnFill() {
        EditText et = findViewById(R.id.editScoreTextEdit);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!mutex_locked) {
                    PlayerScores.set(currPlayer, getScore());
                }
                TextView tv = findViewById(R.id.totalScoreTextValue);
                tv.setText(String.valueOf(sumPlayerScores()));
            }
        });
    }

    private int sumPlayerScores(){
        int sum=0;
        for(int i = 0; i < PlayerScores.size(); i++){
            sum+=PlayerScores.get(i);
        }
        return sum;
    }

    private void extractIDFromIntent() {
        Intent intent = getIntent();
        numPlayers = intent.getIntExtra(EXTRA_NUM_PLAYERS, 0);
        PlayerScores = intent.getIntegerArrayListExtra(EXTRA_SCORES_LIST);
    }

    public static Intent makeIntent(Context context, int numPlayers, ArrayList<Integer> scores){
        Intent intent = new Intent(context, CalculatePlayerScore.class);
        intent.putExtra(EXTRA_NUM_PLAYERS, numPlayers);
        intent.putExtra(EXTRA_SCORES_LIST, scores);
        return intent;
    }

}
