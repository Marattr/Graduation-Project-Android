package com.example.it592project.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.it592project.R;

public class ShopsActivity extends AppCompatActivity {
    String username;
    String shopname;
    Button btn_products;
    Button btn_sales;
    TextView txt_shopname;
    TextView txt_usernameorders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FAE7E3"));
        btn_products=findViewById(R.id.btn_products);
        btn_sales=findViewById(R.id.btn_sales);
        txt_shopname=findViewById(R.id.txt_shopname);
        txt_usernameorders=findViewById(R.id.txt_usernameorders);

        ActionBar bar=getSupportActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Home");
        bar.setHomeAsUpIndicator(R.drawable.gozluk);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CD9489")));
        bar.show();

    }

    @Override
    protected void onStart() {
        super.onStart();

        shopname=getIntent().getStringExtra("shopName");
        username=getIntent().getStringExtra("username");

        txt_shopname.setText(shopname);
        txt_usernameorders.setText(username);

        btn_products.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(ShopsActivity.this,ProductsActivity.class);
                i.putExtra("shopname",shopname);
                startActivity(i);
            }
        });

        btn_sales.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(ShopsActivity.this,OrdersActivity.class);
                i.putExtra("shopname",shopname);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.logout:
                Intent i=new Intent(this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                return true;

        }

        return true;
    }


}
