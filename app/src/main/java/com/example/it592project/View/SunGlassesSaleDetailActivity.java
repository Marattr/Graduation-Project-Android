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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it592project.Adapter.SunGlassesDetailAdapter;
import com.example.it592project.Model.Order;
import com.example.it592project.Model.SunGlasses;
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

public class SunGlassesSaleDetailActivity extends AppCompatActivity {
    ProgressDialog prgDialog;
    TextView txt_sunsdetail_customername;
    TextView txt_sunsdetailcustomerphone;
    TextView txt_sundetail_orderdate;
    TextView txt_sundetail_paymenttype;
    TextView txt_sundetail_totalbill;

    ListView container_sunglasses_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun_glasses_sale_detail);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FAE7E3"));
        container_sunglasses_detail=findViewById(R.id.container_sunglasses_detail);
        txt_sunsdetail_customername=findViewById(R.id.txt_sundetail_customername);
        txt_sunsdetailcustomerphone=findViewById(R.id.txt_sundetailcustomerphone);
        txt_sundetail_orderdate=findViewById(R.id.txt_sundetail_orderdate);
        txt_sundetail_paymenttype=findViewById(R.id.txt_sundetail_paymenttype);
        txt_sundetail_totalbill=findViewById(R.id.txt_sundetail_totalbill);

        ActionBar bar=getSupportActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("SunGlasses Sale");
        bar.setHomeAsUpIndicator(R.drawable.gozluk);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CD9489")));
        bar.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Order order=(Order)getIntent().getSerializableExtra("order");
        txt_sunsdetail_customername.setText(order.getCustomerNameLastname());
        txt_sunsdetailcustomerphone.setText(order.getCustomerPhone());
        txt_sundetail_orderdate.setText(order.getOrderdate());
        txt_sundetail_paymenttype.setText(order.getPaymentType());
        txt_sundetail_totalbill.setText(String.valueOf(order.getTotalbill()));

        String urlStr="http://10.0.2.2:8080/IT592-Project-Murat_Demirors/rest/OrderWebService/getAllProductsByOrderId/"+order.getId();
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
            super.onPreExecute();
            prgDialog = new ProgressDialog(SunGlassesSaleDetailActivity.this);
            prgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            prgDialog.setTitle("Loading");
            prgDialog.setMessage("Please wait..");
            prgDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line = "";
                StringBuilder buffer = new StringBuilder();
                while((line=reader.readLine())!=null){
                    buffer.append(line);

                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                JSONObject object = new JSONObject(s);

                String sunData = object.getString("sunglasses") ;
                JSONArray arr = new JSONArray(sunData);
                List<SunGlasses> sunglasses=new ArrayList<>();

                for(int i=0;i<arr.length();i++){
                    JSONObject obj = arr.getJSONObject(i);
                    SunGlasses sun=new SunGlasses();

                    sun.setBarcode_number(obj.getString("barcode_number"));
                    sun.setBrand(obj.getString("brand"));
                    sun.setSunglasses_modelcode(obj.getString("sunglasses_modelcode"));
                    sun.setSunglasses_colorcode(obj.getString("sunglasses_colorcode"));
                    sunglasses.add(sun);
                }

                SunGlassesDetailAdapter adp=new SunGlassesDetailAdapter(SunGlassesSaleDetailActivity.this,sunglasses);
                container_sunglasses_detail.setAdapter(adp);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            prgDialog.dismiss();
        }
    }
}
