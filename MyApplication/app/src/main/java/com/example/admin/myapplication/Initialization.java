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
     * 飞机-当前使用数据
     */
    static class initial {
        public static int speed = 100;//油门0-1000
        public static int course = 1500;//航向0-3000，顺时针偏转：0-1500，逆时针偏转：1500-3000
        public static int pitch = 1500;//俯仰0-3000，向后：0-1500，向前：1500-3000
        public static int Rollover = 1500;//横滚0-3000，向右：0-1500，向左：1500-3000
        public static int wave=5;//波动值，微调使用
    }

    /**
     *
     */
    static class Aly_storage{
        public static int course ;//航向0-3000，顺时针偏转：0-1500，逆时针偏转：1500-3000
        public static int pitch ;//俯仰0-3000，向后：0-1500，向前：1500-3000
        public static int Rollover ;//横滚0-3000，向右：0-1500，向左：1500-3000
        public static int wave=5;//波动值，微调使用
    }

    /**
     * 飞机-控制-参数
     */
    static class Aircraft_data
    {
        public static int course_Small = 1350;//顺时针偏转：0-1500
        public static int course_Big = 1650;//逆时针偏转：1500-3000
        public static int pitch_Small = 1350;//向后：0-1500
        public static int pitch_Big = 1650;//向前：1500-3000
        public static int Rollover_Small = 1350;//向右：0-1500
        public static int Rollover_Big = 1650;//向左：1500-3000
    }
}