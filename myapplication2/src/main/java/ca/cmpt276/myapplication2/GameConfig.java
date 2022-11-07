package ca.cmpt276.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This activity is for change a single configuration's data.
 */

public class GameConfig extends AppCompatActivity {
    private Button Add;
    private Button Edit;
    private Button Delete;
    private Button View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_config);
        setTitle("Game Config");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Add = findViewById(R.id.btn_add_config);
        Edit = findViewById(R.id.btn_edit_config);
        Delete = findViewById(R.id.btn_delete_config);
        View = findViewById(R.id.btn_viewAchievement);

        addClickCallback();
        editClickCallback();
        deleteClickCallback();
        achievementClickCallback();
    }

    // When the user click the ADD NEW CONFIG button...
    private void addClickCallback() {
        Add = findViewById(R.id.btn_add_config);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToAdd = new Intent(GameConfig.this, ConfigData.class);
                startActivity(jumpToAdd);
            }
        });
    }

    // When the user click the EDIT CONFIG button...
    private void editClickCallback() {
        Edit = findViewById(R.id.btn_edit_config);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToEdit = new Intent(GameConfig.this, ConfigList.class);
                jumpToEdit.putExtra("index", 1);
                startActivity(jumpToEdit);
            }
        });
    }

    // When the user click the DELETE CONFIG button...
    private void deleteClickCallback() {
        Delete = findViewById(R.id.btn_delete_config);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToDelete = new Intent(GameConfig.this, ConfigList.class);
                startActivity(jumpToDelete);
            }
        });
    }

    // When the user click the VIEW ACHIEVEMENT button...
    private void achievementClickCallback() {
        View = findViewById(R.id.btn_viewAchievement);
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToAchievement = new Intent(GameConfig.this, ConfigList.class);
                jumpToAchievement.putExtra("index", 2);
                startActivity(jumpToAchievement);
            }
        });
    }

}