package com.android.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class XltActivity extends AppCompatActivity {

    private ImageView xp33;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xlt);
        xp33 = findViewById(R.id.xp33);

        Resources resources = XltActivity.this.getResources();
        Drawable imageDrawable = resources.getDrawable(R.drawable.gzd_ks); //图片在drawable文件夹下
        xp33.setBackgroundDrawable(imageDrawable);
        xp33.setImageDrawable(null);
        AnimationDrawable   animationDrawableks = (AnimationDrawable) xp33.getBackground();
        animationDrawableks.start();

    }

}