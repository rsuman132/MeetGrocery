package com.rs132studio.meatgrocery.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rs132studio.meatgrocery.R;

import java.util.List;

public class MainListAdaptor extends RecyclerView.Adapter<MainListAdaptor.ViewHolder> {
    Context context;
    List<Integer> list_Images_All;
    List<String> list_Name;
    LayoutInflater layoutInflater;
    RecyclerViewClickListner listner;

public MainListAdaptor(Context context, List<Integer> list_Images_All, List<String> list_Name, RecyclerViewClickListner listner){
    this.context = context;
    this.list_Images_All = list_Images_All;
    this.list_Name = list_Name;
    this.layoutInflater = LayoutInflater.from(context);
    this.listner = listner;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

      holder.list_image.setImageResource(list_Images_All.get(position));
      holder.list_text.setText(list_Name.get(position));

    }

    @Override
    public int getItemCount() {
        return list_Name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       RelativeLayout list_mainRelativeLayout;
       ImageView list_image;
       TextView list_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            list_image = itemView.findViewById(R.id.list_image);
            list_text = itemView.findViewById(R.id.list_NameText);
            list_mainRelativeLayout = itemView.findViewById(R.id.list_mainRelativeLayout);
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
