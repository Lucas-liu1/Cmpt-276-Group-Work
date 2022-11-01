package ca.cmpt276.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPlaysButton();
        setConfigButton();
        setHelpButton();


    }

    void setPlaysButton(){
        Button btn = (Button) findViewById(R.id.btnPlays);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GamePlays.class);
                startActivity(intent);
            }
        });
    }

    void setConfigButton(){
        Button btn = (Button) findViewById(R.id.btnConfig);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameConfig.class);
                startActivity(intent);
            }
        });
    }

    void setHelpButton(){
        Button btn = (Button) findViewById(R.id.btnHelp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Help.class);
                startActivity(intent);
            }
        });
    }
}