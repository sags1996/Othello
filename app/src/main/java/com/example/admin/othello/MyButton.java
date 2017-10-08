package com.example.admin.othello;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Admin on 6/20/2017.
 */

public class MyButton extends android.support.v7.widget.AppCompatButton {
      int player;
    int x;
    int y;
    public MyButton(Context context) {
        super(context);
        player = MainActivity.NO_PLAYER;
    }


    int getPlayer(){
        return  player;
    }


}

