package ca.cmpt276.myapplication2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Photo extends AppCompatActivity {
    public final static int PERMISSION_CODE = 001;
    public final static int REQUEST_CODE = 002;
    private ImageView Photo;
    private Button TakePhoto;
    private Button SavePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Photo = findViewById(R.id.imv_photo);
        TakePhoto = findViewById(R.id.btn_takePhoto);
        SavePhoto = findViewById(R.id.btn_savePhoto);

        setContentView(R.layout.activity_photo);
        setPhotoClickCallback();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Photo = findViewById(R.id.imv_photo);
        TakePhoto = findViewById(R.id.btn_takePhoto);
        SavePhoto = findViewById(R.id.btn_savePhoto);

    }

    private void setPhotoClickCallback() {
        TakePhoto = findViewById(R.id.btn_takePhoto);
        TakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraPermission();
            }
        });
    }

    private void setSavePhotoClickCallback() {
        SavePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Photo.this, "Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void cameraPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            String[] s = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, s, PERMISSION_CODE);
        }
        else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_CODE){
            if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                openCamera();
            }
            else{
                Toast.makeText(this,"Please accept the Camera Permission for taking photo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openCamera(){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap image = (Bitmap) data.getExtras().get("data");
        Photo.setImageBitmap(image);
    }
}