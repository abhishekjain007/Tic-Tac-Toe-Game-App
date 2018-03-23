package com.example.abhij.tictaktoe;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;


/**
 * Created by abhij on 03-02-2018.
 */

public class TTTButton extends AppCompatButton {

    int player=Second.Player_No;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
        if(player==1)
        this.setText("X");
        else
            this.setText("O");

        this.setEnabled(false);
    }

    public TTTButton(Context context)
    {
        super(context);
    }




}
