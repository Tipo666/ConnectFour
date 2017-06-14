package com.franly.connectfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }
    public void onClick(View view) {
        Button btn = (Button) view;
        if(btn.getId()==R.id.vs){
        Intent intent  = new Intent(play.this, Game1vs1.class);
        startActivity(intent);}
        if(btn.getId()==R.id.alone){
        Intent intent  = new Intent(play.this, GameVS.class);
        startActivity(intent);}


    }
}
