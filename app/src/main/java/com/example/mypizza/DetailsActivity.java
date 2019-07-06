package com.example.mypizza;

import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
    CardView small;

    TextView pizzaname, pizzaprice, pizzadescription,  allprice1;

    ImageView pizzaimage;

    Button  Buy;

    String Pizzaname1;
    String PizzaDescription1;
    String Imageurl;
    Double Allprice;
    Double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        pizzaname = findViewById(R.id.name);

        pizzadescription = findViewById(R.id.description);
        pizzaprice = findViewById(R.id.price);
        allprice1 = findViewById(R.id.allprice);

        pizzaimage = findViewById(R.id.image);


        Buy = findViewById(R.id.addToCart);

        //getting data from homeActivity
        Intent intent = getIntent();
        final String imgurl = intent.getStringExtra("IMG");
        final Double pizzaprice1 = intent.getDoubleExtra("PRICE",0.00);
        final String pizzaname1 = intent.getStringExtra("NAME");
        final String pizzadescription1 = intent.getStringExtra("DETAILS");


        //assign homeActivity data to DetailsActivity's variables
        Imageurl = imgurl;
        PizzaDescription1 = pizzadescription1;
        Pizzaname1 = pizzaname1;
        price=pizzaprice1;


        //setting data to xml file
        Glide.with(DetailsActivity.this).load(imgurl).into(pizzaimage);
        pizzaname.setText(pizzaname1);
        pizzadescription.setText(pizzadescription1);
        pizzaprice.setText("Rs." + pizzaprice1);
        allprice1.setText("Rs. " + pizzaprice1);

        Allprice = pizzaprice1;

        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addcart();
                Intent intent1 = new Intent(DetailsActivity.this, CartActivity.class);
                finish();
                startActivity(intent1);

            }
        });
    }

    private void addcart() {
        String URL1 = "http://"+IPAddress.IPAddress+":8080/demo/addcart?cartId=&name="+Pizzaname1+"&imageUrl=" + Imageurl + "&price="+price+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}

