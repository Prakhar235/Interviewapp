package com.pharm.smartprakhar.taskapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pharm.smartprakhar.taskapp.Classes.ImageSaver;
import com.pharm.smartprakhar.taskapp.Entityclasses.Image;
import com.pharm.smartprakhar.taskapp.R;

import java.util.ArrayList;


public class Adapter_Gallery extends RecyclerView.Adapter<Adapter_Gallery.ViewHolder> {

    private ArrayList<Image> arrayList;
    Context context;




    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageview;



        public ViewHolder(View itemView) {
            super(itemView);

            imageview =  itemView.findViewById(R.id.imageicon);




        }
    }



    public Adapter_Gallery(ArrayList<Image> arrayList, Context context) {


        this.arrayList = arrayList;

        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_image, parent, false);

        return new ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try
        {

     Bitmap bm=       new ImageSaver(context)
             .setFileName(arrayList.get(position).getPath())
             .setExternal(true)//image save in external directory or app folder default value is false
             .setDirectory("Imagedir")
             .load(); //
          holder.imageview.setImageBitmap(bm);



        }
        catch (Exception e)
        {

        }




    }

    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }


}
