package com.rs132studio.meatgrocery.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.rs132studio.meatgrocery.Adaptor.CartItemAdaptor;
import com.rs132studio.meatgrocery.Data.DatabaseHandler;
import com.rs132studio.meatgrocery.Model.CartItem;
import com.rs132studio.meatgrocery.R;
import com.rs132studio.meatgrocery.Util.Constants;

import java.util.ArrayList;

public class CartItemActivity extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private ArrayList<CartItem> cartItems;
    private ArrayList<CartItem> listItems;
    private CartItemAdaptor cartItemAdaptor;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseHandler databaseHandler;
    private ImageView cartActivityBack;
    private Button deleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_item);

        cartActivityBack = findViewById(R.id.cartActivityBack);
        deleteAll = findViewById(R.id.delete_all);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
            }

        });

        databaseHandler = new DatabaseHandler(this);

        cartItems = new ArrayList<>();
        listItems = new ArrayList<>();

        cartRecyclerView = findViewById(R.id.cart_Recyclerview);
        cartRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(linearLayoutManager);

        cartItems = (ArrayList<CartItem>) databaseHandler.getAllCartItem();

        for (CartItem items : cartItems){
            CartItem cartItem = new CartItem();
            cartItem.setMeatName(items.getMeatName());
            cartItem.setMeatRate(items.getMeatRate());
            cartItem.setMeatQuantity(items.getMeatQuantity());
            cartItem.setId(items.getId());
            cartItem.setAddDate(items.getAddDate());

            listItems.add(cartItem);
        }

        cartItemAdaptor = new CartItemAdaptor(this, listItems);
        cartRecyclerView.setAdapter(cartItemAdaptor);
        cartItemAdaptor.notifyDataSetChanged();

    }
    private void deleteAll() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_warning);
        alertDialogBuilder.setTitle("Delete All");
        alertDialogBuilder.setMessage("Are you want to delete " + Constants.TABLE_NAME);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHandler = new DatabaseHandler(getApplicationContext());
                databaseHandler.deleteAll();
                Toast.makeText(CartItemActivity.this,   Constants.TABLE_NAME + " is removed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}