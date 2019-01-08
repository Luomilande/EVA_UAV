package com.example.admin.myapplication;

public class DataManage {
    private static final char headers = 0xAA;//协议头
    private static final char Write_state=0xC0;//写状态
    private static final char fixed_head_0=0x1C;//固定值
    private static final char fixed_end_0=0x1C;//固定值
    private static final char fixed_end_1=0x0D;//固定值
    private static final char fixed_end_2=0x0A;//固定值

    public static char[] initdata() {//通信数组赋值
        char[] data=new char[34];
        data[0] = headers;
        data[1] = Write_state;
        data[2] = fixed_head_0;

        data[31] = fixed_end_0;
        data[32] = fixed_end_1;
        data[33] = fixed_end_2;
        return data;
    }
    //将Int数据分高低位。
    public static BitData getHighBitLowBit(char data) {
        return new BitData((char) (data >> 8), (char) (data & 0xFF));
    }

    public static int mergeHighBitLowBit(char h, char l) {
        return (h << 8) | l;
    }
    public static char[] Behavior(char power, char course,char roll, char pitching) {
       char[] data= initdata();
        BitData bitData = getHighBitLowBit(power);//油门高低
        data[3]=bitData.HIGH;
        data[4]=bitData.LOW;
        bitData =getHighBitLowBit(course);//航向
        data[5]=bitData.HIGH;
        data[6]=bitData.LOW;
        bitData = getHighBitLowBit(roll);//横滚
        data[7]=bitData.HIGH;
        data[8]=bitData.LOW;
        bitData = getHighBitLowBit(pitching);//俯仰
        data[9]=bitData.HIGH;
        data[10]=bitData.LOW;
        return  data;
    }
    public static byte[] charToByteArray(char[] chars) {
        byte[] bytes = new byte[34];
        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i];
        }
        return bytes;
    }

}
class BitData {
    public final char HIGH;
    public final char LOW;

    public BitData(char h, char l) {
        HIGH = h;
        LOW = l;
    }


    @Override
    public String toString() {
        return "BitData{" +
                "H=" + HIGH +
                ", L=" + LOW +
                '}';
    }


}



