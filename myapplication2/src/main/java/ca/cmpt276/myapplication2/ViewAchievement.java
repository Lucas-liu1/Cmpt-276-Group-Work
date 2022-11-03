package ca.cmpt276.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import ca.cmpt276.myapplication2.model.AchievementList;


public class ViewAchievement extends AppCompatActivity {

    Button btn;
    TextView level;
    EditText NumPlayers;
    String name;
    String score;
    String[] levelL;

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
                int poor = 30;
                int great = 90;

                levelL = new String[8];
                AchievementList levelList = new AchievementList(poor, great, playerNum);
                for (int i = 0; i < levelList.achievementsList.size(); i++) {
                    name = String.valueOf(levelList.achievementsList.get(i).getName());
                    score = String.valueOf(levelList.achievementsList.get(i).getLowerBound());
                    levelL[i] = "Level name: " + name + "\nRequire score:  " + score +"\n" ;
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