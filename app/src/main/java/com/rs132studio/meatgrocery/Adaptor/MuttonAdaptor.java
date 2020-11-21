package com.rs132studio.meatgrocery.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rs132studio.meatgrocery.Model.ChickenCategory;
import com.rs132studio.meatgrocery.R;

import java.util.ArrayList;

public class MuttonAdaptor extends RecyclerView.Adapter<MuttonAdaptor.ViewHolder> {
    Context context;
    private ArrayList<ChickenCategory> mMuttonList;
    private LayoutInflater layoutInflater;
    MuttonAdaptor.RecyclerViewClickListner listner;

    public MuttonAdaptor(Context context, ArrayList<ChickenCategory> mMuttonList, RecyclerViewClickListner listner){
        this.context = context;
        this.mMuttonList = mMuttonList;
        this.listner = listner;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.card_mutton_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChickenCategory currentChickenList = mMuttonList.get(position);
        holder.breedImages_TextView.setImageResource(currentChickenList.getChickenImage());
        holder.breedNames_Textview.setText(currentChickenList.getChickenBreed());
        holder.breedRate_Textview.setText(currentChickenList.getChickenRate());
        holder.breedQuality_Textview.setText(currentChickenList.getChickenQuantity());
    }

    @Override
    public int getItemCount() {
        return mMuttonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView breedImages_TextView;
        TextView breedNames_Textview, breedRate_Textview, breedQuality_Textview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            breedImages_TextView = itemView.findViewById(R.id.goat_img);
            breedNames_Textview = itemView.findViewById(R.id.goat_breed);
            breedRate_Textview = itemView.findViewById(R.id.mutton_price);
            breedQuality_Textview = itemView.findViewById(R.id.goat_Quality);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listner.onItemClick(v, getAdapterPosition());
        }
    }
    public interface RecyclerViewClickListner {
        void onItemClick(View v, int position);
    }
}
