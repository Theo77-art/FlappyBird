package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.test.myrequest.MyRequest;

public class GameActivity extends AppCompatActivity {
    private ImageButton ushi;
    private Button submit_btn;
    private RequestQueue queue;
    private MyRequest request;
    private TextView numberText;
    private String userName;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ushi = findViewById(R.id.image_increase);
        submit_btn = findViewById(R.id.btn_submit2);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);
        userName = getIntent().getStringExtra("keypseudo");
        numberText = findViewById(R.id.number_text);
        i = 0;

        ushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                numberText.setText(String.valueOf(i));
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(String.valueOf(numberText.getText()));
                request.register(userName, number);



                Intent i = new Intent(getApplicationContext(),OtherActivity.class);
                i.putExtra("keypseudo",userName);
                i.putExtra("keyvalue",number);
                startActivity(i);
                finish();
            }
        });
    }
}