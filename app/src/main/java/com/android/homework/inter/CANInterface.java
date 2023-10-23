package com.android.homework.inter;

public class CANInterface {
    static {
        System.loadLibrary("can_interface"); // 加载本地库
    }

    // 本地方法声明：打开CAN1接口
    public native boolean openCan1Interface(String deviceName);
    // 本地方法声明：发送CAN1数据
    public native boolean sendCan1Message(int canId, byte[] data);
    // 本地方法声明：接收CAN1数据
    public native byte[] receiveCan1Message();
    // 本地方法声明：关闭CAN1接口
    public native void closeCan1Interface();

    // 本地方法声明：打开CAN2接口
    public native boolean openCan2Interface(String deviceName);
    // 本地方法声明：发送CAN2数据
    public native boolean sendCan2Message(int canId, byte[] data);
    // 本地方法声明：接收CAN2数据
    public native byte[] receiveCan2Message();
    // 本地方法声明：关闭CAN2接口
    public native void closeCan2Interface();
}


