package com.example.physicsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.ActionBar;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.widget.Button;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChipGroup chipGroup = findViewById(R.id.chip_group_main);
        ArrayList shapes = new ArrayList<Shape>();
        shapes.add(new Shape(70,200, chipGroup,this));
        Shape hexagon = new Shape(70, 200, chipGroup, this);
        Shape fifty = new Shape(70, 150, chipGroup, this);
        shapes.add(hexagon);
        shapes.add(fifty);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < shapes.size(); i++) {
                            ((Shape)shapes.get(i)).update();
                            if(count==3) {
                                ((Shape)(shapes.get(i))).setPos(500*(i+1)-300,100*(i+1));
                            }
                        }
                        count++;
                        if(count>4) {
                            for(int i = 0; i < shapes.size(); i++) {
                                ((Shape)shapes.get(i)).gravify();
                            }
                        }
                    }
                });
            }
        }, 0, 40);
    }
}
