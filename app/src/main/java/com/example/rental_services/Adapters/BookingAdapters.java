package com.example.rental_services.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rental_services.Database.BookingWithDetails;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.R;
import com.example.rental_services.ViewModels.ItemViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookingAdapters extends RecyclerView.Adapter<BookingAdapters.ViewHolder> {

    private List<BookingWithDetails> bookingList;
    private OnItemClickListener onItemClickListener;
    public BookingAdapters(List<BookingWithDetails> bookingList){this.bookingList = bookingList;}
    public void setTheItemList(List<BookingWithDetails> bookingList){
        this.bookingList = bookingList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_layout_booking, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productBookedName.setText(bookingList.get(position).getItemName());
//        if(bookingList.get(position).getItemBrand() == null){
//
//        }else{
//        holder.bookedProductBrand.setText(bookingList.get(position).getItemBrand().toString());}
        holder.bookedProductModel.setText(bookingList.get(position).getItemModel());
        holder.bookedtPrice.setText(bookingList.get(position).getItemPrice());
        holder.bookedFromDate.setText(bookingList.get(position).getFromDate().toString());
        holder.bookedToDate.setText(bookingList.get(position).getToDate().toString());
        holder.bookedPaymentPaidVia.setText(bookingList.get(position).getPayment());
        if(bookingList.get(position).getItemImagePath() == null){

        }else{
        Picasso.get()
                .load(bookingList.get(position).getItemImagePath())
                .into(holder.productImageBooked);}
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookedProductBrand, bookedProductModel,productBookedName, bookedFromDate, bookedToDate, bookedPaymentPaidVia, bookedShipmentDes, bookedtPrice;
        ImageView productImageBooked;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImageBooked = itemView.findViewById(R.id.productImageBooked);
            bookedProductModel = itemView.findViewById(R.id.bookedProductModel);
            productBookedName = itemView.findViewById(R.id.productBookedName);
            bookedFromDate = itemView.findViewById(R.id.bookedFromDate);
            bookedToDate = itemView.findViewById(R.id.bookedToDate);
            cardView = itemView.findViewById(R.id.listOfallBookedItems);
            bookedPaymentPaidVia = itemView.findViewById(R.id.bookedPaymentPaidVia);
            bookedShipmentDes = itemView.findViewById(R.id.bookedShipmentDes);
            bookedtPrice = itemView.findViewById(R.id.bookedtPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    onItemClickListener.onItemClick(bookingList.get(position));
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(BookingWithDetails item);
        void onDeleteClick(BookingWithDetails item);
        void onUpdateClick(BookingWithDetails item);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }
    public interface OnClickListener {
        void onClick(BookingWithDetails item);
    }

}
