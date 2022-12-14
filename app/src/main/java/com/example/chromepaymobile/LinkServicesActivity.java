package com.example.chromepaymobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chromepaymobile.Api.Network.AllUrl;

import org.json.JSONObject;

public class LinkServicesActivity extends AppCompatActivity {

    TextView fuseId;
    ImageView backImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_services);

        fuseId = findViewById(R.id.fuse_id);
        backImage = findViewById(R.id.back_img_linkServices);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("CustomerPreferences",MODE_PRIVATE);
        String id = sharedPreferences.getString("walletAddress","");

        fuseId.setText(id);
    }

    public void LinkedApi(){

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("CustomerPreferences",MODE_PRIVATE);
            String token = sharedPreferences.getString("token","");

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, AllUrl.ORGLinked + token, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.i("LINKE_VOLLEY", response);

                    try {

                        JSONObject jsonObject = new JSONObject(response);

                        boolean status = jsonObject.getBoolean("status");

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            requestQueue.add(stringRequest);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}