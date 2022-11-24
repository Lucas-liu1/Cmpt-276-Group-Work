package ca.cmpt276.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * this class is about the about screen, this page shows the author's name
 * as well as all the resources we used
 */
public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About Info");
    }
}