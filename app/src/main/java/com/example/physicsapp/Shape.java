package com.example.physicsapp;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Shape extends AppCompatActivity {
    public int sides;
    public int sideLength;
    ChipGroup chipGroup;
    disable[] disables;
    Context Ctext;
    Chips[] chiplist;
    public Shape(int Sides, int SideLength, ChipGroup Cgroup, Context context) {
        int sides = Sides;
        int sideLength = SideLength;
        chiplist = new Chips[sides];
        chipGroup = Cgroup;
        Ctext = context;
        for(int i = 0; i < sides; i++) {
            chiplist[i] = new Chips(new Chip(Ctext),sides);
            chiplist[i].setNum(i);
            chipGroup.addView(chiplist[i].c);
        }
        disables = new disable[sides*(sides-1)/2];

        int c = 0;
        for(int i = 0; i < sides; i++) {
            for(int j = i+1; j< sides; j++) {
                double ydistsqrd = Math.pow((Math.sin(i*2*Math.PI/(sides))*sideLength-Math.sin(j*2*Math.PI/(sides))*sideLength),2);
                double xdistsqrd = Math.pow((Math.cos(i*2*Math.PI/(sides))*sideLength-Math.cos(j*2*Math.PI/(sides))*sideLength),2);
                double distSqrd = ydistsqrd+xdistsqrd;
                double springdist = Math.sqrt(distSqrd);
                disables[c] = new disable(chiplist[i],chiplist[j], springdist, sides);
                c++;
            }
        }
    }

    public void update() {
        for(int i = 0; i < disables.length; i++) {
            disables[i].Disable();
        }
    }
    public void setPos(int Xpos, int Ypos) {
        for(int i = 0; i < disables.length; i++) {
            disables[i].setCoords(Xpos, Ypos);
        }
    }
    public void gravify() {
        for(int i = 0; i < chiplist.length;i++) {
            chiplist[i].gravify();
        }
    }

}
