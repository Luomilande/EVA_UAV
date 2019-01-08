package com.example.admin.myapplication;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.view.View;

import java.io.OutputStream;
import java.util.UUID;

public class Initialization {
    public static   String address="20:F7:7C:85:5E:84";//蓝牙mac地址20:F7:7C:85:5E:84 00:0E:0E:15:85:04
    public static   OutputStream out;//定义输出流
    public static   BluetoothSocket socket;
    static  class initial{
        public static int speed=0;//油门0-1000
        public static int course=1500;//航向0-3000，顺时针偏转：0-1500，逆时针偏转：1500-3000
        public static int pitch=1500;//俯仰0-3000，向后：0-1500，向前：1500-3000
        public static int Rollover=1500;//横滚0-3000，向右：0-1500，向左：1500-3000
    }
}
