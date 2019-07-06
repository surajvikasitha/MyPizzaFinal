package com.example.mypizza;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PizzaAdapter.OnItemClickListner {

    private static final String URL_DATA = "http://"+IPAddress.IPAddress+":8080/demo/all";

    RecyclerView recyclerView;
    PizzaAdapter adapter;

    List<Pizza> productslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productslist = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadRecyclerviewData();

        ImageView cart=(ImageView)findViewById(R.id.cart);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });



    }


    private void loadRecyclerviewData(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray products  = new JSONArray(response);

                    for (int i =0; i<products.length(); i++){

                        JSONObject productobject  = products.getJSONObject(i);

                        int id = productobject.getInt("pizzaId");
                        String name = productobject.getString("name");
                        String description = productobject.getString("description");
                        Double price = productobject.getDouble("price");
                        String imageurl = productobject.getString("imageUrl");

                        Pizza product = new Pizza(id, name, description, price, imageurl);
                        productslist.add(product);

                    }

                    adapter = new PizzaAdapter(MainActivity.this, productslist);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemCliclListener(MainActivity.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    //send data to detail activity

    @Override
    public void onItemClick(int position) {

        Intent detailintent = new Intent(this, DetailsActivity.class);
        Pizza clickItem = productslist.get(position);

        detailintent.putExtra("NAME", clickItem.getName());
        detailintent.putExtra("DETAILS", clickItem.getDescription());
        detailintent.putExtra("PRICE", clickItem.getPrice());
        detailintent.putExtra("IMG", clickItem.getImgurl());

        startActivity(detailintent);


    }

}
