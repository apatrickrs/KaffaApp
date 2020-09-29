package com.kaffa.kaffatest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

import com.kaffa.kaffatest.R;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private GifImageView gifImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gifImage = findViewById(R.id.gifImage);
        gifImage.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));

        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE;

        //Hide NavigationBar
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openScreen();
            }
        }, 9500);
    }

    private void openScreen() {
        ActivityCompat.startActivity(this, new Intent(this, ValidateCnpjActivity.class),
                ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out).toBundle());
        finish();
    }
}
