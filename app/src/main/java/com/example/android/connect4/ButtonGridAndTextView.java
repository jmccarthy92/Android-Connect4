package com.example.android.connect4;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by globe_000 on 10/5/2017.
 */

public class ButtonGridAndTextView extends GridLayout {

    private int vSide;
    private int hSide;
    private Button[][] buttons;
    private Button[]dropButtons;
    private TextView status;

    public ButtonGridAndTextView(Context context, int width, int vSide, int hSide,
                                 OnClickListener listener) {
        super(context);

        setColumnCount( hSide );
        setRowCount( vSide + 2);
        this.vSide = vSide;
        this.hSide = hSide;
        dropButtons = new Button[hSide];
        buttons = new Button[vSide][hSide];
        for( int fRow = 0; fRow < hSide; fRow++){
            dropButtons[fRow] = new Button( context);
            dropButtons[fRow].setOnClickListener(listener);
            dropButtons[fRow].setBackgroundColor(Color.BLACK);

            addView( dropButtons[fRow], width, width);
            dropButtons[fRow].getLayoutParams().width = (int) (width * .9);
            dropButtons[fRow].getLayoutParams().height = (int) (width * .9);
        }
        for( int row = 0; row< vSide ; row++){
            //Log.d("JAMES" + row, vSide + "--*--*--" + hSide);
            for( int col =0 ; col < hSide; col++){
                buttons[row][col] = new Button( context);
                buttons[row][col].setTextSize((int) (width * .2));
            //    buttons[row][col].setOnClickListener(listener);
                addView( buttons[row][col], width, width);
            }
        }
//        for( int col =0; col< hSide; col++){
//            for(int row =0; row< vSide; row++){
//                buttons[row][col] = new Button( context);
//                buttons[row][col].setTextSize((int) (width * .2));
//               // buttons[row][col].setOnClickListener(listener);
//
//            }
//        }
        status = new TextView( context );
        Spec rowSpec = GridLayout.spec(vSide + 1, 1);
        Spec columnSpec =  GridLayout.spec(0, hSide);
        LayoutParams lpStatus = new LayoutParams( rowSpec, columnSpec);
        status.setLayoutParams(lpStatus);
        status.setWidth( hSide * width );
        status.setHeight(width);
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int) (width * .15));

        addView( status );

    }

    public void setTextColor(int row, int col,int color){ buttons[row][col].setTextColor(color); }

    public void setBackgroundResources(int row, int col, int resource){ buttons[row][col].setBackgroundResource(resource);}

    public void setStatusText( String text ){
        status.setText( text );
    }

    public void setStatusBackgroundColor( int color){
        status.setBackgroundColor( color );
    }

    public void setButtonText( int row, int column, String text) {
        buttons[row][column].setText(text);
    }

    public boolean isButton(Button b, int row, int column) {
        return ( b == dropButtons[column] );//  buttons[row][column] ); //dropButtons[row] );
    }

//    public boolean pla
//    if( b == dropButtons[row] ){
//        if( )
//    }


    public void resetButtons() {
        for(int row=0; row< vSide; row++){
            for(int col = 0; col < hSide; col++){
                buttons[row][col].setText("");
            }
        }
    }

    public void enableButtons( boolean enabled) {
        for( int row=0; row< vSide; row++)
            for(int col=0; col< hSide; col++)
                buttons[row][col].setEnabled( enabled );
    }



}
