package com.rs132studio.meatgrocery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.rs132studio.meatgrocery.Adaptor.MainListAdaptor;

public class MainActivity extends AppCompatActivity implements MainListAdaptor.RecyclerViewClickListner{

    private RecyclerView mainRecyclerView;
    List<Integer> logo_images;
    List<String> title_name;
    MainListAdaptor mainListAdaptor;
    GridLayoutManager gridLayoutManager;
    AlertDialog.Builder alertDialogBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecyclerView = findViewById(R.id.main_RecyclerView);
        logo_images = new ArrayList<>();
        title_name = new ArrayList<>();

        logo_images.add(R.drawable.chicken);
        logo_images.add(R.drawable.goat);
        logo_images.add(R.drawable.cow);
        logo_images.add(R.drawable.pig);
        logo_images.add(R.drawable.fish);
        logo_images.add(R.drawable.prawn);

        title_name.add("Chicken");
        title_name.add("Mutton");
        title_name.add("Beef");
        title_name.add("Pork");
        title_name.add("Sea Fish");
        title_name.add("Shell Fishes");

        mainListAdaptor = new MainListAdaptor(this, logo_images, title_name, this);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mainRecyclerView.setLayoutManager(gridLayoutManager);
        mainRecyclerView.setAdapter(mainListAdaptor);

    }

    @Override
    public void onItemClick(View v, int position) {
        View viewItem = mainRecyclerView.getLayoutManager().findViewByPosition(position);
        TextView textView = viewItem.findViewById(R.id.list_NameText);
        String name = textView.getText().toString();

        if(name.equals("Chicken")) {
            Intent chickenIntent = new Intent(MainActivity.this, ChickenActivity.class);
            startActivity(chickenIntent);
        }else if (name.equals("Mutton")){
            Intent muttonIntent = new Intent(MainActivity.this, MuttonActivity.class);
            startActivity(muttonIntent);
        }else if (name.equals("Beef")){
            Intent beefActivityIntent = new Intent(MainActivity.this, BeefActivity.class);
            startActivity(beefActivityIntent);
        }else {
            alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setIcon(R.drawable.ic_baseline_error);
            alertDialogBuilder.setTitle("Currently Unavailable");
            alertDialogBuilder.setMessage("Sorry " + name + " item currently unavailable right now. Please shop with available item");
            alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}