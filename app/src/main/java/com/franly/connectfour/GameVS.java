package com.franly.connectfour;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.franly.connectfour.ConectFour.Algoritmos;

public class GameVS extends AppCompatActivity {
    //Declaracion de mi Tablero el cual esta En el layout activity_game1vs1(TableLayout)
    GridLayout board;
    ScrollView scrollView;
    //tamaño de las fichas
    final int size=183;
    //Ficha jugador de turno
    private ImageView turnplayer ;
    //posiciones disponibles del tablero
    private int[] position = {5, 5, 5, 5, 5, 5, 5};
    //total de fichas
    private int piece;
    //board para comparacion en el algoritmo
    private String[][] tablero = {
            {"O", "O", "O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O", "O", "O"}};
    private final String[][] tablerocopia=tablero.clone();
    private final int[] positioncopia =position.clone();
    Algoritmos algoritmos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1vs1);
        board = (GridLayout) findViewById(R.id.GridLayout1);
        scrollView = (ScrollView) findViewById(R.id.show);
        turnplayer = (ImageView)findViewById(R.id.turnplayer);
        turnplayer.setImageResource(R.drawable.reddisk);
        Ini();
    }
    private void Ini(){

        board.removeAllViews();
        scrollView.removeAllViews();
        tablero=tablerocopia.clone();
        position=positioncopia.clone();
        piece=42;

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
            ImageView.setImageResource(R.drawable.reddisk);
            turnplayer.setImageResource(R.drawable.yellowdisk);
            color = "R";
        } else {
            ImageView.setImageResource(R.drawable.yellowdisk);
            turnplayer.setImageResource(R.drawable.reddisk);
            color = "Y";
        }
        if (piece != 0) {
            if (position[column] < 0) {
                Toast.makeText(GameVS.this, "Columna llena", Toast.LENGTH_SHORT).show();
            } else {
                tablero[position[column]][column] = color;
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.height = size;
                param.width = size;
                param.columnSpec = GridLayout.spec(column);
                param.rowSpec = GridLayout.spec(position[column]);
                ImageView.setLayoutParams(param);
                board.addView(ImageView);
                TextView txt = new TextView(this);
                txt.setText(column);
                scrollView.addView(txt);
                position[column]--;
                piece--;
                if (Win() == 1) {
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
                                    startActivity(new Intent(GameVS.this,Game1vs1.class));
                                    finish();
                                }
                            }).create();
                    alert.show();
                }

            }
        }else {
            Toast.makeText(GameVS.this,"It's Over... empate!",Toast.LENGTH_LONG).show();
        }
    }
    private int Win(){
        algoritmos = new Algoritmos(tablero);
        if (algoritmos.LHorizontal() == 1 || algoritmos.LVertical() == 1 || algoritmos.LDiagonal() == 1) {
            return 1;
        }
        return 0;
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
