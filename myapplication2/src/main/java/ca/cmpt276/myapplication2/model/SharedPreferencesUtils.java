package ca.cmpt276.myapplication2.model;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * This class is for save data to the device.
 */

public class SharedPreferencesUtils {

    //store the data
    public static void storeConfigManagerToSharedPreferences(Context c){
        ConfigManager configManager = ConfigManager.getInstance();
        android.content.SharedPreferences prefs = c.getSharedPreferences("ConfigurationsList", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(configManager);
        editor.putString("ConfigManager", json);
        editor.commit();
    }

    //get the data
    public static void getConfigManagerToSharedPreferences(Context c){
        android.content.SharedPreferences preferences = c.getSharedPreferences("ConfigurationsList", MODE_PRIVATE);
        String json = preferences.getString("ConfigManager", null);
        if (json != null)
        {
            Gson gson = new Gson();
            Type type = new TypeToken<ConfigManager>(){}.getType();

            ConfigManager.setInstance(gson.fromJson(json,type));
        }
    }
}
