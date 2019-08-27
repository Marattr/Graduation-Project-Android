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
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.it592project.Adapter.EyeGlassesDetailAdapter;
import com.example.it592project.Adapter.GlassDetailAdapter;
import com.example.it592project.Model.EyeGlasses;
import com.example.it592project.Model.Glass;
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

public class PrescriptionSaleDetailActivity extends AppCompatActivity {

    ProgressDialog prgDialog;
    ListView containerdetailglasses;
    ListView containerdetaileyeglasses;
    TextView textdetailglass;
    TextView textdetailsunglasses;
    TextView textdetaillens;
    TextView textdetaileyeglasses;

    ListView containerdetailglassesreading;
    ListView containerdetaileyeglassesreading;
    TextView textdetailglassesreading;
    TextView textdetaileyeglassesreading;

    TextView txt_orderdetail_customername;
    TextView txt_orderdetailcustomerphone;
    TextView txt_orderdetail_prescripcription_date;
    TextView txt_orderdetail_doctorcode;
    TextView txt_orderdetail_doctorname;
    TextView txt_orderdetail_hospital;
    TextView txt_orderdetail_orderdate;
    TextView txt_orderdetail_paymenttype;
    TextView txt_orderdetail_totalbill;

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
        setContentView(R.layout.activity_order_detail);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FAE7E3"));
        containerdetailglasses=findViewById(R.id.containerdetailglasses);
        containerdetaileyeglasses=findViewById(R.id.containerdetaileyeglasses);
        textdetailglass=findViewById(R.id.textdetailglass);
        textdetailsunglasses=findViewById(R.id.textdetailsunglasses);
        textdetaillens=findViewById(R.id.textdetaillens);
        textdetaileyeglasses=findViewById(R.id.textdetaileyeglasses);
        containerdetailglassesreading=findViewById(R.id.containerdetailglassesreading);
        containerdetaileyeglassesreading=findViewById(R.id.containerdetaileyeglassesreading);
        textdetailglassesreading=findViewById(R.id.textdetailglassreading);
        textdetaileyeglassesreading=findViewById(R.id.textdetaileyeglassesreading);

        txt_orderdetail_customername=findViewById(R.id.txt_orderdetail_customername);
        txt_orderdetailcustomerphone=findViewById(R.id.txt_orderdetailcustomerphone);
        txt_orderdetail_prescripcription_date=findViewById(R.id.txt_orderdetail_prescripcription_date);
        txt_orderdetail_doctorcode=findViewById(R.id.txt_orderdetail_doctorcode);
        txt_orderdetail_doctorname=findViewById(R.id.txt_orderdetail_doctorname);
        txt_orderdetail_hospital=findViewById(R.id.txt_orderdetail_hospital);
        txt_orderdetail_orderdate=findViewById(R.id.txt_orderdetail_orderdate);
        txt_orderdetail_paymenttype=findViewById(R.id.txt_orderdetail_paymenttype);
        txt_orderdetail_totalbill=findViewById(R.id.txt_orderdetail_totalbill);

        ActionBar bar=getSupportActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Prescription Sale");
        bar.setHomeAsUpIndicator(R.drawable.gozluk);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CD9489")));
        bar.show();

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

        txt_orderdetail_customername.setText(custname);
        txt_orderdetailcustomerphone.setText(custphone);
        txt_orderdetail_prescripcription_date.setText(pdate);
        txt_orderdetail_doctorcode.setText(dcode);
        txt_orderdetail_doctorname.setText(dname);
        txt_orderdetail_hospital.setText(hos);
        txt_orderdetail_orderdate.setText(date);
        txt_orderdetail_paymenttype.setText(ptype);
        txt_orderdetail_totalbill.setText(bill);

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

    class OrderTask extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prgDialog = new ProgressDialog(PrescriptionSaleDetailActivity.this);
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

