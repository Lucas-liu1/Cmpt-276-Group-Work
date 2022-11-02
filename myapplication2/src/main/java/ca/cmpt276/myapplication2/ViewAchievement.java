package ca.cmpt276.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.cmpt276.myapplication2.model.Achievement;
import ca.cmpt276.myapplication2.model.AchievementList;

public class ViewAchievement extends AppCompatActivity {

    Button btn;
    TextView level;
    EditText NumPlayers;
    EditText editText_PoorScore;
    EditText editText_GreatScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_achievement);
        setTitle("Achievement Level");
        ActionBar back = getSupportActionBar();
        back.setDisplayHomeAsUpEnabled(true);

        setView();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Still work in this part!!

/*

                String str = NumPlayers.getText().toString();
                int playerNum= Integer.parseInt(str);
                editText_PoorScore = findViewById(R.id.pt_poorScore);
                editText_GreatScore = findViewById(R.id.pt_greatScore);
                String string_PoorScore = editText_PoorScore.getText().toString().trim();
                String string_GreatScore = editText_GreatScore.getText().toString().trim();
                int num_PoorScore = Integer.parseInt(string_PoorScore);
                int num_GreatScore = Integer.parseInt(string_GreatScore);
                AchievementList levelList = new AchievementList( num_PoorScore, num_GreatScore, playerNum );

                //Achievement tempAchievement = achievementsList.get(playerNum);
                level.setText("Level list:" + );

 */
            }
        });
    }

    private void setView(){
        NumPlayers =  (EditText) findViewById(R.id.editTextPlayers);
        level = (TextView) findViewById(R.id.levelList);
        btn = (Button) findViewById(R.id.btnShow);
    }
}