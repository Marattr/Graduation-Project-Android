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

import com.example.it592project.Adapter.LensDetailAdapter;
import com.example.it592project.Model.Lens;
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

public class LensSaleDetailActivity extends AppCompatActivity {
    ProgressDialog prgDialog;
    TextView txt_lensdetail_customername;
    TextView txt_lensdetailcustomerphone;
    TextView txt_lensdetail_prescripcription_date;
    TextView txt_lensdetail_doctorcode;
    TextView txt_lensdetail_doctorname;
    TextView txt_lensdetail_hospital;
    TextView txt_lensdetail_orderdate;
    TextView txt_lensdetail_paymenttype;
    TextView txt_lensdetail_totalbill;

    TextView textdetaillens;
    ListView containerdetaillens;

    String custname;
    String custphone;
    String pdate;
    String dcode;
    String dname;
    String hos;
    String date;
    String ptype;
    String bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lens_sale_detail);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FAE7E3"));
        txt_lensdetail_customername=findViewById(R.id.txt_lensdetail_customername);
        txt_lensdetailcustomerphone=findViewById(R.id.txt_lensdetailcustomerphone);
        txt_lensdetail_prescripcription_date=findViewById(R.id.txt_lensdetail_prescripcription_date);
        txt_lensdetail_doctorcode=findViewById(R.id.txt_lensdetail_doctorcode);
        txt_lensdetail_doctorname=findViewById(R.id.txt_lensdetail_doctorname);
        txt_lensdetail_hospital=findViewById(R.id.txt_lensdetail_hospital);
        txt_lensdetail_orderdate=findViewById(R.id.txt_lensdetail_orderdate);
        txt_lensdetail_paymenttype=findViewById(R.id.txt_lensdetail_paymenttype);
        txt_lensdetail_totalbill=findViewById(R.id.txt_lensdetail_totalbill);
        textdetaillens=findViewById(R.id.textdetaillens);
        containerdetaillens=findViewById(R.id.containerdetaillens);

        ActionBar bar=getSupportActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Lens Sale");
        bar.setHomeAsUpIndicator(R.drawable.gozluk);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CD9489")));
        bar.show();
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

    @Override
    protected void onStart() {
        super.onStart();
        Order order=(Order)getIntent().getSerializableExtra("order");

        custname=order.getCustomerNameLastname();
        custphone=order.getCustomerPhone();
        pdate=order.getDate();
        dcode=order.getDoctorCode();
        dname=order.getDoctorName();
        hos=order.getHospital();
        date=order.getOrderdate();
        ptype=order.getPaymentType();
        bill=String.valueOf(order.getTotalbill());

        txt_lensdetail_customername.setText(custname);
        txt_lensdetailcustomerphone.setText(custphone);
        txt_lensdetail_prescripcription_date.setText(pdate);
        txt_lensdetail_doctorcode.setText(dcode);
        txt_lensdetail_doctorname.setText(dname);
        txt_lensdetail_hospital.setText(hos);
        txt_lensdetail_orderdate.setText(date);
        txt_lensdetail_paymenttype.setText(ptype);
        txt_lensdetail_totalbill.setText(bill);

        String urlStr="http://10.0.2.2:8080/IT592-Project-Murat_Demirors/rest/OrderWebService/getAllProductsByOrderId/"+order.getId();
        OrderTask task=new OrderTask();
        task.execute(urlStr);
    }

    class OrderTask extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prgDialog = new ProgressDialog(LensSaleDetailActivity.this);
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

                String lensData = object.getString("lenses") ;
                JSONArray arr = new JSONArray(lensData);
                List<Lens>lenses=new ArrayList<>();

                for(int i=0;i<arr.length();i++){
                    JSONObject obj = arr.getJSONObject(i);
                    Lens lens=new Lens();

                    lens.setBarcode_number(obj.getString("barcode_number"));
                    lens.setBrand(obj.getString("brand"));
                    lens.setNumber(obj.getDouble("number"));
                    lens.setNumber_cyl(obj.getDouble("number_cyl"));
                    lens.setNumber_cyl_sferik_konkav(obj.getBoolean("number_cyl_sferik_konkav"));
                    lens.setNumber_sferik_konkav(obj.getBoolean("number_sferik_konkav"));
                    lens.setRight_eye(obj.getBoolean("right_eye"));
                    lens.setAks(obj.getInt("aks"));

                    lenses.add(lens);
                }
                if(lenses.size() > 0  ){
                    textdetaillens.setText("LENS");
                }

                LensDetailAdapter adp=new LensDetailAdapter(LensSaleDetailActivity.this,lenses);
                containerdetaillens.setAdapter(adp);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            prgDialog.dismiss();
        }
    }

}
