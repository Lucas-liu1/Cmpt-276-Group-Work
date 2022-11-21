package ca.cmpt276.myapplication2.model;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is for store all the configurations.
 */

public class ConfigManager {
    private ArrayList<Configuration> configList;// Store all the configurations
    private String[] difficultyLevels = {"easy", "medium", "hard"};
    public static String[] themes = {"theme 1", "theme 2", "theme 3"};
    public static ArrayList<Integer> bufferScore = new ArrayList<>();

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

    public static String[] getThemes(){
        return themes;
    }

    public static int getThemeID(String theme){
       for(int i = 0; i < themes.length;i++){
           if (Objects.equals(themes[i], theme)){
               return i;
           }
       }
       return -1;
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

    public String[] getDifficultyLevels(){
        return difficultyLevels;
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
}
