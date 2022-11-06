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
        getConfigManagerToSharedPreferences();

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

    private void checkEmpty(){
        if(lv_ConfigList.getAdapter().isEmpty()){
            Toast.makeText(this, "It's empty now,\n please add a new configuration first.", Toast.LENGTH_SHORT).show();
        }
    }

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

    private void deleteButtonClickCallback() {
        lv_ConfigList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewclick, int position, long id) {
                TextView textView = (TextView) viewclick;
                configManager.deleteConfig(position);
                storeConfigManagerToSharedPreferences();
                finish();
            }
        });
    }

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

    private void storeConfigManagerToSharedPreferences(){
        ConfigManager configManager = ConfigManager.getInstance();
        SharedPreferences prefs = getSharedPreferences("ConfigurationsList", MODE_PRIVATE);
//        List<Configuration> ConfigList = new ArrayList<Configuration>();
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(configManager);
        editor.putString("ConfigManager", json);
        editor.commit();
    }

    private void getConfigManagerToSharedPreferences(){
        SharedPreferences preferences = getSharedPreferences("ConfigurationsList", MODE_PRIVATE);
        String json = preferences.getString("ConfigManager", null);
        if (json != null)
        {
            Gson gson = new Gson();
            Type type = new TypeToken<ConfigManager>(){}.getType();

//            List<Configuration> alterSamples = new ArrayList<Configuration>();
//            storedManager = gson.fromJson(json,type);
            ConfigManager.setInstance(gson.fromJson(json,type));
//            for(int i = 0; i < alterSamples.size(); i++)
//            {
//                Log.d(TAG, alterSamples.get(i).getName()+":" + alterSamples.get(i).getX() + "," + alterSamples.get(i).getY());
//            }
        }
    }


//    public static List<BookBean> getBookBean(Context ctx) {
//        if (sp == null) {
//            sp = ctx.getSharedPreferences("config", MODE_PRIVATE);
//        }
//        Gson gson = new Gson();
//        String json = sp.getString(ConstantValue.BOOK_BEAN, null);
//        Type type = new TypeToken<List<BookBean>>() {
//        }.getType();
//        List<BookBean> arrayList = gson.fromJson(json, type);
//        return arrayList;
//    }
}