package ca.cmpt276.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Configuration;
import ca.cmpt276.myapplication2.model.SharedPreferencesUtils;

/**
 * This activity is for add, edit or delete the configuration.
 */

public class ConfigList extends AppCompatActivity {
    private ConfigManager configManager;
    private ListView lv_ConfigList;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_list);
        setTitle("ConfigList");
        ActionBar back = getSupportActionBar();
        assert back != null;
        back.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lv_ConfigList = findViewById(R.id.lv_configList_Config);
        configManager = ConfigManager.getInstance();
        SharedPreferencesUtils.getConfigManagerToSharedPreferences(this);

        populateListView();
        checkEmpty();

        Intent intent = getIntent();
        index = intent.getIntExtra("index", -1);
        if (index == -1) {// delete
            deleteButtonClickCallback();
        }
        else if (index == 1) {// Edit
            editButtonClickCallback();
        }
        else {
            achievementButtonClickCallback();
        }
    }

    // Check if the list is empty.
    private void checkEmpty(){
        if(lv_ConfigList.getAdapter().isEmpty()){
            Toast.makeText(this, "It's empty now,\n please add a new configuration first.", Toast.LENGTH_SHORT).show();
        }
    }

    // Populate the list view.
    private void populateListView() {
        configManager = ConfigManager.getInstance();

        ArrayList<String> displayed_Configlist = new ArrayList<>();

        for(int i=0; i<configManager.getConfigList().size(); i++){
            String tempName = configManager.getConfigList().get(i).getName();
            displayed_Configlist.add(tempName);
        }

        //build Adaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.configlist_config,
                displayed_Configlist);

        //configure the list view
        lv_ConfigList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    // When the user click the EDIT CONFIG button...
    private void editButtonClickCallback() {
        lv_ConfigList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewclick, int position, long id) {
                TextView textView = (TextView) viewclick;
                Intent jumpToData = new Intent(ConfigList.this, ConfigData.class);
                jumpToData.putExtra("position", position);
                startActivity(jumpToData);
            }
        });
    }

    // When the user click the DELETE CONFIG button...
    private void deleteButtonClickCallback() {
        lv_ConfigList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewclick, int position, long id) {
                TextView textView = (TextView) viewclick;
                configManager.deleteConfig(position);
                SharedPreferencesUtils.storeConfigManagerToSharedPreferences(ConfigList.this);
                finish();
            }
        });
    }

    // When the user click the VIEW ACHIEVEMENT button...
    private void achievementButtonClickCallback() {
        lv_ConfigList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewclick, int position, long id) {
                TextView textView = (TextView) viewclick;
                Intent jumpToAchieve = new Intent(ConfigList.this, ViewAchievement.class);
                jumpToAchieve.putExtra("position", position);
                startActivity(jumpToAchieve);
            }
        });
    }
}