package com.example.admin.myapplication;


import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.UUID;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.admin.myapplication.RockerView.OnShakeListener;
import com.example.admin.myapplication.RockerView.Direction;
import com.example.admin.myapplication.RockerView.DirectionMode;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;//定义MediaPlayer
    public String info;//记录需要触发语音的类型
//    private boolean run = false;


//    public  OutputStream out;//定义输出流
    public static UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");//定义UUID
//    private final Handler handler = new Handler();
//    private int a=1,b=1;
//    private  TextView tipe;
    //public String address="20:F7:7C:85:5E:84";//蓝牙mac地址
    public byte[]data=new byte[34];//定义通信数组
    private VerticalSeekBar verticalSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verticalSeekBar = findViewById(R.id.verticalSeekBar);
        //verticalSeekBar.setOnSeekBarChangeListener(this);
        // initrokerview();
        //run = true;
        //handler.postDelayed(task, 3000);
        verticalSeekBar.setOnSeekBarChangeListener(new VerticalSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(VerticalSeekBar VerticalBar, int progress, boolean fromUser) {
                System.out.println("当前值为:"+progress);
                Initialization.initial.speed=progress;
            }

            @Override
            public void onStartTrackingTouch(VerticalSeekBar VerticalBar) {

            }

            @Override
            public void onStopTrackingTouch(VerticalSeekBar VerticalBar) {

            }
        });
    }


//    private final Runnable task = new Runnable() {
//        @Override
//        public void run() {
//            if(run){
//                TextView tev=findViewById(R.id.textView2);
//                int min=1;
//                int max=70;
//                Random random = new Random();
//                int num = random.nextInt(max)%(max-min+1)+min;
//                if(num>=60) tev.setTextColor(Color.parseColor("#FF3030"));
//                if(20<=num&&num<60) tev.setTextColor(Color.parseColor("#1E90FF"));
//                if(num<20) tev.setTextColor(Color.parseColor("#66CD00"));
//                VerticalSeekBar seekBar=(VerticalSeekBar)findViewById(R.id.verticalSeekBar);
//
//                tev.setText(String.valueOf(num+"m/s"));
//
//                handler.postDelayed(this,1000);
//            }
//        }
//    };
//    public void onTouch(View view)
//    {
//        ImageButton imgbtn=findViewById(R.id.imageButton2);
//        if (a==1)
//        {
//            a++;
//            imgbtn.setBackgroundResource(R.drawable.fly);
//        }
//        else
//        {
//            a--;
//            imgbtn.setBackgroundResource(R.drawable.fly_no);
//        }
//    }
//    @Override
//    protected  void onDestroy()
//    {
//        super.onDestroy();
//        ReleasePlayer();
//    }
    //释放播放资源
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
    //音频预备以及播放
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
//    public void sxTouch(View view)
//    {
//        ImageButton sxbtn= findViewById(R.id.lximgbtn);
//        if (b==1)
//        {
//            b++;
//            sxbtn.setBackgroundResource(R.drawable.shexiang2);
//        }
//        else
//        {
//            b--;
//            sxbtn.setBackgroundResource(R.drawable.shexiang);
//        }
//    }


    public boolean flag=false;
    /**
     * 点击按钮启动线程
     * @param view
     */
    public void  btnqd(View view){

        BluetoothAdapter blueadapter=BluetoothAdapter.getDefaultAdapter();
        if (!blueadapter.isEnabled()){
            blueadapter.enable();
            Toast.makeText(getApplicationContext(),"蓝牙状态：未开启",Toast.LENGTH_SHORT).show();
        }else
        {
           ImageButton imageButton= findViewById(R.id.imgbtnfj);
            imageButton.setBackgroundResource(R.drawable.fly);
            flag=true;
            findViewById(R.id.imgbtn_ly).setBackgroundResource(R.drawable.ly_on);
            new Thread(new Bluetooth_Conn.ConnectThread()).start();
            Toast.makeText(getApplicationContext(),"开始连接",Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 点击按钮发送数据
     * @param view
     */

    public void  btnly(View view){
        if (flag) {
            //Thread t = new Thread(new Bluetooth_Conn.SendThread());
            //t.start();
            char[] data=DataManage.Behavior((char) Initialization.initial.speed,(char)Initialization.initial.course,(char)Initialization.initial.Rollover,(char)Initialization.initial.pitch);
            byte[] byteData = DataManage.charToByteArray(data);
            Toast.makeText(getApplicationContext(),Initialization.initial.speed,Toast.LENGTH_SHORT).show();

        }else
        {
            Toast.makeText(getApplicationContext(),"请先连接设备",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 设置弹框修改连接蓝牙MAC地址
     * @param view
     */
    public void alert_edit(View view){
        final EditText et = new EditText(this);
        et.setText("00:0E:0E:15:85:04");
        new AlertDialog.Builder(this).setTitle("蓝牙MAC地址更改:")
                .setIcon(R.drawable.sz2)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        String telReg="([a-fA-F0-9]{2}:){5}[a-fA-F0-9]{2}";
                        if (!et.getText().toString().equals("")&&et.getText().toString().matches(telReg))
                        {
                            Initialization.address=et.getText().toString();
                            Toast.makeText(getApplicationContext(),"蓝牙地址更改为:"+et.getText().toString(),Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"提示:蓝牙MAC地址格式错误！",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("取消",null).show();
    }
}
//class verticalSeekBar implements SeekBar.OnSeekBarChangeListener{
//
//    public verticalSeekBar() {
//        super();
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//        System.out.println("当前进度是"+i+";是否来自用户："+b);
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//        System.out.println("开始拖动");
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        System.out.println("脱完了");
//    }
//}