package com.example.admin.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.admin.myapplication.RockerView.OnShakeListener;
import com.example.admin.myapplication.RockerView.Direction;
import com.example.admin.myapplication.RockerView.DirectionMode;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    private boolean run = false;
    private final Handler handler = new Handler();
    private int a=1,b=1;
    public String info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initrokerview();
        run = true;
        handler.postDelayed(task, 3000);
    }
    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if(run){
                TextView tev=findViewById(R.id.textView2);
                int min=1;
                int max=70;
                Random random = new Random();
                int num = random.nextInt(max)%(max-min+1)+min;
                if(num>=60) tev.setTextColor(Color.parseColor("#FF3030"));
                if(20<=num&&num<60) tev.setTextColor(Color.parseColor("#1E90FF"));
                if(num<20) tev.setTextColor(Color.parseColor("#66CD00"));
                VerticalSeekBar seekBar=(VerticalSeekBar)findViewById(R.id.verticalSeekBar);

                tev.setText(String.valueOf(num+"m/s"));

                handler.postDelayed(this,1000);
            }
        }
    };
    public void onTouch(View view)
    {
        ImageButton imgbtn=findViewById(R.id.imageButton2);
        if (a==1)
        {
            a++;
            imgbtn.setBackgroundResource(R.drawable.fly);
        }
        else
        {
            a--;
            imgbtn.setBackgroundResource(R.drawable.fly_no);
        }
    }
//    @Override
//    protected  void onDestroy()
//    {
//        super.onDestroy();
//        ReleasePlayer();
//    }
    private void ReleasePlayer()
    {
        if (mp != null && mp.isPlaying()) {
            mp.stop();
            //关键语句
            mp.reset();
            mp.release();
            mp = null;
        }
    }

    public void init(){
        ReleasePlayer();
        switch (info)
        {
            case "背景":
                mp=MediaPlayer.create(this,R.raw.dy);
                break;
            case "前":
                mp=MediaPlayer.create(this,R.raw.qian);//前飞
                break;
            case "后":
                mp=MediaPlayer.create(this,R.raw.hou);//后飞
                break;
            case "左":
                mp=MediaPlayer.create(this,R.raw.zuo);//左飞
                break;
            case "右":
                mp=MediaPlayer.create(this,R.raw.you);//右飞
                break;
            case "连":
                mp=MediaPlayer.create(this,R.raw.lian);//没连上
                break;
        }
        try
        {
            mp.prepare();
        }catch (IllegalStateException e)
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        if(!mp.isPlaying())
        {
            mp.start();
        }

    }
//    public boolean onTouchEvent(MotionEvent event) {
//
////获得触摸的坐标
//        float x = event.getX();
//        float y = event.getY();
//        switch (event.getAction())
//        {
////触摸屏幕时刻
//            case MotionEvent.ACTION_DOWN:
//              TextView tv1=  findViewById(R.id.txtdow);
//              tv1.setText(x+","+y);
//                break;
////触摸并移动时刻
//            case MotionEvent.ACTION_MOVE:
//                TextView tv2=  findViewById(R.id.txtmove);
//                tv2.setText(x+","+y);
//                break;
////终止触摸时刻
//            case MotionEvent.ACTION_UP:
//                TextView tv3=  findViewById(R.id.txtup);
//                tv3.setText(x+","+y);
//                break;
//        }
//        return true;
//    }


    public void initrokerview(){
        //找到RockerView控件
        RockerView roker=(RockerView) findViewById(R.id.RockerView);
        //实时监测摇动方向
        roker.setOnShakeListener(DirectionMode.DIRECTION_8, new OnShakeListener() {
            //开始摇动时要执行的代码写在本方法里
            @Override
            public void onStart() {

            }
            //结束摇动时要执行的代码写在本方法里
            @Override
            public void onFinish() {
                info=null;
                //Toast.makeText(MainActivity.this, "已复位", Toast.LENGTH_SHORT).show();
            }
            //摇动方向时要执行的代码写在本方法里
            @Override
            public void direction(Direction direction) {
                if (direction == RockerView.Direction.DIRECTION_CENTER){
                    //tv.setText("中心");
                }else if (direction == RockerView.Direction.DIRECTION_DOWN){
                    //tv.setText("下");
                    if (info!="后")
                    {
                        info="后";
                        init();
                    }
                }else if (direction == RockerView.Direction.DIRECTION_LEFT){
                    //tv.setText("左");
                    if (info!="左")
                    {
                        info="左";
                        init();
                    }
                }else if (direction == RockerView.Direction.DIRECTION_UP){
                    //tv.setText("上");
                    if (info!="前")
                    {
                        info="前";
                        init();
                    }
                }else if (direction == RockerView.Direction.DIRECTION_RIGHT){
                    //tv.setText("右");
                    if (info!="右")
                    {
                        info="右";
                        init();
                    }
                }else if (direction == RockerView.Direction.DIRECTION_DOWN_LEFT){
                    //tv.setText("左下");
                }else if (direction == RockerView.Direction.DIRECTION_DOWN_RIGHT){
                    //tv.setText("右下");
                }else if (direction == RockerView.Direction.DIRECTION_UP_LEFT){
                    //tv.setText("左上");
                }else if (direction == RockerView.Direction.DIRECTION_UP_RIGHT){
                    //tv.setText("右上");
                }

            }
        });
    }
    public void sxTouch(View view)
    {
        ImageButton sxbtn= findViewById(R.id.lximgbtn);
        if (b==1)
        {
            b++;
            sxbtn.setBackgroundResource(R.drawable.shexiang2);
        }
        else
        {
            b--;
            sxbtn.setBackgroundResource(R.drawable.shexiang);
        }
    }

}
