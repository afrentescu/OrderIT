package com.example.orderit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DinerAdapter extends RecyclerView.Adapter<DinerAdapter.ViewHolder> {
    TextView tvTable, tvScanQR;
   String tableNumber;
    String restaurantNames[];
    int images[];
    Context context;
    public DinerAdapter(Context ct, String s1[], int img[] ){
        context =ct;
        restaurantNames = s1;
        images =img;
    }
public DinerAdapter onClickListener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View dinerView = inflater.inflate(R.layout.restaurantlayout, parent, false);
        ViewHolder holder = new ViewHolder(dinerView);
        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(restaurantNames[position]);
        holder.dinerImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public ImageView dinerImage;
        public Button btnScan;
        public ViewHolder(View itemView){
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvRestaurantTitle);
            dinerImage = (ImageView)itemView.findViewById(R.id.imageViewRestaurant);
            btnScan = (Button)itemView.findViewById(R.id.btnScanQR);
            tvTable =(TextView) itemView.findViewById(R.id.tvTableBNumber);
            tvScanQR= (TextView) itemView.findViewById(R.id.tvScanQR);
            tvTable.setText("Please enter your table number:");
            tvScanQR.setText("Scan the BAR CODE from the menu to order:");

   // context.startActivities(new Intent[]{intent});
btnScan.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      //  Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show();
        context.startActivity(new Intent(context, Scanner.class));
        tableNumber = (String) tvTable.getText();
        Intent intent = new Intent(context, Scanner.class);
        intent.putExtra("TABLENR", tableNumber);
    }
});
        }
    }


}
