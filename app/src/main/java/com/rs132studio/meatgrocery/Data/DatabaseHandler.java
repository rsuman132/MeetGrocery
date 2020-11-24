package com.rs132studio.meatgrocery.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.rs132studio.meatgrocery.Model.CartItem;
import com.rs132studio.meatgrocery.Util.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    Context context;
    String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

    public DatabaseHandler(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CART_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "(" + Constants.KEY_ID + " INTEGER PRIMARY KEY," + Constants.KEY_MEAT_NAME
                + " TEXT," + Constants.KEY_MEAT_RATE + " TEXT," + Constants.KEY_MEAT_QUANTITY + " TEXT," + Constants.KEY_DATE_ADDED + " LONG);";
        db.execSQL(CREATE_CART_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);

    }

    public void AddCartItem(CartItem cartItem) {
        //Add Items
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_MEAT_NAME, cartItem.getMeatName());
        values.put(Constants.KEY_MEAT_RATE, cartItem.getMeatRate());
        values.put(Constants.KEY_MEAT_QUANTITY, cartItem.getMeatQuantity());
        values.put(Constants.KEY_DATE_ADDED, mydate);  //java.lang.System.currentTimeMillis()

        db.insert(Constants.TABLE_NAME, null, values);
    }

    public CartItem getCartItem(int id) {
        //get any one item
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID, Constants.KEY_MEAT_NAME, Constants.KEY_MEAT_RATE, Constants.KEY_MEAT_QUANTITY, Constants.KEY_DATE_ADDED},
                Constants.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
            CartItem cartItem = new CartItem();
            cartItem.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            cartItem.setMeatName(cursor.getString(cursor.getColumnIndex(Constants.KEY_MEAT_NAME)));
            cartItem.setMeatRate(cursor.getString(cursor.getColumnIndex(Constants.KEY_MEAT_RATE)));
            cartItem.setMeatQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_MEAT_QUANTITY)));
            cartItem.setAddDate(cursor.getString(cursor.getColumnIndex(Constants.KEY_DATE_ADDED)));

        return cartItem;
    }

    public List<CartItem> getAllCartItem() {
        //get all items
        SQLiteDatabase db = this.getWritableDatabase();

        List<CartItem> cartItemList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[] {Constants.KEY_ID, Constants.KEY_MEAT_NAME, Constants.KEY_MEAT_RATE, Constants.KEY_MEAT_QUANTITY, Constants.KEY_DATE_ADDED},
                null, null, null, null, Constants.KEY_DATE_ADDED + " DESC");

        if (cursor.moveToFirst()){
            do {
                CartItem cartItem = new CartItem();
                cartItem.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                cartItem.setMeatName(cursor.getString(cursor.getColumnIndex(Constants.KEY_MEAT_NAME)));
                cartItem.setMeatRate(cursor.getString(cursor.getColumnIndex(Constants.KEY_MEAT_RATE)));
                cartItem.setMeatQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_MEAT_QUANTITY)));
                cartItem.setAddDate(cursor.getString(cursor.getColumnIndex(Constants.KEY_DATE_ADDED)));

                cartItemList.add(cartItem);
            }while (cursor.moveToNext());

        }
        return cartItemList;
    }

    public int updateCartItem (CartItem cartItem){
        //update item
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_MEAT_NAME, cartItem.getMeatName());
        values.put(Constants.KEY_MEAT_RATE, cartItem.getMeatRate());
        values.put(Constants.KEY_MEAT_QUANTITY, cartItem.getMeatQuantity());
        values.put(Constants.KEY_DATE_ADDED, mydate);  //java.lang.System.currentTimeMillis()

        return db.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?", new String[] { String.valueOf(cartItem.getId())});
    }

    public void deleteCartItem (int id) {
        //delete item
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + "=?", new String[] {String.valueOf(id)});
        db.close();
    }

    public int CountCartItem(){
        //get count of items
        String queryCount = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryCount, null);
        return cursor.getCount();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ Constants.TABLE_NAME);
        db.close();
    }
}

