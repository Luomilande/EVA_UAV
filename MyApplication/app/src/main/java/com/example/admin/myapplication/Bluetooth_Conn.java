package com.example.admin.myapplication;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Bluetooth_Conn {

    public static TextView tip;

    private static onConnect monconnect;
    public static void setOnConnect(onConnect mmm){
        monconnect=mmm;
    }

    public interface onConnect{
        void run(boolean flag);
    }
    /**
     * 初始化蓝牙相关操作
     * 获取蓝牙设备，连接蓝牙
     */
    public static class ConnectThread implements Runnable {
        private static boolean flag=true;
        @Override
                public void run() {
                    //创建线程
                    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();//获取蓝牙适配器
                    BluetoothDevice device = adapter.getRemoteDevice(Initialization.address);//获取蓝牙设备
                    if (device == null) {
                        Log.e("提示", "获取蓝牙失败");
                    }
                    try {
                        //连接服务器
                        Initialization.socket = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(device, 1);
                        //获取socket
                        try {
                            Initialization.socket.connect();
                        } catch (IOException e) {
                            Log.e("提示", "socket连接错误");
                             Looper.prepare();
                             Toast.makeText(, "toast", Toast.LENGTH_LONG).show();
                             Looper.loop();
                            flag=false;
                        }
                        //通过输出流发送数据给服务端
                        Initialization.out = Initialization.socket.getOutputStream();
                        Initialization.inputStream =Initialization.socket.getInputStream();
                        if(monconnect!=null&&flag) monconnect.run(true);
                        //Toast.makeText(getApplicationContext(),"已连接",Toast.LENGTH_SHORT).show();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("提示", "连接异常");
            }
            //socket=device.createRfcommSocketToServiceRecord(uuid);
        }
    }

    public static class SendThread extends Thread {

        private  boolean flag=true;
        public  void setFlag(boolean flag){
            this.flag=flag;
        }
        @Override
        public void run() {
            try {
                while (this.flag) {
                    //速度 and 航向 and 横滚 and 俯仰
                    char[] data = DataManage.Behavior((char) Initialization.initial.speed, (char) Initialization.initial.course, (char) Initialization.initial.Rollover, (char) Initialization.initial.pitch);
                    byte[] byteData = DataManage.charToByteArray(data);

                    try {
                        Initialization.out.write(byteData);//发送通信数组给飞机
                        Thread.sleep(500);//5毫秒发送一次数据
                    } catch (Exception e) {
                        flag=false;
                        System.out.println("数据发送出错！");
                    }
                }
            } catch (Exception e) {
                System.out.println("数据发送出错！");
            }
        }
    }
}
//    public static class SenTest implements Runnable{
//
//
//        @Override
//        public void run() {
//            try{
//                while(Initialization.lock_bool){
//                    System.out.println(""+Initialization.initial.speed);
//                }
//            }
//            catch (Exception e) {
//                System.out.println("线程报错!");
//            }
//        }
//    }

