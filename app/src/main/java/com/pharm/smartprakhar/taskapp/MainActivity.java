package com.pharm.smartprakhar.taskapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pharm.smartprakhar.taskapp.Classes.Databasehelper;
import com.pharm.smartprakhar.taskapp.Classes.ImageSaver;
import com.pharm.smartprakhar.taskapp.Classes.Imageoperator;
import com.pharm.smartprakhar.taskapp.Entityclasses.Image;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Imageoperator imageoperator;
    Databasehelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button camera=findViewById(R.id.camera);
        Button gallery=findViewById(R.id.gallery);
        db=new Databasehelper(camera.getContext());
        Button savedimage=findViewById(R.id.savedimages);
          imageoperator=new Imageoperator(camera.getContext());
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dispatchTakePictureIntent(1);

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent(2);


            }
        });
        savedimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Time.class);
                startActivity(intent);


            }
        });

    }

    private void saveImage(Bitmap finalBitmap)  {
        int count=db.getImageCount()+1;
        new ImageSaver(this)
                .setFileName("image"+count+".png")
                .setExternal(true)//image save in external directory or app folder default value is false
                .setDirectory("Imagedir")
                .save(finalBitmap); //
        Image image=new Image();
        image.setImageid(Integer.toString(db.getImageCount()));
        String currentdate = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        Log.d("Date",currentdate);
        image.setTimestamp(currentdate);
        image.setPath("image"+count+".png");
        try {
            db.insertImage(image);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }




    static final int REQUEST_IMAGE_CAPTURE = 1;
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    int RESULT_LOAD_IMAGE=2;
    public void dispatchTakePictureIntent(int selectedvalue) {
        if(selectedvalue==2)
        {
            Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            i.setType("image/*");
            startActivityForResult(i, RESULT_LOAD_IMAGE);


        }
        else
        {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        }







    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (((requestCode == REQUEST_IMAGE_CAPTURE) || (requestCode == RESULT_LOAD_IMAGE)) && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Uri   selectedImage = data.getData();


            Bitmap imageBitmap = null;
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (imageBitmap != null) {
                Log.d("bitmap",imageBitmap.toString());
            }
            saveImage(imageBitmap);

        }


    }








}
