package com.example.duan1_pro1121_nhom2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1_pro1121_nhom2.LoginAccount.DangNhap;
import com.example.duan1_pro1121_nhom2.R;

public class HelloWorld extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_word);

        imageView = findViewById(R.id.img_hello);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), DangNhap.class));
            }
        },3500);
        Moving_();
    }
    public void Moving_()
    {

        //Hien IMG
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView,"translationY",500f,-500f);
        animator2.setDuration(2000);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView,"alpha",0f,1f);
        animator3.setDuration(2000);
        // Config slideshow process to show next img
        AnimatorSet ans = new AnimatorSet();
        ans.play(animator2).with(animator3);
        ans.start();


    }

}