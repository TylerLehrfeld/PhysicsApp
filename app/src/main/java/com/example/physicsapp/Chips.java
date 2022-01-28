package com.example.physicsapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;

import com.google.android.material.chip.Chip;

public class Chips {
    public Chip c;
    private int Sides;
    private double gravity;
    private double Xvelo = 0;
    private double Yvelo = 0;
    public Chips(Chip C,int sides) {
        c = C;
        c.setVisibility(View.VISIBLE);
        /*c.setX((float)40);
        c.setY((float)(Math.random()+100));*/
        c.setText(" ");
        Sides = sides;
        gravity = 5;
    }
    public void setNum(int i) {
        //c.setText(i+"");

        //int color = Color.rgb(200/Sides*i+50,50,50);
        int color = Color.RED;
        c.setChipBackgroundColor(ColorStateList.valueOf(color));
    }

    public void gravify() {
        gravity+=.01;
        if(c.getY()>=2000) {
            c.setY((float)(2000));
            gravity=0;
        }
        if((float) (c.getY()+gravity)>=2000) {
            c.setY(2000);
        } else {
            c.setY((float) (c.getY()+gravity));
        }

    }
}
