package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
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
   public static TextView txt_score, txt_best_score, txt_score_over;
   public static RelativeLayout rl_game_over;
   private GameView gv;
   public static Button btn_start;
   private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_game);
        txt_score = findViewById(R.id.txt_score);
        txt_best_score=findViewById(R.id.txt_best_score);
        txt_score_over=findViewById(R.id.txt_score_over);
        rl_game_over = findViewById(R.id.rl_game_over);
        btn_start = findViewById(R.id.btn_start);
        gv = findViewById(R.id.gv);
        btn_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               gv.setStart(true);
               txt_score.setVisibility(View.VISIBLE);
               btn_start.setVisibility(View.INVISIBLE);


            }
        });
        rl_game_over.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                 btn_start.setVisibility(View.VISIBLE);
                 rl_game_over.setVisibility(View.INVISIBLE);
                 gv.setStart(false);
                 gv.reset();

            }



        });
        mediaPlayer = MediaPlayer.create(this,R.raw.sillychipsong);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();


    }

    @Override
    protected void onResume(){
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mediaPlayer.pause();
    }


}