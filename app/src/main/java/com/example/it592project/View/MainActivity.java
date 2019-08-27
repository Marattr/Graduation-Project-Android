package com.example.it592project.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.it592project.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText txt_username;
    EditText txt_password;
    ImageView img;
    Button btn_login;
    ProgressDialog prgDialog;
    int serviceMessageCode;
    String shopname;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#FAE7E3"));

        txt_username=findViewById(R.id.txt_username);
        txt_password=findViewById(R.id.txt_password);
        img=findViewById(R.id.img_main);
        img.setImageResource(R.drawable.mainpicture);
        btn_login=findViewById(R.id.btn_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn_login.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        username=txt_username.getText().toString();
                        password=txt_password.getText().toString();
                UserCheckTask task=new UserCheckTask();
                task.execute("http://10.0.2.2:8080/IT592-Project-Murat_Demirors/rest/UserWebService/checkUser",username,password);
            }
        });
    }

    class UserCheckTask extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            prgDialog = new ProgressDialog(MainActivity.this);
            prgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            prgDialog.setTitle("Loading");
            prgDialog.setMessage("Please wait..");
            prgDialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            String urlStr=strings[0];

            String username=strings[1];
            String password=strings[2];

            JSONObject object=new JSONObject();
            try {

                object.put("username",username);
                object.put("password",password);

                URL url=new URL(urlStr);
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestProperty("Content-Type","application/JSON");
                conn.setFixedLengthStreamingMode(object.toString().getBytes().length);

                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

                writer.write(object.toString());
                writer.flush();

                BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line="";
                StringBuilder buffer=new StringBuilder();
                while((line=reader.readLine())!=null){
                    buffer.append(line);

                }

                return buffer.toString();

            }catch (JSONException e) {
                e.printStackTrace();

            }catch (MalformedURLException e2){

                e2.printStackTrace();
            } catch (IOException e3){

                e3.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject json=new JSONObject(s);
                serviceMessageCode=Integer.valueOf(json.getString("serviceMessageCode"));

                if(serviceMessageCode==1){

                    shopname=json.getString("shopName");
                    Intent i=new Intent(MainActivity.this, ShopsActivity.class);
                    i.putExtra("username",username);
                    i.putExtra("shopName",shopname);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"Please enter username and password again!",Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            prgDialog.dismiss();
        }
    }
}
