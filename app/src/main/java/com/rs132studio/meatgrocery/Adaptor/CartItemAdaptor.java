package com.rs132studio.meatgrocery.Adaptor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rs132studio.meatgrocery.Data.DatabaseHandler;
import com.rs132studio.meatgrocery.Model.CartItem;
import com.rs132studio.meatgrocery.R;

import java.util.List;
import java.util.zip.Inflater;

public class CartItemAdaptor extends RecyclerView.Adapter<CartItemAdaptor.ViewHolder> {
    private Context context;
    private List<CartItem> cartItemsList;
    private DatabaseHandler db;

    public CartItemAdaptor(Context context, List<CartItem> cartItemsList) {
        this.context = context;
        this.cartItemsList = cartItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItemsList.get(position);
        holder.cartItemName.setText(cartItem.getMeatName());
        holder.cartItemRate.setText(cartItem.getMeatRate());
        holder.cartItemQuality.setText(cartItem.getMeatQuantity());
        holder.carItemDate.setText(cartItem.getAddDate());
    }

    @Override
    public int getItemCount() {
        return cartItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cartItemName, cartItemRate, cartItemQuality, carItemDate;
        ImageView cartItemEdit, cartItemDelete;
        int id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartItemName = itemView.findViewById(R.id.addedItemName);
            cartItemRate = itemView.findViewById(R.id.addedItemRate);
            cartItemQuality = itemView.findViewById(R.id.cartItemRate);
            carItemDate = itemView.findViewById(R.id.cartItemDate);
            cartItemEdit = itemView.findViewById(R.id.editCartItem);
            cartItemDelete = itemView.findViewById(R.id.deleteCartItem);

            cartItemEdit.setOnClickListener(this);
            cartItemDelete.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position;
            CartItem cartItem;
            switch (v.getId()) {
                case R.id.editCartItem:
                    //edit
                    position = getAdapterPosition();
                    cartItem = cartItemsList.get(position);
                    editItem(cartItem);
                    break;
                case R.id.deleteCartItem:
                    //delete
                    position = getAdapterPosition();
                    cartItem = cartItemsList.get(position);
                    deleteItem(cartItem.getId());
                    break;
            }
        }

        private void editItem(final CartItem cartItem) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.edit_database, null);

            final TextView editBreedName = view.findViewById(R.id.editBreedName);
            final EditText editPrice = view.findViewById(R.id.editPrice);
            final EditText editQuantity = view.findViewById(R.id.editQuantity);
            Button saveBtnEdit = view.findViewById(R.id.saveBtnEdit);
            Button cancelBtn = view.findViewById(R.id.cancelBtn);

            alertDialogBuilder.setView(view);
            final AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();

            saveBtnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db = new DatabaseHandler(context);
                    cartItem.setMeatName(cartItemName.getText().toString());
                    cartItem.setMeatRate(editPrice.getText().toString());
                    cartItem.setMeatQuantity(editQuantity.getText().toString());

                    if (!cartItemName.getText().toString().isEmpty()
                    && !editPrice.getText().toString().isEmpty() && !editQuantity.getText().toString().isEmpty()) {
                        db.updateCartItem(cartItem);
                        notifyItemChanged(getAdapterPosition(), cartItem);
                    } else {
                        Toast.makeText(context, "Something is missing", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            });
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        private void deleteItem(final int id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setIcon(R.drawable.ic_baseline_warning);
            alertDialogBuilder.setTitle("Delete");
            alertDialogBuilder.setMessage("Are you want to delete " + cartItemName.getText().toString());
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db = new DatabaseHandler(context);
                    db.deleteCartItem(id);
                    cartItemsList.remove(getAdapterPosition());
                    Toast.makeText(context, cartItemName.getText().toString() + " is removed", Toast.LENGTH_SHORT).show();
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
}
