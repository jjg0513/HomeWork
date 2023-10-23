package com.android.homework.observer;

import android.util.Log;

import com.android.homework.inter.CANInterface;
import com.android.homework.utils.DataUtils;

/**
 * @auther 19062
 * @date 2023/9/16
 * @time 16:39.
 */


public class CanOneChangObserver implements Runnable{

    private boolean isRunning;
    private CANInterface canInterface;
    private CanChangeListener mCanChangeListener;


    public CanOneChangObserver() {
        Log.d("ccm======>","canOneChangObserver 2");
        isRunning = false;
        canInterface = new CANInterface();
        startListening();
    }

    public CanChangeListener getCanChangeListener() {
        return mCanChangeListener;
    }

    // 设置WIFI改变的监听器
    public void setCanChangeListener(CanChangeListener canChangeListener) {
        mCanChangeListener = canChangeListener;
    }

    public interface CanChangeListener {
        /**
         * 监听can1变化
         */
        void onCan1Changed(String part1, String part2);
    }


    public void startListening() {
        if (!isRunning) {
            isRunning = true;
            Log.d("ccm======>","canOneChangObserver 3");
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    public void stopListening() {
        isRunning = false;
    }

    @Override
    public void run() {
        Log.d("ccm======>","canOneChangObserver 4");
        while (isRunning) {

            byte[] receivedCan1Data = canInterface.receiveCan1Message();

            if (receivedCan1Data != null) {
                Log.d("ccm------>", "run: receivedCan1Data 1：" + receivedCan1Data.length);
                // 拆分16字节数据为两个8字节数据
                // 帧字节
                byte[] partCan1Id = new byte[8];
                // 报文字节
                byte[] partCan1Data = new byte[8];
                System.arraycopy(receivedCan1Data, 0, partCan1Id, 0, 8);
                System.arraycopy(receivedCan1Data, 8, partCan1Data, 0, 8);
                Log.d("ccm------>", "run: 接收到了数据 1：" + DataUtils.ByteArrToHex(partCan1Id)+" , String: "+DataUtils.ByteArrToHex(partCan1Data));
                mCanChangeListener.onCan1Changed(DataUtils.ByteArrToHex(partCan1Id),DataUtils.ByteArrToHex(partCan1Data));

            }

        }
    }

}
