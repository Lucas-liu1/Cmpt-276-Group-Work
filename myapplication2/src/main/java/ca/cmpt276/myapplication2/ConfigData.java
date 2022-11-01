package ca.cmpt276.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Configuration;

public class ConfigData extends AppCompatActivity {
    private ConfigManager configManager;
    private int targetPosition;
    private Button btn_Save;
    private EditText editText_Name;
    private EditText editText_PoorScore;
    private EditText editText_GreatScore;
    private Configuration targetConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configManager = ConfigManager.getInstance();
        btn_Save = findViewById(R.id.btn_save);
        editText_Name = findViewById(R.id.pt_name);
        editText_PoorScore = findViewById(R.id.pt_poorScore);
        editText_GreatScore = findViewById(R.id.pt_greatScore);

        Intent intent = getIntent();
        targetPosition = intent.getIntExtra("position", -1);

        if (targetPosition == -1) {// Add
            addClickCallback();
        } else {// Edit
            displayData();
            editClickCallback();
        }
    }

    private void addClickCallback() {
        btn_Save = findViewById(R.id.btn_save);//Delete???
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string_Name = editText_Name.getText().toString().trim();
                String string_PoorScore = editText_PoorScore.getText().toString().trim();
                String string_GreatScore = editText_GreatScore.getText().toString().trim();
                int num_PoorScore = Integer.parseInt(string_PoorScore);
                int num_GreatScore = Integer.parseInt(string_GreatScore);
                if(string_Name.isEmpty() || string_PoorScore.isEmpty() || string_GreatScore.isEmpty()){
                    Toast.makeText(ConfigData.this, "Please fill in all the places", Toast.LENGTH_SHORT).show();
                    return;
                }
                targetConfig = new Configuration(string_Name, num_PoorScore, num_GreatScore);
                ConfigManager configList = ConfigManager.getInstance();
                configList.addConfig(targetConfig);
                finish();//???
            }
        });
    }

    private void displayData(){
        editText_Name = findViewById(R.id.pt_name);//Delete???
        editText_PoorScore = findViewById(R.id.pt_poorScore);//Delete???
        editText_GreatScore = findViewById(R.id.pt_greatScore);//Delete???

        Configuration oldConfig = configManager.getConfigList().get(targetPosition);
        String oldName = oldConfig.getName();
        int oldPoorScore = oldConfig.getPoor_score();
        int oldGreatScore = oldConfig.getGreat_score();

        editText_Name.setText(oldName);
        editText_PoorScore.setText(oldPoorScore);
        editText_GreatScore.setText(oldGreatScore);
    }

    private void editClickCallback() {
        btn_Save = findViewById(R.id.btn_save);//Delete???
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string_Name = editText_Name.getText().toString().trim();
                String string_PoorScore = editText_PoorScore.getText().toString().trim();
                String string_GreatScore = editText_GreatScore.getText().toString().trim();
                int num_PoorScore = Integer.parseInt(string_PoorScore);
                int num_GreatScore = Integer.parseInt(string_GreatScore);
                if(string_Name.isEmpty() || string_PoorScore.isEmpty() || string_GreatScore.isEmpty()){
                    Toast.makeText(ConfigData.this, "Please fill in all the places", Toast.LENGTH_SHORT).show();
                    return;
                }
                targetConfig = new Configuration(string_Name, num_PoorScore, num_GreatScore);
                configManager = ConfigManager.getInstance();
                configManager.editConfig(targetPosition, targetConfig);
                finish();//???
            }
        });
    }
}