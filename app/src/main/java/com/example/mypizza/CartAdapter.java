package com.example.mypizza;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<Cart> cartList;

    double total = 0;
    double price1;
    List<Cart> cartlist;

    private OnItemClickListner mListner;

    public interface OnItemClickListner {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListner listener) {
        mListner = listener;
    }

    public CartAdapter(Context context, ArrayList<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cart_list, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {

        final Cart cart = cartList.get(position);

        String imageurl = cart.getImageUrl();
        String name1 = cart.getName();
        double totalprice = cart.getPrice();

        Glide.with(context).load(imageurl).into(holder.imageView);
        holder.name.setText(name1);
        holder.price.setText("RS. " + totalprice);

    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name;
        public TextView crust;
        public TextView extra;
        public TextView price;
        public ImageView delete;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            delete = (ImageView) itemView.findViewById(R.id.delete);



            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListner.onItemClick(position);

                        }
                    }
                }
            });


        }
    }

}
