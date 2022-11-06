package ca.cmpt276.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ca.cmpt276.myapplication2.model.AchievementList;
import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Configuration;
import ca.cmpt276.myapplication2.model.SharedPreferencesUtils;


public class ViewAchievement extends AppCompatActivity {


    private Button btn;
    private TextView level;
    private EditText NumPlayers;
    private String name;
    private String score;
    private String[] levelL;
    private ConfigManager configManager;
    private int targetPosition;
    private Configuration targetConfig;
    private int poorScore;
    private int greatScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_achievement);
        setTitle("Achievement Level");
        ActionBar back = getSupportActionBar();
        assert back != null;
        back.setDisplayHomeAsUpEnabled(true);

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
                AchievementList levelList = new AchievementList(poorScore, greatScore, playerNum);

                levelL[0] = "Level name: " + levelList.achievementsList.get(0).getName() + "\nRequire score: less than " + poorScore*playerNum +"\n" ;
                levelL[7] = "Level name: " + levelList.achievementsList.get(7).getName() + "\nRequire score: greater than " + greatScore*playerNum +"\n" ;

                for (int i = 1; i < levelList.achievementsList.size() - 1; i++) {
                    name = String.valueOf(levelList.achievementsList.get(i).getName());
                    score = String.valueOf(levelList.achievementsList.get(i).getLowerBound());
                    levelL[i] = "Level name: " + name + "\nRequire score: greater than " + score +"\n" ;
                }

                level.setText(
                        "Level  List\n"+"\n"
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

    private void setView(){
        NumPlayers =  (EditText) findViewById(R.id.editTextPlayers);
        level = (TextView) findViewById(R.id.levelList);
        btn = (Button) findViewById(R.id.btnShow);
    }
}