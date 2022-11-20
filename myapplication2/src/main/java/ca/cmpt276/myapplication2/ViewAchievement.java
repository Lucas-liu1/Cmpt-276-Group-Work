package ca.cmpt276.myapplication2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ca.cmpt276.myapplication2.model.AchievementList;
import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Configuration;
import ca.cmpt276.myapplication2.model.SharedPreferencesUtils;
/**
 * this class is about showing the achievement level list
 * by getting a input number(player number) to calculate the required score of each level.
 */

public class ViewAchievement extends AppCompatActivity {


    private Button btn;
    private TextView level;
    private EditText NumPlayers;
    private String name ;
    private String score;
    private String[] levelL;
    private ConfigManager configManager;
    private int targetPosition;
    private Configuration targetConfig;
    private int poorScore;
    private int greatScore;
    private String difficulty;
    private String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_achievement);
        setTitle("Achievement Level");
        ActionBar back = getSupportActionBar();
        assert back != null;
        back.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        populateDifficultySpinner();
        populateThemeSpinner();

        setView();
        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String str = NumPlayers.getText().toString();
                int playerNum = Integer.parseInt(str);

                //need reset value with input
                configManager = ConfigManager.getInstance();
                Intent intent = getIntent();
                targetPosition = intent.getIntExtra("position", -1);

                targetConfig = configManager.getConfigList().get(targetPosition);
                SharedPreferencesUtils.getConfigManagerToSharedPreferences(ViewAchievement.this);
                poorScore = targetConfig.getPoor_score();
                greatScore = targetConfig.getGreat_score();


                levelL = new String[8];
                AchievementList levelList = new AchievementList(poorScore, greatScore, playerNum, difficulty, theme);
                int LowerBound = poorScore*playerNum;
                int HigherBound = greatScore*playerNum;
                if(difficulty == "easy"){
                    LowerBound *= 0.75;
                    HigherBound *= 0.75;
                }
                else if(difficulty == "hard"){
                    LowerBound *= 1.25;
                    HigherBound *= 1.25;
                }

                levelL[0] = "Level name: " + levelList.achievementsList.get(0).getName() + "\nRequire score: less than " + LowerBound +"\n" ;
                levelL[7] = "Level name: " + levelList.achievementsList.get(7).getName() + "\nRequire score: greater than " + HigherBound +"\n" ;

                for (int i = 1; i < levelList.achievementsList.size() - 1; i++) {
                    name = String.valueOf(levelList.achievementsList.get(i).getName());
                    score = String.valueOf(levelList.achievementsList.get(i).getLowerBound());
                    levelL[i] = "Level name: " + name + "\nRequire score: greater than " + score +"\n" ;
                }

                level.setText(
                        "Level  List\n"
                                +String.valueOf(levelL[0])+"\n"
                                +String.valueOf(levelL[1])+"\n"
                                +String.valueOf(levelL[2])+"\n"
                                +String.valueOf(levelL[3])+"\n"
                                +String.valueOf(levelL[4])+"\n"
                                +String.valueOf(levelL[5])+"\n"
                                +String.valueOf(levelL[6])+"\n"
                                +String.valueOf(levelL[7])
                );
            }
        });
    }

    public void populateThemeSpinner(){
        Spinner spinner = findViewById(R.id.ThemeSpinner);
        String[] themeList = {"theme 1", "theme 2", "theme 3" };
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                themeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                theme = themeList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void populateDifficultySpinner(){
        Spinner spinner = findViewById(R.id.difficultySpinner);
        String[] diffList = {"normal", "easy", "hard"};
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                diffList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                difficulty = diffList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void setView(){
        NumPlayers =  (EditText) findViewById(R.id.editTextPlayers);
        level = (TextView) findViewById(R.id.levelList);
        btn = (Button) findViewById(R.id.btnShow);
    }
}