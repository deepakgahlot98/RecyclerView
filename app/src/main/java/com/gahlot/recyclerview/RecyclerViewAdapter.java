package com.gahlot.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context context;

     public RecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context context) {
         this.mImageNames = mImageNames;
         this.mImages = mImages;
         this.context = context;
     }

     @NonNull
     @Override
     public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
         ViewHolder holder = new ViewHolder(view);
         return holder;
     }

    @Override
     public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
         Log.d(TAG, "onBindViewHolder: called");

         Glide.with(context).asBitmap().load(mImages.get(position)).into(holder.circleImageView);

         holder.image.setText(mImageNames.get(position));

         holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Log.d(TAG, "onClick: Clicked on " + mImageNames.get(position));
                 Toast.makeText(context, mImageNames.get(position), Toast.LENGTH_SHORT).show();
             }
         });
     }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView image;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.image);
            image = itemView.findViewById(R.id.image_name);
            relativeLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
