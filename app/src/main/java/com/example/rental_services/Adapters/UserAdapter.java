package com.example.rental_services.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rental_services.Entities.User;
import com.example.rental_services.R;
import com.example.rental_services.ViewModels.UserViewModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private  Context context;
    private List<User> users;
    private OnClickListener listener;
    private UserViewModel uservm;
    private OnItemClickListener onItemClickListener;
    public void setTheUserList(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    public UserAdapter(Context context) {
        this.context = context;}
//    private void setOnClickListener(OnClickListener listener){
//        this.listener = listener;
//    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.all_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.email.setText(users.get(position).getEmail());
        holder.username.setText(users.get(position).getUsername());
        holder.name.setText(users.get(position).getFullName());
//        holder.address.setText((CharSequence) users.get(position).getAddress());
        holder.mobile.setText(users.get(position).getMobileNo());

//            @Override
//            public void onClick(View view){
//                int position = getAdapterPosition();
//                onItemClickListener.onItemClick(users.get(position));
//            }
        }

    @Override
    public int getItemCount() {
        if(users!= null)
            return users.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView email;
        TextView username;
        TextView name;
        TextView address;
        TextView mobile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            username = itemView.findViewById(R.id.username);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            mobile = itemView.findViewById(R.id.mobile);

        }
    }
    public interface OnItemClickListener{
        void onItemClick(User user);
        void onDeleteClick(User user);
        void onUpdateClick(User user);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }
    public interface OnClickListener {
        void onClick(User user);
    }
}
