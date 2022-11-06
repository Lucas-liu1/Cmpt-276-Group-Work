package ca.cmpt276.myapplication2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Game;

public class CongratulationsFragment extends AppCompatDialogFragment {
    Game currentGame;

    public void setCurrentGame(Game newGame){
        currentGame= newGame;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the view to show
        ConfigManager GameConfigurations = ConfigManager.getInstance();
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.congratulate_message_layout,null);

        TextView tv = v.findViewById(R.id.statisticsText);
        tv.setText(String.format("%s",currentGame.getRecord()));

        ImageView leftIcon = v.findViewById(R.id.leftIcon);

        leftIcon.setImageDrawable(getResources().getDrawable(getResourceByLevel(),null));

        // Create a button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (getActivity() instanceof AddGamePlay) {
                    getActivity().finish();
                }
            }
        };

        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle(String.format("Congratulations: %s",currentGame.getLevel()))
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }


    private int getResourceByLevel(){
        switch (currentGame.getLevel()){
            case "Iron":
                return R.drawable.iron;
            case "Bronze":
                return R.drawable.bronze;
            case "Silver":
                return R.drawable.silver;
            case "Gold":
                return R.drawable.gold;
            case "Platinum":
                return R.drawable.platinum;
            case "Master":
                return R.drawable.master;
            case "Grandmaster":
                return R.drawable.grandmaster;
            default:
                return R.drawable.grandmaster;
        }


    }

}

