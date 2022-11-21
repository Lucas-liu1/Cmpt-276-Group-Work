package ca.cmpt276.myapplication2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import ca.cmpt276.myapplication2.model.ConfigManager;
import ca.cmpt276.myapplication2.model.Game;

/**
 * Simple fragment that displays your achievement level based on the score you chose
 * This builds off of addgameplay and uses the achievement list to properly decide which
 * achievement level you landed on.
 * Provides linking to drawable images to showcase the achievement level you got
 */
public class CongratulationsFragment extends AppCompatDialogFragment {
    private Game currentGame;
    Context passedContext;
    private int configID;
    private int gameID;
    private int themeID;

    public void setCurrentGame(Context context, Game newGame, int configurationID, int gameid,int themeid){
        passedContext=context;
        currentGame= newGame;
        configID=configurationID;
        gameID=gameid;
        themeID=themeid;
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

        leftIcon.setImageDrawable(getResources().getDrawable(getResourceByLevel(),null ));

        // Create a button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (getActivity() instanceof AddGamePlay || getActivity() instanceof EditGamePlay) {
                    getActivity().finish();
                }
            }
        };
        //add sound
        MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(),R.raw.win);
        mediaPlayer.start();


        // Create an edit button listener
        DialogInterface.OnClickListener edit_listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = EditGamePlay.makeIntent(passedContext, configID,gameID,themeID);
                startActivity(intent);
            }
        };


        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle(String.format("Congratulations: %s",currentGame.getLevel()))
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .setNeutralButton("edit",edit_listener)
                .create();
    }


    private int getResourceByLevel(){
        switch (currentGame.getLevel()){
            //
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
            case "Diamond":
                return R.drawable.diamond;
            case "Master":
                return R.drawable.master;
            case "Grandmaster":
                return R.drawable.grandmaster;
          //theme 2
            case "Bike":
                return R.drawable.bike;
            case "Motorbike":
                return R.drawable.motorbike;
            case "Car":
                return R.drawable.car;
            case "Bus":
                return R.drawable.bus;
            case "Train":
                return R.drawable.train;
            case "Yacht":
                return R.drawable.yacht;
            case "Helicopter":
                return R.drawable.helicopter;
            case "Plane":
                return R.drawable.plane;
            //theme 3
            case "Bird":
                return R.drawable.bird;
            case "Dog":
                return R.drawable.dog;
            case "Sheep":
                return R.drawable.sheep;
            case "Zebra":
                return R.drawable.zebra;
            case "Lion":
                return R.drawable.lion;
            case "Tiger":
                return R.drawable.tiger;
            case "Bear":
                return R.drawable.bear;
            case "Elephant":
                return R.drawable.elephant;

            default:
                return R.drawable.grandmaster;
        }


    }

}

