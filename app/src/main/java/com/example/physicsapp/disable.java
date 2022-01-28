package com.example.physicsapp;

import android.view.Gravity;
import android.view.View;
import android.app.ActionBar;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

import com.google.android.material.chip.Chip;

public class disable {
    private double Yforce = 0;
    private double Xforce = 0;
    private double Yvelo = 0;
    private double Xvelo = 0;
    private double attYVelo=0;
    private double attYforce = 0;
    private Chip attractor;
    private Chip C;
    double Dist = 0;
    private double Sides;
    int Count = 0;
    private double Cgravity = .4;
    private double Agravity = .4;
    private Chips c1;
    private Chips c2;
    public disable(Chips chip1, Chips chip2, double dist, int sides) {
        c1 = chip1;
        c2 = chip2;
        attractor =  chip2.c;
        C = chip1.c;
        Dist = dist;
        Sides = (double) sides;
    }
    public void Disable() {
        double atY = attractor.getY();
        double atX = attractor.getX();
        double Y = C.getY();
        double X = C.getX();
        double diffY = Y - atY;
        double diffX = X - atX;
        double totaldiff = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        diffX = (int) (diffX * (totaldiff - Dist) / totaldiff);
        diffY = (int) (diffY * (totaldiff - Dist) / totaldiff);
        Yforce = (diffY * .01);
        Xforce = (diffX * .01);
        Yvelo -= Yforce;
        attYVelo-= Yforce;
        Xvelo -= Xforce;
        Yvelo *= .95;
        Xvelo *= .95;
        /*if(Xvelo>0) {
            C.setChipBackgroundColor(ColorStateList.valueOf(Color.BLUE));
            attractor.setChipBackgroundColor(ColorStateList.valueOf(Color.BLUE));
        } else {
            C.setChipBackgroundColor(ColorStateList.valueOf(Color.RED));
            attractor.setChipBackgroundColor(ColorStateList.valueOf(Color.RED));
        }*/

        C.setX((float) (X + Xvelo));
        if (C.getY() > 2000&&Count>60) {
            //C.setChipBackgroundColor(ColorStateList.valueOf(Color.GREEN));
            if(Yvelo>0) {

            } else {
                if((float) (Y + Yvelo)>2000) {
                    C.setY(2000);
                } else {
                    C.setY((float) (Y +     Yvelo));
                }
            }
        } else {
            if((float) (Y + Yvelo)>2000&&Count>60) {
                C.setY(2000);
            } else {
                C.setY((float) (Y +     Yvelo));
            }
        }


        if (attractor.getY() > 2000&&Count>60) {
            //attractor.setChipBackgroundColor(ColorStateList.valueOf(Color.GREEN));
            if(0-Yvelo>0) {

            } else {
                if((float) (attractor.getY() - Yvelo)>2000&&Count>60) {
                    attractor.setY(2000);
                } else {
                    attractor.setY((float) (attractor.getY() - Yvelo));
                }
            }
        } else {

            if((float) (attractor.getY() - Yvelo)>2000&&Count>60) {
                attractor.setY(2000);
            } else {
                attractor.setY((float) (attractor.getY() - Yvelo));
            }
        }
        attractor.setX((float) (attractor.getX() - Xvelo));
        Count++;

    }
    public void setCoords(int Xpos, int Ypos) {
        attractor.setX(Xpos);
        attractor.setY(Ypos);
    }

}
