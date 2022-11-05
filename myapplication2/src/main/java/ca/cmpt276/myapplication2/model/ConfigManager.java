package ca.cmpt276.myapplication2.model;

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
}
