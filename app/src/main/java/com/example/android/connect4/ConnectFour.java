package com.example.android.connect4;

import android.util.Log;

/**
 * Created by globe_000 on 10/4/2017.
 */

public class ConnectFour
{
    public static final int VERT_SIDE = 6;
    public static final int HORIZ_SIDE = 7;
    private int turn;
    private int [][] game;

    public ConnectFour(){
        game = new int[VERT_SIDE][HORIZ_SIDE];
        resetGame();
    }

    public void printGrid(){
        String j = "";
        for(int row = 0; row < VERT_SIDE; row++) {
            for (int col = 0; col < HORIZ_SIDE; col++) {
                Integer temp = new Integer(game[row][col]);
                j += temp + " ";
            }
            j += "\n";
        }
        Log.d("JAMES", j);

    }

    public int play(int row, int col) {
        int currentTurn = turn;

        if( row >=0 && col >=0 && row < VERT_SIDE && col < HORIZ_SIDE && game[row][col] ==0 ){
            game[row][col] = turn;
            if( turn ==1)
                turn = 2;
            else
                turn = 1;
         //   printGrid();
            return currentTurn;
        } else
            return 0;
    }

    public int whoWon() {
        int rows = checkRows( );
        if ( rows > 0 )
            return rows;
        int columns = checkColumns( );
        if( columns > 0 )
            return columns;
        int diagonals = checkDiagonals( );
        if( diagonals > 0 )
            return diagonals;
        return 0;

    }

    protected int checkRows() {

        for( int row = 0; row < VERT_SIDE; row++ ){
            for(int pairs = 0 ; pairs < 4; pairs++) {
                if (game[row][pairs] != 0 && game[row][pairs] == game[row][pairs + 1]
                        && game[row][pairs + 1] == game[row][pairs + 2]
                        && game[row][pairs + 2] == game[row][pairs + 3]){
                    return game[row][pairs];
                }
            }
        }
        return 0;
    }

    protected int checkColumns() {

        for(int col =0; col< HORIZ_SIDE ; col++) {
            for(int pairs = 0 ; pairs < 3; pairs++)
                if (game[pairs][col] != 0 && game[pairs][col] == game[pairs + 1][col]
                        && game[pairs + 1][col] == game[pairs + 2][col]
                        && game[pairs + 2][col] == game[pairs + 3][col])
                    return game[pairs][col];

        }
        return 0;
    }

    protected int checkDiagonals() {
        int[][]directions = {{1,0},{1,-1},{1,1},{0,1}};
        for(int[]d: directions){
            int dx = d[0];
            int dy = d[1];
            for(int x = 0; x < VERT_SIDE; x++ ) {
                for( int y= 0; y< HORIZ_SIDE; y++){
                    int lastx = x + 3*dx;
                    int lasty = y + 3*dy;
                    if ( 0 <= lastx && lastx < VERT_SIDE && 0 <= lasty && lasty < HORIZ_SIDE){
                        int w = game[x][y];
                        if( w != 0 && w == game[x+dx][y+dy]
                                   && w == game[x+2*dx][y+2*dy]
                                   && w == game[lastx][lasty])
                            return w;
                    }
                }
            }
        }
        return 0;
    }


    public boolean canPlayPosition(int row, int col){
        if( game[row][col] == 0) // for loop to make sure you don't take position above
            return true;
        else
            return false;
    }

    public boolean canNotPlay() {
        boolean result = true;
        for(int row =0; row < VERT_SIDE; row++)
            for(int col = 0; col<HORIZ_SIDE; col++)
                if(game[row][col]==0)
                    result = false;
        return result;

    }

    public boolean isGameOver() {
        return canNotPlay() || ( whoWon() > 0);
    }

    public void resetGame() {
        for(int row =0; row < VERT_SIDE; row++)
            for(int col = 0; col<HORIZ_SIDE; col++)
                game[row][col] = 0;
        turn = 1;
    }

    public String result() {
        if(whoWon() > 0)
            return "Player " + whoWon() + " won";
        else if(canNotPlay())
            return "Tie Game";
        else
            return "PLAY !!";
    }


}
