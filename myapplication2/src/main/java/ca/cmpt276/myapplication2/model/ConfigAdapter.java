package ca.cmpt276.myapplication2.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ca.cmpt276.myapplication2.R;

public class ConfigAdapter extends ArrayAdapter<ConfigDisplay> {
    private Context context;
    private int resource;

    public ConfigAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ConfigDisplay> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent,false);
        ImageView image = convertView.findViewById(R.id.image_Config);
        TextView name = convertView.findViewById(R.id.name_Config);

        if(getItem(position).getImageDisplay() != null){
            byte[] Photo = getItem(position).getImageDisplay();
            Bitmap photo_bm = BitmapFactory.decodeByteArray(Photo, 0, Photo.length);
            image.setImageBitmap(photo_bm);
        }
        else{
            image.setImageResource(R.drawable.player);
        }

        name.setText(getItem(position).getNameDisplay());

        return convertView;
    }
}
