package com.franly.connectfour;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.franly.connectfour.ConectFour.Algoritmos;

public class Game1vs1 extends AppCompatActivity {
    //Declaracion de mi Tablero el cual esta En el layout activity_game1vs1(TableLayout)
    private GridLayout board;
    //private ScrollView scrollView;
    //private LinearLayout linear;
    //tamaño de las fichas
    final int size=183;
    //Ficha jugador de turno
    private ImageView turnplayer ;

    private int[] position = new int[7];
    //posiciones disponibles del tablero
    //total de fichas
    private int piece;
    //board para comparacion en el algoritmo
    private String[][] tablero=new String[6][7];
   /* {"O", "O", "O", "O", "O", "O", "O"},
    {"O", "O", "O", "O", "O", "O", "O"},
    {"O", "O", "O", "O", "O", "O", "O"},
    {"O", "O", "O", "O", "O", "O", "O"},
    {"O", "O", "O", "O", "O", "O", "O"},
    {"O", "O", "O", "O", "O", "O", "O"}};*/

    //Animation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1vs1);
        board = (GridLayout) findViewById(R.id.GridLayout1);
        //scrollView = (ScrollView) findViewById(R.id.show);
        turnplayer = (ImageView)findViewById(R.id.turnplayer);
        turnplayer.setImageResource(R.mipmap.reddisk);

        Ini();


    }
    private void Ini(){
        board.removeAllViews();
        for(int x=0;x<7;x++){
            position[x]=5;
        }
        for(int x=0;x<6;x++){
            for(int y=0;y<7;y++){
                tablero[x][y]="O";
            }
        }
        piece=42;
        //scrollView.addView(linear);
        final int column = 7;
        int row = piece / column;
        board.setColumnCount(column);
        board.setRowCount(row);
        for(int c = 0; c < 7; c++)
        {
            ImageView ImageView = new ImageView(this);
            ImageView.setImageResource(R.drawable.fondodisk);
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = size;
            param.width =  size;
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(0);
            ImageView.setLayoutParams (param);
            ImageView.setContentDescription(""+c);
            ImageView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    Play(Integer.parseInt(view.getContentDescription().toString()));
                }
            });
           board.addView(ImageView);
        }
    }
    public void Play(int column) {
        ImageView ImageView = new ImageView(this);
        String color;
        if (piece % 2 == 0) {
            ImageView.setImageResource(R.mipmap.reddisk);
            turnplayer.setImageResource(R.mipmap.yellowdisk);
            color = "R";
        } else {
            ImageView.setImageResource(R.mipmap.yellowdisk);
            turnplayer.setImageResource(R.mipmap.reddisk);
            color = "Y";
        }
        if (piece != 0) {
            if (position[column] < 0) {
                Toast.makeText(Game1vs1.this, "Columna llena", Toast.LENGTH_SHORT).show();
            } else {
                tablero[position[column]][column] = color;
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.height = size;
                param.width = size;
                param.columnSpec = GridLayout.spec(column);
                param.rowSpec = GridLayout.spec(position[column]);
                ImageView.setLayoutParams(param);
                board.addView(ImageView);
                position[column]--;
                piece--;
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
                                    startActivity(new Intent(Game1vs1.this,Game1vs1.class));
                                    finish();
                                }
                            }).create();
                    alert.show();
                }

            }
        }else {
            Toast.makeText(Game1vs1.this,"It's Over... empate!",Toast.LENGTH_LONG).show();
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
    }

