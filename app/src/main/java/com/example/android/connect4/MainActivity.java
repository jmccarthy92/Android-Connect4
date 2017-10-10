package com.example.android.connect4;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private ConnectFour game;
    private ButtonGridAndTextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new ConnectFour();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize( size );
        int w = size.x / ConnectFour.HORIZ_SIDE;
        ButtonHandler bh = new ButtonHandler();
        view = new ButtonGridAndTextView(this, w, ConnectFour.VERT_SIDE, ConnectFour.HORIZ_SIDE, bh );
        view.setStatusText(game.result());
        setContentView(view);
    }

    public void showNewGameDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("This is fun");
        alert.setMessage("Play Again?");
        PlayDialog playAgain = new PlayDialog();
        alert.setPositiveButton("YES", playAgain);
        alert.setNegativeButton("NO", playAgain);
        alert.show();
    }

    private class ButtonHandler implements View.OnClickListener {

        public void onClick( View v ) {
            for(int column = 0; column < ConnectFour.HORIZ_SIDE; column++){
            //for(int row = ConnectFour.VERT_SIDE - 1; row >= 0; row --) {
              //  for( int column = 0; column < ConnectFour.HORIZ_SIDE; column++) {
                for( int row = ConnectFour.VERT_SIDE - 1; row >= 0; row--) {
                    if( view.isButton( (Button) v, row, column) && game.canPlayPosition(row,column)){
                        int play = game.play( row, column);
                        this.makePlay(row, column, play);

                        if( game.isGameOver()){
                            view.setStatusBackgroundColor( Color.RED );
                            view.enableButtons( false );
                            view.setStatusText( game.result() );
                            showNewGameDialog();
                        }
                        return;
                    }
                }
            }
        }

        private void makePlay( int row , int col, int play  ){
                    if(play == 1) {
                        //view.setButtonText(row, col, "X");
                        view.setBackgroundResources(row,col,R.drawable.playerpieces);
                        //view.setTextColor(row,col, Color.RED);
                    } else if (play == 2){
                     //   view.setButtonText(row, col, "O");
                    //    view.setTextColor(row,col, Color.GREEN);
                        view.setBackgroundResources(row,col,R.drawable.playerpieces2);
                      //  return;
                    }
        }

    }


    private class PlayDialog implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int id){
            if( id == -1){
                game.resetGame();
                view.enableButtons( true );
                view.resetButtons();
                view.setStatusBackgroundColor( Color.GREEN );
                view.setStatusText( game.result());
            } else if ( id == -2)
                MainActivity.this.finish();
        }
    }
}
