package ca.cmpt276.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.cmpt276.myapplication2.model.ConfigManager;

public class ConfigList extends AppCompatActivity {
    private ConfigManager configManager;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configManager = ConfigManager.getInstance();

        //populateListView();

        Intent intent = getIntent();
        index = intent.getIntExtra("index", -1);
        if (index == -1) {// delete
            deleteButtonClickCallback();
        } else {// Edit
            editButtonClickCallback();
        }
    }

//    private void populateListView() {
//        //create list of item
//        ArrayList<String> displayed_Configlist = null;
//
//        for(int i=0; i<configManager.getConfigList().size(); i++){
//            String tempName = configManager.getConfigList().get(i).getName();
//            displayed_Configlist.add(tempName);
//        }
//
//        //build Adaptor
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this,
//                R.layout.configurationList,//Should be configurationList.xml??????
//                displayed_Configlist);
//
//        //configure the list view
//        ListView list = findViewById(R.id.lv_configList_Config);
//        list.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }

    private void editButtonClickCallback() {
        ListView list = findViewById(R.id.lv_configList_Config);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewclick, int position, long id) {
                TextView textView = (TextView) viewclick;
                Intent jumpToData = new Intent(ConfigList.this, ConfigData.class);
                jumpToData.putExtra("position", position);
                startActivity(jumpToData);
            }
        });
    }

    private void deleteButtonClickCallback() {
        ListView list = findViewById(R.id.lv_configList_Config);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewclick, int position, long id) {
                TextView textView = (TextView) viewclick;
                configManager.deleteConfig(position);
                finish();
            }
        });
    }
}