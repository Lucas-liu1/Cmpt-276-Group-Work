package ca.cmpt276.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Configuration;
import ca.cmpt276.myapplication2.model.Game;

public class GamePlays extends AppCompatActivity {
    private ConfigManager GameConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_plays);

        GameConfiguration = ConfigManager.getInstance();

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Game plays");

        setAddGamePlayButton();
        populateListView();
    }

    private void setAddGamePlayButton() {
        Button btn = findViewById(R.id.addGameButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddGamePlay.makeIntent(GamePlays.this);
                startActivity(intent);
            }
        });
    }

    // theres something wrong with the array lists as I try to populate the listview
    // need to understand which part is wrong
    private void populateListView() {
//        String[] listData = new String[GameConfiguration.getNumTotalGames()];
//
//        // Create list of items
//        for(int i =0; i< GameConfiguration.getNumConfigurations(); i++) {
//            ArrayList<Game> ithConfig = GameConfiguration.getConfigList().get(i).getGamesList();
//            for (int j = 0; i < ithConfig.size(); j++) {
//                listData[j] = ithConfig.get(j).getRecord();
//            }
//        }
//        // Build Adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this,   // activity context
//                R.layout.list_items, // layout to use
//                listData);      // data
//
//        // Configure the list view
//        ListView list = findViewById(R.id.listGamesPlayed);
//        list.setAdapter(adapter);
    }
}