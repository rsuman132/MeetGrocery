package com.rs132studio.meatgrocery.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rs132studio.meatgrocery.Adaptor.CartItemAdaptor;
import com.rs132studio.meatgrocery.Data.DatabaseHandler;
import com.rs132studio.meatgrocery.Model.CartItem;
import com.rs132studio.meatgrocery.R;

import java.util.ArrayList;

public class ChickenDetailPage extends AppCompatActivity {
    private TextView chickDetailBreedName, chickDetailBreedRate, chickDetailBreedQuantity, chickDetailIncreaseTextView;
    private ImageView chickDetailBackArrow, chickDetailBreedImage;
    private ImageButton chickDetailDecreaseBtn, chickDetailIncreaseBtn;
    private Button chickDetailAddToCartBTn;
    private Bundle chickDetailBundle;
    private View.OnClickListener buttonClick;
    int textcount = 1;
    int a, b, c;
    private int chickDetailRate;
    private DatabaseHandler databaseHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_detail_page);

        chickDetailBreedName = findViewById(R.id.chickenDetailBreedName);
        chickDetailBreedRate = findViewById(R.id.chickenDetailBreedRate);
        chickDetailBreedQuantity = findViewById(R.id.chickenDetailBreedQuantity);
        chickDetailIncreaseTextView = findViewById(R.id.increaseTextView);
        chickDetailBackArrow = findViewById(R.id.chickenDetailbackArrow);
        chickDetailBreedImage = findViewById(R.id.chickenDetailBreedImage);
        chickDetailDecreaseBtn = findViewById(R.id.decreaseBtn);
        chickDetailIncreaseBtn = findViewById(R.id.IncreaseBtn);
        chickDetailAddToCartBTn = findViewById(R.id.addToCart);

        databaseHandler = new DatabaseHandler(ChickenDetailPage.this);

        buttonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.decreaseBtn:
                        dectextCount();
                        break;
                    case R.id.IncreaseBtn:
                        inctextCount();
                        break;
                }

            }
        };

        chickDetailDecreaseBtn.setOnClickListener(buttonClick);
        chickDetailIncreaseBtn.setOnClickListener(buttonClick);

        chickDetailBundle = getIntent().getExtras();
        if (chickDetailBundle != null) {
            String chickDetailname = chickDetailBundle.getString("breedname");
            chickDetailRate = Integer.parseInt(chickDetailBundle.getString("breedrate"));
            String chickDetailQuantity = chickDetailBundle.getString("breedquantity");

            byte[] chickDetailImage = chickDetailBundle.getByteArray("breedimage");
            Bitmap bitmap = BitmapFactory.decodeByteArray(chickDetailImage, 0, chickDetailImage.length);

            chickDetailBreedImage.setImageBitmap(bitmap);
            chickDetailBreedName.setText(chickDetailname);
            chickDetailBreedRate.setText(String.valueOf(chickDetailRate));
            chickDetailBreedQuantity.setText(chickDetailQuantity);
        }

        chickDetailAddToCartBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCartDo(v);
            }
        });
    }

    private void addCartDo(View v) {
        if (!chickDetailBreedName.getText().toString().isEmpty() && !chickDetailBreedRate.getText().toString().isEmpty() &&
                !chickDetailBreedQuantity.getText().toString().isEmpty()) {
                saveItemToDatabase();
        }
    }

    private void saveItemToDatabase() {
        CartItem cartItem = new CartItem();
        String newName = chickDetailBreedName.getText().toString();
        String newRate = chickDetailBreedRate.getText().toString();
        String newQuantity = chickDetailBreedQuantity.getText().toString();

        cartItem.setMeatName(newName);
        cartItem.setMeatRate(newRate);
        cartItem.setMeatQuantity(newQuantity);

        databaseHandler.AddCartItem(cartItem);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent carListIntent = new Intent(ChickenDetailPage.this, CartItemActivity.class);
                startActivity(carListIntent);
            }
        }, 1000);
    }

    private void dectextCount() {

        if (textcount < 2){
            chickDetailIncreaseTextView.setText(Integer.toString(textcount));

        } else {
            textcount--;
            chickDetailIncreaseTextView.setText(Integer.toString(textcount));
        }
        chickDetailBreedQuantity.setText(Integer.toString(textcount));
        a = Integer.parseInt(chickDetailBreedQuantity.getText().toString());
        b = Integer.parseInt(chickDetailBreedRate.getText().toString());
        c = a * chickDetailRate;
        chickDetailBreedRate.setText(Integer.toString(c));
    }

    private void inctextCount() {
        textcount++;
        chickDetailIncreaseTextView.setText(Integer.toString(textcount));
        chickDetailBreedQuantity.setText(Integer.toString(textcount));
        chickDetailBreedQuantity.setText(Integer.toString(textcount));
        a = Integer.parseInt(chickDetailBreedQuantity.getText().toString());
        b = Integer.parseInt(chickDetailBreedRate.getText().toString());
        c = a * chickDetailRate;
        chickDetailBreedRate.setText(Integer.toString(c));
    }
}