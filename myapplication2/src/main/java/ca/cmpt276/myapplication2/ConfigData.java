package ca.cmpt276.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Configuration;
import ca.cmpt276.myapplication2.model.SharedPreferencesUtils;

/**
 * This activity is for change the data in a single configuration.
 */

public class ConfigData extends AppCompatActivity {
    private ConfigManager configManager;
    private byte[] photo_byte = ConfigManager.getBufferPhoto();
    private int targetPosition;
    private Button btn_Save;
    private EditText editText_Name;
    private EditText editText_PoorScore;
    private EditText editText_GreatScore;
    private Configuration targetConfig;
    private ImageView imv_photo;
    private String oldName;
    private int oldPoorScore;
    private int oldGreatScore;
    private byte[] oldPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_data);
        setTitle("ConfigData");
    }

    @Override
    protected void onResume() {
        super.onResume();
        configManager = ConfigManager.getInstance();
        SharedPreferencesUtils.getConfigManagerToSharedPreferences(ConfigData.this);
        btn_Save = findViewById(R.id.btn_save);
        editText_Name = findViewById(R.id.pt_name);
        editText_PoorScore = findViewById(R.id.pt_poorScore);
        editText_GreatScore = findViewById(R.id.pt_greatScore);
        imv_photo = findViewById(R.id.imv_Photo_Config);
        photo_byte = ConfigManager.getBufferPhoto();
        photoClickCallback();

        Intent intent = getIntent();
        targetPosition = intent.getIntExtra("position", -1);

        if (targetPosition == -1) {// Add
            addClickCallback();
            displayPhoto();
        } else {// Edit
            displayData();
            editClickCallback();
        }
    }

    // When the user click the ADD NEW CONFIG button...
    private void addClickCallback() {
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string_Name = editText_Name.getText().toString().trim();
                String string_PoorScore = editText_PoorScore.getText().toString().trim();
                String string_GreatScore = editText_GreatScore.getText().toString().trim();

                if (string_Name.isEmpty() || string_PoorScore.isEmpty() || string_GreatScore.isEmpty()){
                    Toast.makeText(ConfigData.this, "Please fill in all the places", Toast.LENGTH_SHORT).show();
                    return;
                }

                int num_PoorScore = Integer.parseInt(string_PoorScore);
                int num_GreatScore = Integer.parseInt(string_GreatScore);

                if (num_PoorScore >= num_GreatScore){
                    Toast.makeText(ConfigData.this, "Great Score must be greater than Poor Score", Toast.LENGTH_SHORT).show();
                    return;
                }

                targetConfig = new Configuration(string_Name, num_PoorScore, num_GreatScore);
                photo_byte = ConfigManager.getBufferPhoto();
                if(photo_byte != null){
                    targetConfig.setPhoto_byte(photo_byte);
                }
                ConfigManager configList = ConfigManager.getInstance();
                configList.addConfig(targetConfig);
                SharedPreferencesUtils.storeConfigManagerToSharedPreferences(ConfigData.this);
                ConfigManager.clearBufferPhoto();
                finish();
            }
        });
    }

    // Set the value of the Edit Text
    private void displayData(){
        Configuration oldConfig = configManager.getConfigList().get(targetPosition);
        oldName = oldConfig.getName();
        oldPoorScore = oldConfig.getPoor_score();
        oldGreatScore = oldConfig.getGreat_score();

        if(oldConfig.getPhoto_byte() != null && ConfigManager.getBufferPhoto() == null){
            oldPhoto = oldConfig.getPhoto_byte();
            Bitmap photo_bm = BitmapFactory.decodeByteArray(oldPhoto, 0, oldPhoto.length);
            imv_photo.setImageBitmap(photo_bm);
        }
        else if(ConfigManager.getBufferPhoto() != null){
            oldPhoto = ConfigManager.getBufferPhoto();
            Bitmap photo_bm = BitmapFactory.decodeByteArray(oldPhoto, 0, oldPhoto.length);
            imv_photo.setImageBitmap(photo_bm);
        }

        editText_Name.setText(oldName);
        editText_PoorScore.setText(String.valueOf(oldPoorScore));
        editText_GreatScore.setText(String.valueOf(oldGreatScore));
    }

    private void displayPhoto(){
        if(ConfigManager.getBufferPhoto() != null){
            byte[] Photo = ConfigManager.getBufferPhoto();
            Bitmap photo_bm = BitmapFactory.decodeByteArray(Photo, 0, Photo.length);
            imv_photo.setImageBitmap(photo_bm);
        }
    }

    // When the user click the EDIT CONFIG button...
    private void editClickCallback() {
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string_Name = editText_Name.getText().toString().trim();
                String string_PoorScore = editText_PoorScore.getText().toString().trim();
                String string_GreatScore = editText_GreatScore.getText().toString().trim();

                if(string_Name.isEmpty() || string_PoorScore.isEmpty() || string_GreatScore.isEmpty()){
                    Toast.makeText(ConfigData.this, "Please fill in all the places", Toast.LENGTH_SHORT).show();
                    return;
                }

                int num_PoorScore = Integer.parseInt(string_PoorScore);
                int num_GreatScore = Integer.parseInt(string_GreatScore);

                if (num_PoorScore >= num_GreatScore){
                    Toast.makeText(ConfigData.this, "Great Score must be greater than Poor Score", Toast.LENGTH_SHORT).show();
                    return;
                }


                targetConfig = new Configuration(string_Name, num_PoorScore, num_GreatScore);
                photo_byte = ConfigManager.getBufferPhoto();

                if((num_PoorScore == oldPoorScore) && (num_GreatScore == oldGreatScore)){
                    photo_byte = ConfigManager.getBufferPhoto();
                    configManager = ConfigManager.getInstance();

                    if(!string_Name.equals(oldName)){
                        configManager.getConfigList().get(targetPosition).setName(string_Name);
                    }

                    if(photo_byte != null){
                        configManager.getConfigList().get(targetPosition).setPhoto_byte(photo_byte);
                    }

                    SharedPreferencesUtils.storeConfigManagerToSharedPreferences(ConfigData.this);
                    ConfigManager.clearBufferPhoto();
                    finish();
                    return;
                }

                if(photo_byte != null){
                    targetConfig.setPhoto_byte(photo_byte);
                }else {
                    targetConfig.setPhoto_byte(oldPhoto);
                }
                configManager = ConfigManager.getInstance();
                configManager.editConfig(targetPosition, targetConfig);
                SharedPreferencesUtils.storeConfigManagerToSharedPreferences(ConfigData.this);
                ConfigManager.clearBufferPhoto();
                finish();
            }
        });
    }

    private void photoClickCallback() {
        Button btn_addPhoto = findViewById(R.id.btn_Photo_Config);
        btn_addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jumpToPhoto = new Intent(ConfigData.this, Photo.class);
                startActivity(jumpToPhoto);
            }
        });
    }
}