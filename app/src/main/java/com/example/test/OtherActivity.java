package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.myrequest.MyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OtherActivity extends AppCompatActivity {
    //private MyRequest request;
    private Button testBtn;
    RecyclerView recyclerView;
    Adapter adapter;
    String url = "https://test-android-php.000webhostapp.com/users.json";
    List<Users> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        recyclerView = findViewById(R.id.recycler_view);
        users = new ArrayList<>();
        getScores();



    }

    private void getScores() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest json = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.d("ERRORRRR", "Test");
                for (int i = 0; i < response.length(); i++) {
                    //Log.d("ERRORRRR", "In the loop");
                    int j = i+1;
                    try {
                        //Log.d("ERRORRRR", "success" + i);
                        JSONObject userObj = response.getJSONObject(i);
                        Users user = new Users();
                        user.setPosition(j);
                        user.setNameUser(userObj.getString("name").toString());
                        user.setScoreUser(userObj.getInt("number"));

                        //Log.d("ERRORRRR", "success creating view");
                        users.add(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //Log.d("ERRORRRR", "success for the loop");
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), users);
                recyclerView.setAdapter(adapter);
                //Log.d("ERRORRRR", "success the 3 lines of onResponse");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("ERRORRRR", "onErrorResponse: " + error);
            }
        });
        //Log.d("ERRORRRR", "success onResponse");
        queue.add(json);
        //Log.d("ERRORRRR", "adding to queue");
    }
}