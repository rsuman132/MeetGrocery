package com.rs132studio.meatgrocery.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import com.rs132studio.meatgrocery.Adaptor.ChickenListAdaptor;
import com.rs132studio.meatgrocery.Model.ChickenCategory;
import com.rs132studio.meatgrocery.R;

public class ChickenActivity extends AppCompatActivity implements ChickenListAdaptor.RecyclerViewClickListner {

    private RecyclerView chickenRecyclerView;
    private ArrayList<ChickenCategory> chickenList;
    private ChickenListAdaptor chickenListAdaptor;
    private LinearLayoutManager linearLayoutManager;
    private ImageView cActivityBackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken);
        chickenRecyclerView = findViewById(R.id.chicken_Recyclerview);

        cActivityBackBtn = findViewById(R.id.cActivityBack);
        cActivityBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chickenActivityBack = new Intent(ChickenActivity.this, MainActivity.class);
                startActivity(chickenActivityBack);
                finish();
            }
        });

        chickenInfoList();
        setChickenAdaptor();

    }

    private void setChickenAdaptor() {
        chickenListAdaptor = new ChickenListAdaptor(this, chickenList, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        chickenRecyclerView.setHasFixedSize(true);
        chickenRecyclerView.setLayoutManager(linearLayoutManager);
        chickenRecyclerView.setAdapter(chickenListAdaptor);
    }

    private void chickenInfoList() {
        chickenList = new ArrayList<>();
        chickenList.add(new ChickenCategory(R.drawable.broilerchicken, "Broiler Chicken or White Leghorn Chicken", "130",  "1"));
        chickenList.add(new ChickenCategory(R.drawable.countrychicken, "Country Chicken or Naattu Kozhi", "240",  "1"));
        chickenList.add(new ChickenCategory(R.drawable.goose, "Duck or Goose", "300",  "1"));
        chickenList.add(new ChickenCategory(R.drawable.broilereggs, "Broiler Chicken Eggs", "5",  "1"));
        chickenList.add(new ChickenCategory(R.drawable.countrychickeneggs, "Country Chicken Eggs", "10",  "1"));
    }

    @Override
    public void onItemClick(View v, int position) {
        View viewItem = chickenRecyclerView.getLayoutManager().findViewByPosition(position);
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
        Intent chickenDetailIntent = new Intent(ChickenActivity.this, ChickenDetailPage.class);

        chickenDetailIntent.putExtra("breedimage", byteArray);
        chickenDetailIntent.putExtra("breedname", sBreedName);
        chickenDetailIntent.putExtra("breedrate", sBreedRate);
        chickenDetailIntent.putExtra("breedquantity", sBreedQuantity);
        startActivity(chickenDetailIntent);
    }
}