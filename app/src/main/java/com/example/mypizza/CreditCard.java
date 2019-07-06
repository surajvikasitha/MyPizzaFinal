package com.example.mypizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class CreditCard extends AppCompatActivity {

//    private CreditCard cardForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_form);

        final TextView price = (TextView) findViewById(R.id.payment_amount);
        TextView pricetext = (TextView) findViewById(R.id.payment_amount_holder);
        pricetext.setText("");
        price.setText("");


        CardForm cardForm =(CardForm) findViewById(R.id.cardform);

        TextView textDes=(TextView)findViewById(R.id.payment_amount);
        Button btnpay=(Button)findViewById(R.id.btn_pay);
        btnpay.setText(String.format("Pay Now!",textDes.getText()));


        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                Toast.makeText(CreditCard.this,"Your Order is Successfully Placed..! It will Receive Soon!",
                        Toast.LENGTH_SHORT).show();

            }
        });


    }
}
