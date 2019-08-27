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

import com.example.it592project.Adapter.EyeGlassesAdapter;
import com.example.it592project.Adapter.GlassAdapter;
import com.example.it592project.Adapter.LensAdapter;
import com.example.it592project.Adapter.SunGlassesAdapter;
import com.example.it592project.Model.EyeGlasses;
import com.example.it592project.Model.Glass;
import com.example.it592project.Model.Lens;
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

public class ProductsActivity extends AppCompatActivity {
    ProgressDialog prgDialog;
    ListView containerglasses;
    ListView containereyeglasses;
    ListView containerlenses;
    ListView containersunglasses;
    TextView textglass;
    TextView textsunglasses;
    TextView textlens;
    TextView texteyeglasses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FAE7E3"));
        containerglasses=findViewById(R.id.containerglasses);
        containereyeglasses=findViewById(R.id.containereyeglasses);
        containerlenses=findViewById(R.id.containerlenses);
        containersunglasses=findViewById(R.id.containersunglasses);
        textglass=findViewById(R.id.textglass);
        textsunglasses=findViewById(R.id.textsunglasses);
        textlens=findViewById(R.id.textlens);
        texteyeglasses=findViewById(R.id.texteyeglasses);

        ActionBar bar=getSupportActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Products");
        bar.setHomeAsUpIndicator(R.drawable.gozluk);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CD9489")));
        bar.show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        String shopname=getIntent().getStringExtra("shopname");
        ProductsTask task=new ProductsTask();
        String urlStr="http://10.0.2.2:8080/IT592-Project-Murat_Demirors/rest/ProductWebService/getAllProducts/"+shopname;
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

    class ProductsTask extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            prgDialog = new ProgressDialog(ProductsActivity.this);
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
                List<Glass>glasses=new ArrayList<>();

                for(int i=0;i<arr.length();i++){
                    JSONObject obj = arr.getJSONObject(i);
                    Glass glass=new Glass();

                    glass.setAddedDate(obj.getString("addedDate"));
                    glass.setAks(Integer.valueOf(obj.getString("aks")));
                    glass.setAntirefle(Boolean.valueOf(obj.getString("antirefle")));
                    glass.setBarcode_number(obj.getString("barcode_number"));
                    glass.setBrand(obj.getString("brand"));
                    glass.setColor(obj.getString("color"));
                    glass.setNumber(obj.getDouble("number"));
                    glass.setNumber_cyl(obj.getDouble("number_cyl"));
                    glass.setNumber_cyl_sferik_konkav(obj.getBoolean("number_cyl_sferik_konkav"));
                    glass.setNumber_sferik_konkav(obj.getBoolean("number_sferik_konkav"));
                    glass.setType(obj.getString("type"));

                    glasses.add(glass);
                }
                if(glasses.size() > 0 ){
                    textglass.setText("GLASS");
                }
                GlassAdapter adp=new GlassAdapter(ProductsActivity.this,glasses);
                containerglasses.setAdapter(adp);




                String eyeglassesData = object.getString("eyeglasses") ;
                JSONArray arr2 = new JSONArray(eyeglassesData);
                List<EyeGlasses>eyeglasses=new ArrayList<>();

                for(int i=0;i<arr2.length();i++){
                    JSONObject obj2 = arr2.getJSONObject(i);
                    EyeGlasses e=new EyeGlasses();

                    e.setAddedDate(obj2.getString("addedDate"));
                    e.setBarcode_number(obj2.getString("barcode_number"));
                    e.setBrand(obj2.getString("brand"));
                    e.setEyeglasses_colorcode(obj2.getString("eyeglasses_colorcode"));
                    e.setEyeglasses_modelcode(obj2.getString("eyeglasses_modelcode"));

                    eyeglasses.add(e);

                }
                if(eyeglasses.size() > 0){
                    texteyeglasses.setText("EYEGLASSES");
                }
                EyeGlassesAdapter adp2=new EyeGlassesAdapter(ProductsActivity.this,eyeglasses);
                containereyeglasses.setAdapter(adp2);


                String lensData = object.getString("lenses") ;
                JSONArray arr3 = new JSONArray(lensData);
                List<Lens>lenses=new ArrayList<>();

                for(int i=0;i<arr3.length();i++){
                    JSONObject obj3 = arr3.getJSONObject(i);
                    Lens lens=new Lens();

                    lens.setAddedDate(obj3.getString("addedDate"));
                    lens.setBarcode_number(obj3.getString("barcode_number"));
                    lens.setBrand(obj3.getString("brand"));
                    lens.setNumber(obj3.getDouble("number"));
                    lens.setNumber_cyl(obj3.getDouble("number_cyl"));
                    lens.setNumber_cyl_sferik_konkav(obj3.getBoolean("number_cyl_sferik_konkav"));
                    lens.setNumber_sferik_konkav(obj3.getBoolean("number_sferik_konkav"));


                    lenses.add(lens);
                }
                if(lenses.size() > 0 ){
                    textlens.setText("LENS");
                }

                LensAdapter adp3=new LensAdapter(ProductsActivity.this,lenses);
                containerlenses.setAdapter(adp3);

                String sunglassesData = object.getString("sunglasses") ;
                JSONArray arr4 = new JSONArray(sunglassesData);
                List<SunGlasses>sunGlasses=new ArrayList<>();

                for(int i=0;i<arr4.length();i++){
                    JSONObject obj4 = arr4.getJSONObject(i);
                    SunGlasses sun=new SunGlasses();

                    sun.setAddedDate(obj4.getString("addedDate"));
                    sun.setBarcode_number(obj4.getString("barcode_number"));
                    sun.setBrand(obj4.getString("brand"));
                    sun.setSunglasses_colorcode(obj4.getString("sunglasses_colorcode"));
                    sun.setSunglasses_modelcode(obj4.getString("sunglasses_modelcode"));

                    sunGlasses.add(sun);
                }
                if(sunGlasses.size() > 0 ){
                    textsunglasses.setText("SUNGLASSES");
                }

                SunGlassesAdapter adp4=new SunGlassesAdapter(ProductsActivity.this,sunGlasses);
                containersunglasses.setAdapter(adp4);


                ListUtils.setDynamicHeight(containerglasses);
                ListUtils.setDynamicHeight(containereyeglasses);
                ListUtils.setDynamicHeight(containerlenses);
                ListUtils.setDynamicHeight(containersunglasses);

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
