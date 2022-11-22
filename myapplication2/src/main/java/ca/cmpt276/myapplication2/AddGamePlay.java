package ca.cmpt276.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import ca.cmpt276.myapplication2.model.AchievementList;
import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Game;
import ca.cmpt276.myapplication2.model.SharedPreferencesUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Extension of the game plays class
 * This class provides the functionality of creating a new game play.
 * Drawing from the models provided able to take in a game config to give out results of
 * the game played with the inputted scores drawing back to the achievement list manager
 */

public class AddGamePlay extends AppCompatActivity {
    private ConfigManager GameConfiguration;
    private ArrayList<Integer> scores = ConfigManager.getBufferScore();
    private int configurationID;
    private int themeID;
    private String difficulty;
    private String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_addplay);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Add Game Play");
        GameConfiguration = ConfigManager.getInstance();

        populateSpinner();
        populateDifficultySpinner();
        populateThemeSpinner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameConfiguration = ConfigManager.getInstance();
        SharedPreferencesUtils.getConfigManagerToSharedPreferences(this);
        scores = ConfigManager.getBufferScore();
        setCreateButton();
        setCalculateButton();
        fillTotalScoreField();
    }

    private void setCreateButton() {
        Button btn = findViewById(R.id.createButton);
        btn.setText("Create");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGamePlay();
            }
        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, AddGamePlay.class);
    }

    private void fillTotalScoreField(){
        int sum = 0;
        for(int i = 0; i < scores.size(); i++) {
            sum+=scores.get(i);
        }
        EditText et = findViewById(R.id.scoreTextEdit);
        et.setText(String.format("%d",sum));
    }

    private void populateDifficultySpinner(){
        Spinner spinner = findViewById(R.id.difficultyLevelSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                GameConfiguration.getDifficultyLevels());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(1); // medium difficulty
        difficulty = GameConfiguration.getDifficultyLevels()[1]; // set default to medium

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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                configurationID = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public void populateThemeSpinner(){
        Spinner spinner = findViewById(R.id.spinner);
        String[] themeList = ConfigManager.getThemes();
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                themeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                theme = themeList[position];
                themeID = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setCalculateButton() {
        Button btn = findViewById(R.id.calculateScoreBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getNumPlayers()>0) {
                    Intent intent = CalculatePlayerScore.makeIntent(AddGamePlay.this,
                            getNumPlayers(), new ArrayList<>());
                    startActivity(intent);
                }else{
                    Toast.makeText(AddGamePlay.this,
                            "Please Fill in Valid Number of Players",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveFirstpass(){
        SharedPreferences sharedPref;
        sharedPref = getSharedPreferences("firstPass", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("firstPass", false);
        editor.apply();
    }

    private int getNumPlayers(){
        EditText numPlayers = findViewById(R.id.numPlayersTextEdit);
        String numPlayersStr = numPlayers.getText().toString();
        int num_players;
        try {
            num_players = Integer.parseInt(numPlayersStr);
        }catch(NumberFormatException except){
            Toast.makeText(AddGamePlay.this, "Number of Players must be an integer", Toast.LENGTH_SHORT)
                    .show();
            return 0;
        }
        return num_players;
    }

    private void addGamePlay(){
        saveFirstpass();
        int num_players;

        if(configurationID >= GameConfiguration.getNumConfigurations()
                || configurationID < 0){
            Toast.makeText(AddGamePlay.this,
                    "Please Select Valid Game",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        num_players=getNumPlayers();

        Game newGame = new Game(num_players, scores, difficulty);
        newGame.setTheme(theme);
        AchievementList achievementList = new AchievementList(
                GameConfiguration.getConfigList().get(configurationID).getPoor_score(),
                GameConfiguration.getConfigList().get(configurationID).getGreat_score(),
                num_players,
                difficulty,
                theme);
        newGame.setAchievementList(achievementList);

        GameConfiguration = ConfigManager.getInstance();
        GameConfiguration.addGame(configurationID, newGame);
        SharedPreferencesUtils.storeConfigManagerToSharedPreferences(AddGamePlay.this);


        FragmentManager manager = getSupportFragmentManager();
        CongratulationsFragment dialog = new CongratulationsFragment();
        dialog.setCurrentGame(AddGamePlay.this,newGame,configurationID,
                GameConfiguration.getConfigList().get(configurationID).getGamesListSize()-1,
                themeID);
        dialog.show(manager,"MessageDialog");
    }

}
