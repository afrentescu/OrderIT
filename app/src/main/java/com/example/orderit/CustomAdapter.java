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
{
  private OnRestaurantListener restaurantListener;
    String restaurantNames[];
int images[];
Context context;
    public CustomAdapter(Context ct, String s1[], int img[], OnRestaurantListener restaurantListener ){
      context =ct;
     restaurantNames = s1;
     images =img;
     this.restaurantListener = restaurantListener;
}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
      View view =   inflater.inflate(R.layout.customlayoutmain, parent, false);

        return new MyViewHolder(view, restaurantListener);
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
     TextView tvCategory;
     ImageView myImage;
      OnRestaurantListener restListener;

        public MyViewHolder(@NonNull View itemView, OnRestaurantListener restListener) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategories);
            myImage =itemView.findViewById(R.id.imageView);
             this.restListener = restListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
          restListener.onRestaurantClick(getAdapterPosition());
        }
    }

    public interface OnRestaurantListener{
        void onRestaurantClick(int position);
    }
}
