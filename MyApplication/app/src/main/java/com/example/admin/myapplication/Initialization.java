package com.example.admin.myapplication;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.view.View;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class Initialization {
    public static String address="00:0E:0E:0E:31:17";//蓝牙mac地址20:F7:7C:85:5E:84 00:0E:0E:15:85:04,00:0E:0E:15:85:04
    public static OutputStream out;//定义输出流
    public static BluetoothSocket socket;
    public static boolean lock_bool=true;
    protected static Handler handler;
    public static OutputStream outputStream;
    public static InputStream inputStream;

    /**
     * 飞机数组传输值
     */
    static class initial {
        public static int speed = 100;//油门0-1000
        public static int course = 1500;//航向0-3000，顺时针偏转：0-1500，逆时针偏转：1500-3000
        public static int pitch = 1725;//俯仰0-3000，向后：0-1500，向前：1500-3000
        public static int Rollover = 1240;//横滚0-3000，向右：0-1500，向左：1500-3000
        public static int wave=5;//波动值，微调使用
    }

    /**
     * 飞机姿态参数
     */
    static class Aircraft_data
    {
        public static int course_Small = 750;//顺时针偏转：0-1500
        public static int course_Big = 2250;//逆时针偏转：1500-3000
        public static int pitch_Small = 750;//向后：0-1500
        public static int pitch_Big = 2250;//向前：1500-3000
        public static int Rollover_Small = 750;//向右：0-1500
        public static int Rollover_Big = 2250;//向左：1500-3000
    }
}