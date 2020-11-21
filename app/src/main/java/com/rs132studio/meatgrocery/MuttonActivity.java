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

import com.rs132studio.meatgrocery.Adaptor.MuttonAdaptor;
import com.rs132studio.meatgrocery.Model.ChickenCategory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MuttonActivity extends AppCompatActivity implements MuttonAdaptor.RecyclerViewClickListner{

    private RecyclerView muttonRecyclerView;
    private ArrayList<ChickenCategory> muttonList;
    private MuttonAdaptor muttonListAdaptor;
    private LinearLayoutManager linearLayoutManager;
    private ImageView mActivityBackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutton);
        muttonRecyclerView = findViewById(R.id.mutton_Recyclerview);

        mActivityBackBtn = findViewById(R.id.mActivityBack);
        mActivityBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chickenActivityBack = new Intent(MuttonActivity.this, MainActivity.class);
                startActivity(chickenActivityBack);
                finish();
            }
        });

        muttonInfoList();
        setMuttonAdaptor();
    }

    private void setMuttonAdaptor() {
        muttonListAdaptor = new MuttonAdaptor(this, muttonList, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        muttonRecyclerView.setHasFixedSize(true);
        muttonRecyclerView.setLayoutManager(linearLayoutManager);
        muttonRecyclerView.setAdapter(muttonListAdaptor);
    }

    private void muttonInfoList() {
        muttonList = new ArrayList<>();
        muttonList.add(new ChickenCategory(R.drawable.muttonboneless, "Boneless Mutton", "750",  " 1 "));
        muttonList.add(new ChickenCategory(R.drawable.muttonnalli, "Bone Mutton-Nalli", "700",  " 1 "));
        muttonList.add(new ChickenCategory(R.drawable.muttonleg, "Mutton Leg", "700",  " 1 "));
        muttonList.add(new ChickenCategory(R.drawable.muttonintestine, "Mutton Intestine", "750",  " 1 "));
        muttonList.add(new ChickenCategory(R.drawable.muttonbrain, "Mutton Brain", "800",  " 1 "));
        muttonList.add(new ChickenCategory(R.drawable.muttonliver, "Mutton Liver", "800",  " 1 "));
    }

    @Override
    public void onItemClick(View v, int position) {
        View viewItem = muttonRecyclerView.getLayoutManager().findViewByPosition(position);
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
        Intent chickenDetailIntent = new Intent(MuttonActivity.this, ChickenDetailPage.class);

        chickenDetailIntent.putExtra("breedimage", byteArray);
        chickenDetailIntent.putExtra("breedname", sBreedName);
        chickenDetailIntent.putExtra("breedrate", sBreedRate);
        chickenDetailIntent.putExtra("breedquantity", sBreedQuantity);
        startActivity(chickenDetailIntent);
        finish();
    }
}