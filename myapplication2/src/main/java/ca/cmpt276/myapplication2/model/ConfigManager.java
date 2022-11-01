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

    public void addNewConfig(Configuration newConfig){
        configList.add(newConfig);
    }

    public void addConfig(Configuration newConfig){
        configList.add(newConfig);
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
}
