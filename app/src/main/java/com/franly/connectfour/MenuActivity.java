package com.franly.connectfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //getSupportActionBar().hide();

    }
    public void onClick(View view){
     Button btn = (Button)(view);
     Intent intent;
        switch (btn.getId()){
            case R.id.btnexit:finish();break;
            case R.id.btnplay:intent = new Intent(MenuActivity.this,play.class);startActivity(intent);break;
            default:break;

        }

    }
}
