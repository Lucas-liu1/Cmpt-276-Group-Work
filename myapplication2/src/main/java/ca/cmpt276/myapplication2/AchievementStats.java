package ca.cmpt276.myapplication2;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import ca.cmpt276.myapplication2.model.Achievement;
import ca.cmpt276.myapplication2.model.AchievementList;
import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Configuration;


public class AchievementStats extends AppCompatActivity{
    private static ConfigManager configManager; // = ConfigManager.getInstance();
    private static int curConfigurationIndex;
    ArrayList<Achievement> tempList;
    ArrayList<String> xLabels = new ArrayList<>();
    ArrayList barList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configManager = ConfigManager.getInstance();
        setContentView(R.layout.activity_achievement_stats);
        setTitle("Achievement Statistics");
        ActionBar back = getSupportActionBar();
        assert back != null;
        back.setDisplayHomeAsUpEnabled(true);
        extractIDFromIntent();
        tempList = configManager.getConfigList().get(curConfigurationIndex).
                getGamesList().get(0).getAchievementList().getAchievementsList();
        for (int i = 0; i< tempList.size();i++){
            xLabels.add(tempList.get(i).getName());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        configManager = ConfigManager.getInstance();
        BarChart barChart = findViewById(R.id.barChart);
        retrieveData();
        BarDataSet barDataSet = new BarDataSet(barList, "SCORE LEVEL");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

    }

    private void extractIDFromIntent() {
        Intent intent = getIntent();
        curConfigurationIndex = intent.getIntExtra("position", -1);
    }

    private void retrieveData() {
        barList = new ArrayList();
        String toDisplay = "Historical Achievements:\n";
        ArrayList data = configManager.getConfigList().get(curConfigurationIndex).getConfigurationHistoryStats();
        for(int i = 0; i < 8; i++) {
            int value = (int) data.get(i);
            Log.i("RETRIEVEDATA",""+value);
            toDisplay = toDisplay + tempList.get(i).getName() + ": " + value +"\n";
            barList.add(new BarEntry(2f+i*2f, value));
        }
        TextView tv = findViewById(R.id.historicalAchievementText);
        tv.setText(toDisplay);
    }
}