                String glassData = object.getString("glasses") ;
                JSONArray arr = new JSONArray(glassData);
                List<Glass> glasses=new ArrayList<>();
                List<Glass> glassesreading=new ArrayList<>();
                for(int i=0;i<arr.length();i++){
                    JSONObject obj = arr.getJSONObject(i);
                    Glass glass=new Glass();

                    glass.setAks(obj.getInt("aks"));
                    glass.setAntirefle(Boolean.valueOf(obj.getString("antirefle")));
                    glass.setBarcode_number(obj.getString("barcode_number"));
                    glass.setBrand(obj.getString("brand"));
                    glass.setColor(obj.getString("color"));
                    glass.setNumber(obj.getDouble("number"));
                    glass.setNumber_cyl(obj.getDouble("number_cyl"));
                    glass.setNumber_cyl_sferik_konkav(obj.getBoolean("number_cyl_sferik_konkav"));
                    glass.setNumber_sferik_konkav(obj.getBoolean("number_sferik_konkav"));
                    glass.setType(obj.getString("type"));
                    glass.setReading_glasses(obj.getBoolean("reading_glasses"));
                    glass.setRight_eye(obj.getBoolean("right_eye"));

                    if(glass.isReading_glasses()){
                        glassesreading.add(glass);
                    }else{
                        glasses.add(glass);
                    }
                }
                if(glasses.size() > 0 ){
                    textdetailglass.setText("GLASS");
                    GlassDetailAdapter adp=new GlassDetailAdapter(PrescriptionSaleDetailActivity.this,glasses);
                    containerdetailglasses.setAdapter(adp);
                }
                if(glassesreading.size() > 0 ){
                    textdetailglassesreading.setText("Reading GLASS");
                    GlassDetailAdapter adp2=new GlassDetailAdapter(PrescriptionSaleDetailActivity.this,glassesreading);
                    containerdetailglassesreading.setAdapter(adp2);
                }




                String eyeglassesData = object.getString("eyeglasses") ;
                JSONArray arr2 = new JSONArray(eyeglassesData);
                List<EyeGlasses>eyeglasses=new ArrayList<>();
                List<EyeGlasses>eyeglassesreading=new ArrayList<>();

                for(int i=0;i<arr2.length();i++){
                    JSONObject obj2 = arr2.getJSONObject(i);
                    EyeGlasses e=new EyeGlasses();

                    e.setBarcode_number(obj2.getString("barcode_number"));
                    e.setBrand(obj2.getString("brand"));
                    e.setEyeglasses_colorcode(obj2.getString("eyeglasses_colorcode"));
                    e.setEyeglasses_modelcode(obj2.getString("eyeglasses_modelcode"));
                    e.setReading_glasses(obj2.getBoolean("reading_glasses"));

                    if(e.isReading_glasses()){
                        eyeglassesreading.add(e);
                    }else{
                        eyeglasses.add(e);
                    }


                }
                if(eyeglasses.size() > 0){
                    textdetaileyeglasses.setText("EYEGLASSES");
                    EyeGlassesDetailAdapter adp3=new EyeGlassesDetailAdapter(PrescriptionSaleDetailActivity.this,eyeglasses);
                    containerdetaileyeglasses.setAdapter(adp3);
                }
                if(eyeglassesreading.size() > 0){
                    textdetaileyeglassesreading.setText("Reading GLASSES");
                    EyeGlassesDetailAdapter adp4=new EyeGlassesDetailAdapter(PrescriptionSaleDetailActivity.this,eyeglassesreading);
                    containerdetaileyeglassesreading.setAdapter(adp4);
                }




                ListUtils.setDynamicHeight(containerdetailglasses);
                ListUtils.setDynamicHeight(containerdetaileyeglasses);
                ListUtils.setDynamicHeight(containerdetailglassesreading);
                ListUtils.setDynamicHeight(containerdetaileyeglassesreading);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            prgDialog.dismiss();
        }
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {

                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }
}
