package com.example.it592project.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.it592project.Adapter.OrderAdapter;
import com.example.it592project.Model.Order;
import com.example.it592project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {
    ListView containerorder;
    String shopname;
    ProgressDialog prgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        containerorder=findViewById(R.id.containerorder);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FAE7E3"));

        ActionBar bar=getSupportActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Orders");
        bar.setHomeAsUpIndicator(R.drawable.gozluk);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CD9489")));
        bar.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        shopname =getIntent().getStringExtra("shopname");
        String urlStr="http://10.0.2.2:8080/IT592-Project-Murat_Demirors/rest/OrderWebService/getAllOrders/"+shopname;
        OrderTask task=new OrderTask();
        task.execute(urlStr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                startActivity(new Intent(this,ShopsActivity.class));
                return true;

            case R.id.logout:
                Intent i=new Intent(this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                return true;

        }

        return true;
    }

    class OrderTask extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            prgDialog = new ProgressDialog(OrdersActivity.this);
            prgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            prgDialog.setTitle("Loading");
            prgDialog.setMessage("Please wait..");
            prgDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line = "";
                StringBuilder buffer = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                JSONObject object = new JSONObject(s);

                String orderData = object.getString("orders");
                JSONArray arr = new JSONArray(orderData);
                List<Order> orders = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    Order order = new Order();

                    String doctorCode=obj.getString("doctorCode");

                    if(!doctorCode.equals("0")){
                        String hospital=obj.getString("hospital");
                        String date=obj.getString("date");
                        String docName=obj.getString("doctorName");

                        order.setDoctorCode(doctorCode);
                        order.setHospital(hospital);
                        order.setDoctorName(docName);
                        order.setDate(date);
                    }

                    order.setCustomerNameLastname(obj.getString("customerNameLastname"));
                    order.setCustomerPhone(obj.getString("customerPhone"));
                    order.setOrderdate(obj.getString("orderdate"));
                    order.setPaymentType(obj.getString("paymentType"));
                    order.setSaleType(obj.getString("saleType"));
                    order.setTotalbill(Double.valueOf(obj.getString("totalbill")));
                    order.setId(Integer.valueOf(obj.getString("id")));

                    orders.add(order);
                }

                OrderAdapter adp = new OrderAdapter(OrdersActivity.this, orders);
                containerorder.setAdapter(adp);
                containerorder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Order order=(Order)containerorder.getAdapter().getItem(position);
                        if(order.getSaleType().equals("Prescription Sale")){
                            Intent i=new Intent(OrdersActivity.this, PrescriptionSaleDetailActivity.class);
                            i.putExtra("order",order);
                            startActivity(i);
                        }else if(order.getSaleType().equals("Lens Sale")){
                            Intent i=new Intent(OrdersActivity.this, LensSaleDetailActivity.class);
                            i.putExtra("order",order);
                            startActivity(i);
                        }else if(order.getSaleType().equals("SunGlasses Sale")){
                            Intent i=new Intent(OrdersActivity.this,SunGlassesSaleDetailActivity.class);
                            i.putExtra("order",order);
                            startActivity(i);
                        }

                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

            prgDialog.dismiss();
        }
    }
}
