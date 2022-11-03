package ca.cmpt276.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ca.cmpt276.myapplication2.model.ConfigManager;

public class AddGamePlay extends AppCompatActivity {
    private ConfigManager GameConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_addplay);

        GameConfiguration = ConfigManager.getInstance();

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Add Game Play");

    }

    public static Intent makeIntent(Context context){
        return new Intent(context, AddGamePlay.class);
    }


}
