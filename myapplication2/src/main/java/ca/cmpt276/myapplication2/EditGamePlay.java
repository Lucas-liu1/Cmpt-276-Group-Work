package ca.cmpt276.myapplication2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.ArrayList;

/**
 * Extension of the game plays class
 * This class provides the functionality of editing an existing game play.
 * Drawing from the models provided able to take in a game config to give out results of
 * the game played with the inputted scores drawing back to the achievement list manager
 */

public class EditGamePlay extends AppCompatActivity {
    private static final String EXTRA_GAME = "Game";
    private static final String EXTRA_CONFIG = "Configuration";
    private static final String EXTRA_THEME = "Theme";
    Game updatedGame;
    private ArrayList<Integer> scores = ConfigManager.getBufferScore();
    private ConfigManager GameConfiguration;
    private int configurationID; // the game config ID
    private static int gameID; // the game ID
    private static int themeID; // the game theme index on spinner
    private String difficulty;
    private String theme;
    private byte[] photo_byte = ConfigManager.getBufferPhoto();
    private int numPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_addplay);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Edit Game Play");
        GameConfiguration = ConfigManager.getInstance();
        extractIDFromIntent();
        updatedGame = GameConfiguration.getConfigList().get(configurationID).getGame(gameID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        scores = ConfigManager.getBufferScore();
        photo_byte = ConfigManager.getBufferPhoto();
        if (scores.size()>0){
            fillTotalScoreField();
        }
        SharedPreferencesUtils.getConfigManagerToSharedPreferences(this);
        populateSpinner();
        populateDifficultySpinner();
        setSaveButton();
        setCalculateButton();
        populateFields();
        populateThemeSpinner();
        setupNumPlayersOnFill();
        photoClickCallback();
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

    private void setupNumPlayersOnFill() {
        EditText et = findViewById(R.id.numPlayersTextEdit);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                updatedGame.setNumPlayers(getNumPlayers());
            }
        });
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
                    Intent intent = CalculatePlayerScore.makeIntent(EditGamePlay.this,
                            getNumPlayers(), updatedGame.getScoresList());
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

    private void extractIDFromIntent() {
        Intent intent = getIntent();
        configurationID = intent.getIntExtra(EXTRA_CONFIG,0);
        gameID = intent.getIntExtra(EXTRA_GAME,0);
        themeID = intent.getIntExtra(EXTRA_THEME,0);
    }

    public static Intent makeIntent(Context context, int configID,int gameID, int themeID){
        Intent intent = new Intent(context, EditGamePlay.class);
        intent.putExtra(EXTRA_CONFIG, configID);
        intent.putExtra(EXTRA_GAME,gameID);
        intent.putExtra(EXTRA_THEME,themeID);
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

    private void fillTotalScoreField(){
        int sum = 0;
        for(int i = 0; i < scores.size(); i++) {
            sum+=scores.get(i);
        }
        EditText et = findViewById(R.id.scoreTextEdit);
        et.setText(String.format("%d",sum));
        updatedGame.setScoresList(scores);
    }


    public void populateThemeSpinner(){
        Spinner spinner = findViewById(R.id.spinner);
        String[] themeList = ConfigManager.getThemes();
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                themeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(themeID);
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

    private void populateFields(){
        EditText numPlayers = findViewById(R.id.numPlayersTextEdit);
        numPlayers.setText(String.format("%d",updatedGame.getNumPlayers()));

        EditText totalScore = findViewById(R.id.scoreTextEdit);
        totalScore.setText(String.format("%d",getSum(scores)));

        ImageView photo = findViewById(R.id.PPP);
        if (photo_byte == null){
            if(updatedGame.getPhoto() == null)
            {
                return;
            }
            photo_byte = updatedGame.getPhoto();
        }
        else{
            photo_byte = ConfigManager.getBufferPhoto();
        }
        Bitmap photo_bm = BitmapFactory.decodeByteArray(photo_byte, 0, photo_byte.length);
        photo.setImageBitmap(photo_bm);
    }

    private void updateGamePlay(){
        int num_players;

        if(configurationID >= GameConfiguration.getNumConfigurations()
                || configurationID < 0){
            Toast.makeText(EditGamePlay.this,
                    "Please Select Valid Game",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        num_players = getNumPlayers();

        updatedGame.setDifficulty(difficulty);
        updatedGame.setTheme(theme);
        updatedGame.setNumPlayers(num_players);
        updatedGame.setScoresList(scores_submit);

        //photo is a byte[] here, not bitmap
        photo_byte = ConfigManager.getBufferPhoto();
        if(photo_byte != null){
            updatedGame.setPhoto(photo_byte);
        }

        AchievementList achievementList = new AchievementList(
                GameConfiguration.getConfigList().get(configurationID).getPoor_score(),
                GameConfiguration.getConfigList().get(configurationID).getGreat_score(),
                num_players,
                difficulty,
                theme);
        updatedGame.setAchievementList(achievementList);

        GameConfiguration = ConfigManager.getInstance();
        GameConfiguration.getConfigList().get(configurationID).getGamesList().set(gameID,updatedGame);
        SharedPreferencesUtils.storeConfigManagerToSharedPreferences(EditGamePlay.this);

        FragmentManager manager = getSupportFragmentManager();
        CongratulationsFragment dialog = new CongratulationsFragment();
        dialog.setCurrentGame(EditGamePlay.this, updatedGame, configurationID, gameID,themeID);
        dialog.show(manager,"MessageDialog");
        ConfigManager.clearBufferPhoto();
    }

    private void photoClickCallback() {
        Button btn_addPhoto = findViewById(R.id.btn_addPhoto);
        btn_addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToPhoto = new Intent(EditGamePlay.this, Photo.class);
                jumpToPhoto.putExtra("From", 1);
                startActivity(jumpToPhoto);
            }
        });
    }

//    private void showPhoto(){
//        photo_byte = getIntent().getByteArrayExtra("Photo");
//        ImageView photo = findViewById(R.id.PPP);
//        if(photo_byte == null){
//            return;
//        }
//        else{
//            Bitmap photo_bm = BitmapFactory.decodeByteArray(photo_byte, 0, photo_byte.length);
//            photo.setImageBitmap(photo_bm);
//        }
//
//    }

}
