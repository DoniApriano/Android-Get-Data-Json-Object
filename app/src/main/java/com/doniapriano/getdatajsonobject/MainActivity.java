package com.doniapriano.getdatajsonobject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvData;
    RecyclerView.Adapter adData;
    List<DataModel> dataModels;
    private String url = "https://mdn.github.io/learning-area/javascript/apis/fetching-data/can-store/products.json";
    RecyclerView.LayoutManager lmData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvData = findViewById(R.id.rv_data);

        getData();
    }

    private void getData() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dataModels = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        DataModel dataModel = new DataModel();
                        dataModel.setName(jsonObject.getString("name"));
                        dataModel.setType(jsonObject.getString("type"));
                        dataModels.add(dataModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                lmData = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                rvData.setLayoutManager(lmData);
                adData = new AdapterData(MainActivity.this,dataModels);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error " + error, Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);

    }
}