package com.example.orderit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{ String restaurantNames[];
int images[];
Context context;
    public CustomAdapter(Context ct, String s1[], int img[] ){
      context =ct;
     restaurantNames = s1;
     images =img;
}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
      View view =   inflater.inflate(R.layout.customlayoutmain, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.tvCategory.setText(restaurantNames[position]);
    holder.myImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
     TextView tvCategory;
     ImageView myImage;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategories);
            myImage =itemView.findViewById(R.id.imageView);
        }
    }
}
