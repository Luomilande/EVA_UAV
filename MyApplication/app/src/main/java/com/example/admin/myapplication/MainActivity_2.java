package com.example.admin.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class MainActivity_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        initView();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(18000);//休眠3秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(MainActivity_2.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }.start();


    }

    private void initView() {
        VideoView vv = (VideoView) findViewById(R.id.videoView2);
        //设置播放加载路径
        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        //播放
        vv.start();
        //循环播放
    }
}
