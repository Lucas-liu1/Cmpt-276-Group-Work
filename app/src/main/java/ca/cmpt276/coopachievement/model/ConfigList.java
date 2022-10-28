package ca.cmpt276.coopachievement.model;

import java.util.ArrayList;

public class ConfigList {
    ArrayList<Configuration> configList;// Store all the configurations

    public ConfigList() {
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
