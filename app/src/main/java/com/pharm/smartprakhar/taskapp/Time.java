package com.pharm.smartprakhar.taskapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pharm.smartprakhar.taskapp.Adapters.Adapter_Gallery;
import com.pharm.smartprakhar.taskapp.Classes.Databasehelper;
import com.pharm.smartprakhar.taskapp.Entityclasses.Image;

import java.util.ArrayList;
import java.util.Calendar;

public class Time extends AppCompatActivity {
    Databasehelper db;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    LinearLayoutManager mLayoutManager;
    Adapter_Gallery adapter_gallery;
    TextView time1,timecount1;
    TextView time2,timecount2;
    TextView time3,timecount3;


    ArrayList<Image> imagelist=new ArrayList<>();
    LinearLayout parentlayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        time1=findViewById(R.id.time1);
        timecount1=findViewById(R.id.timecount1);

        time2=findViewById(R.id.time2);
        timecount2=findViewById(R.id.timecount2);

        time3=findViewById(R.id.time3);
        timecount3=findViewById(R.id.timecount3);
        Calendar calender=Calendar.getInstance();

        String currentdate = java.text.DateFormat.getDateInstance().format(calender.getTime());




        db=new Databasehelper(getApplicationContext());
        try {

            imagelist= db.getAllImage(currentdate);


        }
        catch(Exception e)
        {

        }
        //We take the list out from database and pass it to recycler view with a adapter and we use squillite databasehelper class


        recyclerView =  findViewById(R.id.recycler_view);
        recyclerView2 =  findViewById(R.id.recycler_view2);
        recyclerView3 =  findViewById(R.id.recycler_view3);
        mLayoutManager =

                new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        if(!(imagelist.size()==0))
        {
            time1.setText(currentdate);
            timecount1.setText(Integer.toString(imagelist.size()));

            try {
                adapter_gallery=new Adapter_Gallery(imagelist, getApplicationContext());
                recyclerView.setAdapter(adapter_gallery);

            }
            catch(Exception e)
            {

            }


        }
        calender.add(Calendar.DATE,-1);
         currentdate = java.text.DateFormat.getDateInstance().format(calender.getTime());
        addtime(time2,timecount2,recyclerView2,currentdate);

        calender.add(Calendar.DATE,-1);
        currentdate = java.text.DateFormat.getDateInstance().format(calender.getTime());
        addtime(time3,timecount3,recyclerView3,currentdate);




    }
 private void    addtime(TextView time,TextView timecount,RecyclerView recyclerView,String currentdate)
 {
     db=new Databasehelper(getApplicationContext());
     try {

         imagelist= db.getAllImage(currentdate);


     }
     catch(Exception e)
     {

     }
     mLayoutManager =

             new LinearLayoutManager(getApplicationContext());
     mLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
     recyclerView.setLayoutManager(mLayoutManager);
     if(!(imagelist.size()==0))
     {
         time.setText(currentdate);
         timecount.setText(Integer.toString(imagelist.size()));

         try {
             adapter_gallery=new Adapter_Gallery(imagelist, getApplicationContext());
             recyclerView.setAdapter(adapter_gallery);

         }
         catch(Exception e)
         {

         }


     }

 }


}
