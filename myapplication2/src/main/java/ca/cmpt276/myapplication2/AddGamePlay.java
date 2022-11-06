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

public class AddGamePlay extends AppCompatActivity {
    private ConfigManager GameConfiguration;
    private int configurationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_addplay);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Add Game Play");

    }

    @Override
    protected void onResume() {
        super.onResume();
        GameConfiguration = ConfigManager.getInstance();
        SharedPreferencesUtils.getConfigManagerToSharedPreferences(this);
        populateSpinner();
        setCreateButton();
    }

    private void setCreateButton() {
        Button btn = findViewById(R.id.createButton);
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

    public void populateSpinner(){
        Spinner spinner = findViewById(R.id.gameConfigSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                GameConfiguration.getConfigListNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (GameConfiguration.getNumConfigurations()>0){
            spinner.setSelection(0);
            configurationID = 0;
        }else{
            configurationID = -1;
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                configurationID = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void saveFirstpass(){
        SharedPreferences sharedPref;
        sharedPref = getSharedPreferences("firstPass", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("firstPass", false);
        editor.commit();
    }


    public void addGamePlay(){
        saveFirstpass();
        int num_players;
        int sum_score;

        if(configurationID >= GameConfiguration.getNumConfigurations()
                || configurationID < 0){
            Toast.makeText(AddGamePlay.this,
                    "Please Select Valid Game",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        EditText numPlayers = findViewById(R.id.numPlayersTextEdit);
        String numPlayersStr = numPlayers.getText().toString();
        try {
            num_players = Integer.parseInt(numPlayersStr);
        }catch(NumberFormatException except){
            Toast.makeText(AddGamePlay.this, "Number of Players must be an integer", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        EditText totalScore = findViewById(R.id.scoreTextEdit);
        String totalScoreStr = totalScore.getText().toString();
        try {
            sum_score = Integer.parseInt(totalScoreStr);
        }catch(NumberFormatException except){
            Toast.makeText(AddGamePlay.this, "Score must be an integer", Toast.LENGTH_SHORT)
                    .show();
            return;
        }


        Game newGame = new Game(num_players, sum_score);
        AchievementList achievementList = new AchievementList(
                GameConfiguration.getConfigList().get(configurationID).getPoor_score(),
                GameConfiguration.getConfigList().get(configurationID).getGreat_score(),
                num_players);
        newGame.setAchievementList(achievementList);

        GameConfiguration = ConfigManager.getInstance();
        GameConfiguration.addGame(configurationID, newGame);
        SharedPreferencesUtils.storeConfigManagerToSharedPreferences(AddGamePlay.this);

        FragmentManager manager = getSupportFragmentManager();
        CongratulationsFragment dialog = new CongratulationsFragment();
        dialog.setCurrentGame(newGame);
        dialog.show(manager,"MessageDialog");


    }

}
