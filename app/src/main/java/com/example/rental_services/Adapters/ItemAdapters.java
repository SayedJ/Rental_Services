package com.example.rental_services.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rental_services.DetailItemActivity;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.R;
import com.example.rental_services.ViewModels.ItemViewModel;
import com.example.rental_services.ViewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapters extends RecyclerView.Adapter<ItemAdapters.ViewHolder> {

    private Context context;
    private List<Item> items;
    private OnClickListener listener;
    private ItemViewModel itemvm;
    private Item item;
    private OnItemClickListener onItemClickListener;
    public ItemAdapters(Context context ){
        this.context = context;
    }
    public ItemAdapters(List<Item> items){
        this.items = items;
    }

    public void setTheItemList (List<Item> items){
                this.items = items;
                notifyDataSetChanged();
            }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.name.setText(items.get(position).getName());
       holder.brand.setText(items.get(position).getBrand());
       holder.model.setText(items.get(position).getModel());
       holder.price.setText(String.valueOf(items.get(position).getPrice()) + " kr/day");


//       holder.name.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               int positiont = holder.getAdapterPosition();
//               if(onItemClickListener != null && positiont != RecyclerView.NO_POSITION) {
//               }
//
//           }
//       });
//       holder.itemView.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               if(onItemClickListener != null && holder.getAdapterPosition() != RecyclerView.NO_POSITION){
//                   onItemClickListener.onItemClick(items.get(position));
//                   Intent intent = new Intent(context, DetailItemActivity.class);
//                   intent.putExtra("Number", items.get(position).getItemId());
//                   context.startActivity(intent);
//
//               }
//           }
//       });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, brand, model, price;
        public CardView cardview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.userProduct);
            brand = itemView.findViewById(R.id.userProductBrand);
            model = itemView.findViewById(R.id.userProductModel);
            price = itemView.findViewById(R.id.productPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                        onItemClickListener.onItemClick(items.get(position));
                    Toast.makeText(itemView.getContext(), "Thi is great" +items.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getBindingAdapterPosition();
//                    Context contex = itemView.getContext();
//                    Intent intent = new Intent(contex, DetailItemActivity.class);
//
//                    intent.putExtra("Number", position);
////                    intent.putExtra("Hyundai", name.getText());
////                    intent.putExtra("i30", brand.getText());
////                    intent.putExtra("Coupe", model.getText());
////                    intent.putExtra("price", price.getText().toString());
//                    contex.startActivity(intent);
//                }
//
//            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Item item);
        void onDeleteClick(Item item);
        void onUpdateClick(Item item);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }
    public interface OnClickListener {
        void onClick(Item item);
    }
}
