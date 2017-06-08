package com.franly.connectfour.ConectFour;

public class Algoritmos {
    private String[][] tablero;
    public Algoritmos(String[][] tablero){
        this.tablero=tablero.clone();
    }

    public int LVertical(){

        int counter=0;
        for(int x=0;x<=6;x++){
            for(int y=0;y<=4;y++){
                if(!tablero[y][x].equals("O")){
                    if(tablero[y][x].equals(tablero[y+1][x])){
                        counter++;if(counter==3)return 1;
                    }else{counter=0;}
                }else{counter=0;}
            }
        }
        return 0;
    }
    public int LHorizontal(){

        int counter=0;
        for(int y=0;y<=5;y++){
            for(int x=0;x<=5;x++){
                if(!tablero[y][x].equals("O")){
                    if(tablero[y][x].equals(tablero[y][x+1])){
                        counter++;if(counter==3)return 1;
                    }else{counter=0;}
                }else{counter=0;}
            }
        }
        return 0;
    }

    public int LDiagonal(){
        if(DiagonalAlgorithm1(2,0,4)==1)return 1;
        if(DiagonalAlgorithm1(1,0,4)==1)return 1;
        if(DiagonalAlgorithm1(0,0,4)==1)return 1;
        if(DiagonalAlgorithm1(0,1,4)==1)return 1;

        if(DiagonalAlgorithm2(3,0,1)==1)return 1;
        if(DiagonalAlgorithm2(4,0,1)==1)return 1;
        if(DiagonalAlgorithm2(5,0,1)==1)return 1;
        if(DiagonalAlgorithm2(5,1,1)==1)return 1;

        if(DiagonalAlgorithm3(0,3,2)==1)return 1;
        if(DiagonalAlgorithm3(0,2,3)==1)return 1;

        if(DiagonalAlgorithm4(5,2,2)==1)return 1;
        if(DiagonalAlgorithm4(5,3,3)==1)return 1;

        return 0;
    }
    private int DiagonalAlgorithm1(int y,int x,int end){
        int counter1=0;
        for(int v=y,h=x;v<=end; v++,h++){
            if(!tablero[v][h].equals("O")){
                if(tablero[v][h].equals(tablero[v+1][h+1])){
                    counter1++;if(counter1==3)return 1;
                }else{counter1=0;}
            }else{counter1=0;}
        }
        return 0;
    }
    private int DiagonalAlgorithm2(int y,int x,int end){
        int counter1=0;
        for(int v=y,h=x;v>=end; v--,h++){
            if(!tablero[v][h].equals("O")){
                if(tablero[v][h].equals(tablero[v-1][h+1])){
                    counter1++;if(counter1==3)return 1;
                }else{counter1=0;}
            }else{counter1=0;}

        }

        return 0;
    }
    private int DiagonalAlgorithm3(int y,int x,int end){
        int counter1=0;
        for(int v=y,h=x;v>=end; v++,h++){
            if(!tablero[v][h].equals("O")){
                if(tablero[v][h].equals(tablero[v+1][h+1])){
                    counter1++;if(counter1==3)return 1;
                }else{counter1=0;}
            }else{counter1=0;}
        }

        return 0;
    }
    private int DiagonalAlgorithm4(int y,int x,int end){
        int counter1=0;
        for(int v=y,h=x;v>=end; v--,h++){
            if(!tablero[v][h].equals("O")){
                if(tablero[v][h].equals(tablero[v-1][h+1])){
                    counter1++;if(counter1==3)return 1;
                }else{counter1=0;}
            }else{counter1=0;}
        }

        return 0;
    }
}
