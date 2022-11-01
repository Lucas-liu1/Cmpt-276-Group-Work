package ca.cmpt276.myapplication2.model;

import java.util.ArrayList;

public class ConfigList {
    ArrayList<Configuration> configList;// Store all the configurations

    public ConfigList() {
    }

    private static ConfigList instance;
    public static ConfigList getInstance(){
        if(instance == null){
            instance = new ConfigList();
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
