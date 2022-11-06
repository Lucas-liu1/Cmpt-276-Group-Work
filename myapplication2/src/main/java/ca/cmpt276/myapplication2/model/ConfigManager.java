package ca.cmpt276.myapplication2.model;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ConfigManager {
    private ArrayList<Configuration> configList;// Store all the configurations

    private ConfigManager() {
        configList = new ArrayList<>();
    }

    private static ConfigManager instance;
    public static ConfigManager getInstance(){
        if(instance == null){
            instance = new ConfigManager();
        }
        return instance;
    }

    public void addConfig(Configuration newConfig){
        configList.add(newConfig);
    }

    public void addGame(int configID, Game game){
        configList.get(configID).addGame(game);
    }

    public void editConfig(int index, Configuration newConfig){
        configList.set(index, newConfig);
    }

    public void deleteConfig(int index){
        configList.remove(index);
    }

    public ArrayList<Configuration> getConfigList() {
        return configList;
    }


    public String[] getConfigListNames(){
        String[] configNames = new String[configList.size()];
        for(int i=0; i<configList.size();i++){
            configNames[i] = configList.get(i).getName();
        }
        return configNames;
    }

    public String[] getConfigListNamesWithAll(){
        String[] configNames = new String[configList.size()+1];
        configNames[0] = "All Games";
        for(int i=0; i<configList.size();i++){
            configNames[i+1] = configList.get(i).getName();
        }
        return configNames;
    }

    public int getNumConfigurations(){
        return configList.size();
    }

    public int getNumTotalGames(){
        int total = 0;
        for(int i = 0 ;i < configList.size();i++){
            total += configList.get(i).getGamesListSize();
        }
        return total;
    }

    public static void setInstance(ConfigManager instance) {
        ConfigManager.instance = instance;
    }

//    private void getConfigManagerToSharedPreferences(){
//        SharedPreferences preferences = getSharedPreferences("ConfigurationsList", MODE_PRIVATE);
//        String json = preferences.getString("ConfigManager", null);
//        if (json != null)
//        {
//            Gson gson = new Gson();
//            Type type = new TypeToken<ConfigManager>(){}.getType();
//            ConfigManager.setInstance(gson.fromJson(json,type));
//        }
//    }
//    private void storeConfigManagerToSharedPreferences(){
//        ConfigManager configManager = ConfigManager.getInstance();
//        SharedPreferences prefs = getSharedPreferences("ConfigurationsList", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(configManager);
//        editor.putString("ConfigManager", json);
//        editor.commit();
//    }
}
