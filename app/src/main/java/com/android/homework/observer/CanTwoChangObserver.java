package com.android.homework.observer;

import android.util.Log;

import com.android.homework.inter.CANInterface;
import com.android.homework.utils.DataUtils;

/**
 * @auther 19062
 * @date 2023/9/16
 * @time 16:39.
 */


public class CanTwoChangObserver implements Runnable {
    private boolean isRunning;
    private CANInterface canInterface;
    private CanChangeListener mCanChangeListener;



    public CanTwoChangObserver() {
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
         * 监听can2变化
         */
        void onCan2Changed(String part1, String part2);
    }


    public void startListening() {
        if (!isRunning) {
            isRunning = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    public void stopListening() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {

            byte[] receivedCan2Data = canInterface.receiveCan2Message();
            if (receivedCan2Data != null) {
                Log.d("ccm------>", "run: receivedCan1Data 2：" + receivedCan2Data.length);
                // 拆分16字节数据为两个8字节数据
                // 帧字节
                byte[] partCan2Id = new byte[8];
                // 报文字节
                byte[] partCan2Data = new byte[8];
                System.arraycopy(receivedCan2Data, 0, partCan2Id, 0, 8);
                System.arraycopy(receivedCan2Data, 8, partCan2Data, 0, 8);
                Log.d("ccm------>", "run: 接收到了数据 2：" + DataUtils.ByteArrToHex(partCan2Id)+" , String: "+DataUtils.ByteArrToHex(partCan2Data));
                mCanChangeListener.onCan2Changed(DataUtils.ByteArrToHex(partCan2Id),DataUtils.ByteArrToHex(partCan2Data));

            }

        }
    }

}
