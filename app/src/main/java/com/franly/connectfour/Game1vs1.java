package com.franly.connectfour;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.franly.connectfour.ConectFour.Algoritmos;


public class Game1vs1 extends AppCompatActivity {
    //Declaracion de mi Tablero el cual esta En el layout activity_game1vs1(TableLayout)
    private GridLayout board;
    //motrar jugadas
    private ScrollView allPlaysMade;
    LinearLayout ll;
    //Ficha jugador de turno
    private ImageView TurnPlayer;
    //posicion de la cima de cada columna
    private int[] Position = new int[7];
    //total de fichas
    private int Piece;
    //board para comparacion en el algoritmo
    private String[][] Tablero =new String[6][7];
    final int size= 183;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1vs1);
        board = (GridLayout) findViewById(R.id.GridLayout1);
        allPlaysMade = (ScrollView) findViewById(R.id.show);
        TurnPlayer = (ImageView)findViewById(R.id.turnplayer);
        Ini();

    }
    private void Ini() {
        //bgsound.start();
        TurnPlayer.setImageResource(R.mipmap.reddisk);
        board.removeAllViews();
        allPlaysMade.removeAllViews();
        ll= new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        allPlaysMade.addView(ll);
        for (int x = 0; x < 7; x++) {
            Position[x] = 5;
        }
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                Tablero[x][y] = "O";
            }
        }
        Piece = 42;
        //scrollView.addView(linear);
        final int column = 7;
        int row = Piece / column;
        board.setColumnCount(column);
        board.setRowCount(row);
        for (int c = 0; c < 7; c++) {
            for (int y = 0; y < 6; y++) {
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.fondotransparente);
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.height = size;
                param.width = size;
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(y);
                imageView.setLayoutParams(param);
                imageView.setContentDescription("" + c);
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Play(Integer.parseInt(view.getContentDescription().toString()));
                    }
                });
                board.addView(imageView);
            }
        }
    }
    private void Play(int column) {
        ImageView ImageView = new ImageView(this);
        String color;
        if (Piece % 2 == 0) {
            ImageView.setImageResource(R.drawable.reddisk);
            TurnPlayer.setImageResource(R.drawable.yellowdisk);
            color = "R";
        } else {
            ImageView.setImageResource(R.drawable.yellowdisk);
            TurnPlayer.setImageResource(R.drawable.reddisk);
            color = "Y";
        }
        if (Piece != 0) {
            if (Position[column] < 0) {
                Toast.makeText(Game1vs1.this, "Columna llena", Toast.LENGTH_SHORT).show();
            } else {
                mkSound();
                Animation anim= AnimationUtils.loadAnimation(this, R.anim.move);
                Tablero[Position[column]][column] = color;
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.height = board.getHeight()/6;
                param.width = board.getHeight()/6;
                param.columnSpec = GridLayout.spec(column);
                param.rowSpec = GridLayout.spec(5);
                ImageView.setLayoutParams(param);
                ImageView.setAnimation(anim);
                board.addView(ImageView);
                param.rowSpec = GridLayout.spec(Position[column]);
                Position[column]--;
                Piece--;
                addplaymade(column, Position[column]+1);
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
        }else {
            Toast.makeText(Game1vs1.this,"It's Over... empate!",Toast.LENGTH_LONG).show();
        }
    }
    private boolean Win(){
        Algoritmos algoritmos = new Algoritmos(Tablero);
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
    private void mkSound(){
        MediaPlayer disksound = MediaPlayer.create(Game1vs1.this,R.raw.coin);
        disksound.start();
    }
    private void addplaymade(int column,int row){
        TextView txt = new TextView(this);
        txt.setText("Columna:"+(column+1)+" Fila:"+(6-(row)));
        txt.setTextSize(25);
        ll.addView(txt);
    }
    }

