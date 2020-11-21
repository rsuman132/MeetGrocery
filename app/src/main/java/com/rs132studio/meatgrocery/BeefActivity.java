package com.rs132studio.meatgrocery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rs132studio.meatgrocery.Adaptor.BeefAdaptor;
import com.rs132studio.meatgrocery.Adaptor.MuttonAdaptor;
import com.rs132studio.meatgrocery.Model.ChickenCategory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class BeefActivity extends AppCompatActivity implements BeefAdaptor.RecyclerViewClickListner {
    private RecyclerView beefRecyclerView;
    private ArrayList<ChickenCategory> beefArray;
    private BeefAdaptor beefAdaptor;
    private LinearLayoutManager linearLayoutManager;
    private ImageView bActivityBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beef);
        beefRecyclerView = findViewById(R.id.beef_Recyclerview);
        bActivityBack = findViewById(R.id.bActivityBack);
        bActivityBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(BeefActivity.this, MainActivity.class);
                startActivity(backIntent);
                finish();
            }
        });

        muttonInfoList();
        setBeefAdaptor();
    }

    private void setBeefAdaptor() {
        beefAdaptor = new BeefAdaptor(this, beefArray, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        beefRecyclerView.setHasFixedSize(true);
        beefRecyclerView.setLayoutManager(linearLayoutManager);
        beefRecyclerView.setAdapter(beefAdaptor);
    }

    private void muttonInfoList() {
        beefArray = new ArrayList<>();
        beefArray.add(new ChickenCategory(R.drawable.bonelessbeef, "Boneless Beef Meat", "400",  " 1 "));
        beefArray.add(new ChickenCategory(R.drawable.beefmixed, "Mixed Beef Parts Meat", "350",  " 1 "));
        beefArray.add(new ChickenCategory(R.drawable.beefliver, "Beef Liver", "350",  " 1 "));
    }

    @Override
    public void onItemClick(View v, int position) {
        View viewItem = beefRecyclerView.getLayoutManager().findViewByPosition(position);
        ImageView breedImage = viewItem.findViewById(R.id.goat_img);
        TextView breedName = viewItem.findViewById(R.id.goat_breed);
        TextView breedRate = viewItem.findViewById(R.id.mutton_price);
        TextView breedQuantity = viewItem.findViewById(R.id.goat_Quality);

        //string conversion
        breedImage.buildDrawingCache();
        Bitmap bmap = breedImage.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        String sBreedName = breedName.getText().toString();
        String sBreedRate = breedRate.getText().toString();
        String sBreedQuantity = breedQuantity.getText().toString();
        Intent chickenDetailIntent = new Intent(BeefActivity.this, ChickenDetailPage.class);

        chickenDetailIntent.putExtra("breedimage", byteArray);
        chickenDetailIntent.putExtra("breedname", sBreedName);
        chickenDetailIntent.putExtra("breedrate", sBreedRate);
        chickenDetailIntent.putExtra("breedquantity", sBreedQuantity);
        startActivity(chickenDetailIntent);
        finish();
    }
}