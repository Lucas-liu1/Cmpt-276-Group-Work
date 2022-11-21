package ca.cmpt276.myapplication2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import ca.cmpt276.myapplication2.model.AchievementList;
import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Game;
import ca.cmpt276.myapplication2.model.SharedPreferencesUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Extension of the game plays class
 * This class provides the functionality of editing an existing game play.
 * Drawing from the models provided able to take in a game config to give out results of
 * the game played with the inputted scores drawing back to the achievement list manager
 */

public class EditGamePlay extends AppCompatActivity {
    private static final String EXTRA_GAME = "Game";
    private static final String EXTRA_CONFIG = "Configuration";
    private ConfigManager GameConfiguration;
    private int configurationID; // the game config ID
    private static int gameID; // the game ID
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_addplay);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Edit Game Play");
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameConfiguration = ConfigManager.getInstance();
        SharedPreferencesUtils.getConfigManagerToSharedPreferences(this);
        extractIDFromIntent();
        populateSpinner();
        populateDifficultySpinner();
        setSaveButton();
        setCalculateButton();
        populateFields();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        //menu inflator
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                attemptDelete();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void attemptDelete(){ // popup message to confirm deletion of game
        GameConfiguration = ConfigManager.getInstance();
        AlertDialog.Builder Message = new AlertDialog.Builder(this); // create the builder for the popup box

        Message.setTitle("Delete Game");
        Message.setMessage("Please Confirm Delete");
        Message.setCancelable(true);
        Message.setPositiveButton("Delete",         // for confirming delete
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameConfiguration.getConfigList().get(configurationID)
                                        .deleteGameById(gameID);
                        SharedPreferencesUtils.storeConfigManagerToSharedPreferences(EditGamePlay.this);
                        finish();
                    }
                });
        Message.setNegativeButton(android.R.string.cancel, // for cancelling delete
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog popup = Message.create();
        popup.show();
    }

    private void setSaveButton() {
        Button btn = findViewById(R.id.createButton);
        btn.setText("Save");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateGamePlay();
            }
        });
    }

    private void setCalculateButton() {
        Button btn = findViewById(R.id.calculateScoreBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getNumPlayers()>0) {
                    Intent intent = CalculatePlayerScore.makeIntent(EditGamePlay.this, getNumPlayers());
                    startActivity(intent);
                }else{
                    Toast.makeText(EditGamePlay.this,
                            "Please Fill in Valid Number of Players",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getNumPlayers(){
        EditText numPlayers = findViewById(R.id.numPlayersTextEdit);
        String numPlayersStr = numPlayers.getText().toString();
        int num_players;
        try {
            num_players = Integer.parseInt(numPlayersStr);
        }catch(NumberFormatException except){
            Toast.makeText(EditGamePlay.this, "Number of Players must be an integer", Toast.LENGTH_SHORT)
                    .show();
            return 0;
        }
        return num_players;
    }

    private int getSumScore(){
        int sum_score;
        EditText totalScore = findViewById(R.id.scoreTextEdit);
        String totalScoreStr = totalScore.getText().toString();
        try {
            sum_score = Integer.parseInt(totalScoreStr);
        }catch(NumberFormatException except){
            Toast.makeText(EditGamePlay.this, "Score must be an integer", Toast.LENGTH_SHORT)
                    .show();
            return 0;
        }
        return sum_score;
    }

    private void extractIDFromIntent() {
        Intent intent = getIntent();
        configurationID = intent.getIntExtra(EXTRA_CONFIG,0);
        gameID = intent.getIntExtra(EXTRA_GAME,0);
    }

    public static Intent makeIntent(Context context, int configID,int gameID){
        Intent intent = new Intent(context, EditGamePlay.class);
        intent.putExtra(EXTRA_CONFIG, configID);
        intent.putExtra(EXTRA_GAME,gameID);
        return intent;
    }

    private void populateDifficultySpinner(){
        difficulty = GameConfiguration.getConfigList().get(configurationID).getGamesList().get(gameID).getDifficulty();
        Spinner spinner = findViewById(R.id.difficultyLevelSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                GameConfiguration.getDifficultyLevels());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        for(int i = 0; i< GameConfiguration.getDifficultyLevels().length;i++){
            if (difficulty.equals(GameConfiguration.getDifficultyLevels()[i])) {
                spinner.setSelection(i); // load the game's difficulty
            }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                difficulty = GameConfiguration.getDifficultyLevels()[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void populateSpinner(){
        Spinner spinner = findViewById(R.id.gameConfigSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                GameConfiguration.getConfigListNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(configurationID);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                configurationID = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void populateFields(){
        Game currGame = GameConfiguration.getConfigList().get(configurationID).getGamesList().get(gameID);

        EditText numPlayers = findViewById(R.id.numPlayersTextEdit);
        numPlayers.setText(String.format("%d",currGame.getNumPlayers()));

        EditText totalScore = findViewById(R.id.scoreTextEdit);
        totalScore.setText(String.format("%d",currGame.getScore()));
    }

    private void updateGamePlay(){
        int num_players;
        int sum_score;

        if(configurationID >= GameConfiguration.getNumConfigurations()
                || configurationID < 0){
            Toast.makeText(EditGamePlay.this,
                    "Please Select Valid Game",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        num_players = getNumPlayers();
        sum_score = getSumScore();

        Game updatedGame = GameConfiguration.getConfigList().get(configurationID).getGame(gameID);
        updatedGame.setDifficulty(difficulty);
        updatedGame.setNumPlayers(num_players);
        updatedGame.setScore(sum_score);
        AchievementList achievementList = new AchievementList(
                GameConfiguration.getConfigList().get(configurationID).getPoor_score(),
                GameConfiguration.getConfigList().get(configurationID).getGreat_score(),
                num_players,
                difficulty);
        updatedGame.setAchievementList(achievementList);

        GameConfiguration = ConfigManager.getInstance();
        GameConfiguration.getConfigList().get(configurationID).getGamesList().set(gameID,updatedGame);
        SharedPreferencesUtils.storeConfigManagerToSharedPreferences(EditGamePlay.this);

        FragmentManager manager = getSupportFragmentManager();
        CongratulationsFragment dialog = new CongratulationsFragment();
        dialog.setCurrentGame(EditGamePlay.this, updatedGame, configurationID, gameID);
        dialog.show(manager,"MessageDialog");
    }

}