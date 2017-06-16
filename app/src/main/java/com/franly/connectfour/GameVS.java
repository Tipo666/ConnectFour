package com.franly.connectfour;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;
import java.util.Random;
import com.franly.connectfour.ConectFour.Algoritmos;

public class GameVS extends AppCompatActivity {
    //Declaracion de mi Tablero el cual esta En el layout activity_game1vs1(TableLayout)
    GridLayout board;

    ScrollView scrollView;

    //tamaño de las fichas
    final int size=183;
    //posicion de la cima de cada columna
    private int[] position = new int[7];
    //total de fichas
    private int piece;
    //board para comparacion en el algoritmo
    private String[][] tablero=new String[6][7];
    final MediaPlayer disksound= MediaPlayer.create(GameVS.this,R.raw.coin);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1vs1);
        board = (GridLayout) findViewById(R.id.GridLayout1);
        scrollView = (ScrollView) findViewById(R.id.show);
        ImageView turnplayer = (ImageView) findViewById(R.id.turnplayer);
        turnplayer.setImageResource(R.drawable.reddisk);
        Ini();
    }
    private void Ini(){
        board.removeAllViews();
        for (int x = 0; x < 7; x++) {

            position[x] = 5;
        }
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                tablero[x][y] = "O";
            }
        }
        final int column = 7;
        int row = piece / column;
        board.setColumnCount(column);
        board.setRowCount(row);
        for (int c = 0; c < 7; c++) {
            for (int y = 0; y < 6; y++) {

                ImageView ImageView = new ImageView(this);
                ImageView.setImageResource(R.drawable.fondodisk);
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.height = size;
                param.width = size;
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(y);
                ImageView.setLayoutParams(param);
                ImageView.setContentDescription("" + c);
                ImageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Play(Integer.parseInt(view.getContentDescription().toString()));
                    }
                });
                board.addView(ImageView);
            }
        }
    }
    public void Play(int column) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.reddisk);
        if (piece != 0) {
            if (position[column] < 0) {
                Toast.makeText(GameVS.this, "Columna llena", Toast.LENGTH_SHORT).show();
            } else {
                Playdisk(column,"R",imageView);
                disksound.start();
                Ganar("R");
                PlayCPU();
            }
        }else {
            Toast.makeText(GameVS.this,"It's Over... empate!",Toast.LENGTH_LONG).show();
        }
    }
    private boolean Win(){
        Algoritmos algoritmos = new Algoritmos(tablero);
        return (algoritmos.LHorizontal() == 1 || algoritmos.LVertical() == 1 || algoritmos.LDiagonal() == 1);

    }
    public void OnCLickButton(View view){
        Button btn = (Button)view;
        switch (btn.getId()){
            case R.id.restart:
                Ini();
                break;
            case R.id.exit:
                finish();
                break;
        }
    }
    private void PlayCPU(){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.yellowdisk);
        Random r = new Random();
        int ramdom=0;
        while(position[ramdom]<0){
        ramdom = r.nextInt(6);
        }

        Playdisk(ramdom,"Y",imageView);
        Ganar("Y");
        piece--;
    }
    private void Ganar(String color){
        if (Win()) {
            if(color.equals("R")){
                color="Red";
            }else{
                color="Yellow";

            }
            AlertDialog alert = new AlertDialog.Builder(this)
                    .setTitle("You Win!")
                    .setMessage("Game over! "+color+" Player win.")
                    .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Ini();
                        }
                    })
                    .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).create();
            alert.show();
        }
    }
    private void Playdisk(int column,String color,ImageView imageView){
        Animation anim= AnimationUtils.loadAnimation(this, R.anim.move);
        tablero[position[column]][column] = color;
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.height = size;
        param.width = size;
        param.columnSpec = GridLayout.spec(column);
        param.rowSpec = GridLayout.spec(5);
        imageView.setLayoutParams(param);
        imageView.setAnimation(anim);
        board.addView(imageView);
        param.rowSpec = GridLayout.spec(position[column]);
        position[column]--;
        piece--;
        MediaPlayer disksound= MediaPlayer.create(GameVS.this,R.raw.coin);
        disksound.start();
    }
}
