package com.android.homework.bean;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;



/**
 * 基座
 */

public class BaseControlBean {


    /** 0x0100第一个字节 **/
    // 1~2.基座行走推杆位置信号 00-前进 01-倒车 10-停止 11-未定义

    private MutableLiveData<Integer> baseWalkingPushRodPositionSignal = new MutableLiveData<>();
    // 3~4.行走传感器故障信息 00-无故障 01-传感器故障 10-传感器故障 11-同时故障

    private MutableLiveData<Integer> walkingSensorFaultInformation = new MutableLiveData<>();
    // 5~6.基座换挡推杆位置信号 00-加档 01-减档 10-空挡 11-未定义

    private MutableLiveData<Integer> positionSignalOfBaseShiftPushRod = new MutableLiveData<>();
    // 7~8.换挡传感器故障信息 00-无故障 01-传感器故障 10-传感器故障 11-同时故障

    private MutableLiveData<Integer> shiftSensorFaultInformation = new MutableLiveData<>();
    /** 0x0100第二个字节 **/
    // 1~8.行走前推开度
    private MutableLiveData<Integer> degreeOfPushBeforeWalking = new MutableLiveData<>();
    /** 0x0100第三个字节 **/
    // 1~8.行走后推开度
    private MutableLiveData<Integer> degreeOfPushAfterWalking = new MutableLiveData<>();
    /** 0x0100第四个字节 **/
    // 1~8.换挡前推开度

    private MutableLiveData<Integer> degreeOfPushBeforeShifting = new MutableLiveData<>();
    /** 0x0100第五个字节 **/
    // 1~8.换挡后推开度
    private MutableLiveData<Integer> degreeOfPushAfterShifting = new MutableLiveData<>();
    /** 0x0100第六个字节 **/
    // 1~8.未定义
    /** 0x0100第七个字节 **/
    // 1~8.未定义

    @Override
    public String toString() {
        return "BaseControlBean{" +
                "baseWalkingPushRodPositionSignal=" + baseWalkingPushRodPositionSignal +
                ", walkingSensorFaultInformation=" + walkingSensorFaultInformation +
                ", positionSignalOfBaseShiftPushRod=" + positionSignalOfBaseShiftPushRod +
                ", shiftSensorFaultInformation=" + shiftSensorFaultInformation +
                ", degreeOfPushBeforeWalking=" + degreeOfPushBeforeWalking +
                ", degreeOfPushAfterWalking=" + degreeOfPushAfterWalking +
                ", degreeOfPushBeforeShifting=" + degreeOfPushBeforeShifting +
                ", degreeOfPushAfterShifting=" + degreeOfPushAfterShifting +
                '}';
    }

    /** 0x0100第八个字节 **/
    // 1~8.未定义


    public MutableLiveData<Integer> getBaseWalkingPushRodPositionSignal() {
        return baseWalkingPushRodPositionSignal;
    }

    public void setBaseWalkingPushRodPositionSignal(int baseWalkingPushRodPositionSignal) {
        this.baseWalkingPushRodPositionSignal.postValue(baseWalkingPushRodPositionSignal);
    }

    public MutableLiveData<Integer> getWalkingSensorFaultInformation() {
        return walkingSensorFaultInformation;
    }

    public void setWalkingSensorFaultInformation(int walkingSensorFaultInformation) {
        this.walkingSensorFaultInformation.postValue(walkingSensorFaultInformation);
    }

    public MutableLiveData<Integer> getPositionSignalOfBaseShiftPushRod() {
        return positionSignalOfBaseShiftPushRod;
    }

    public void setPositionSignalOfBaseShiftPushRod(int positionSignalOfBaseShiftPushRod) {
        this.positionSignalOfBaseShiftPushRod.postValue(positionSignalOfBaseShiftPushRod);
    }

    public MutableLiveData<Integer> getShiftSensorFaultInformation() {
        return shiftSensorFaultInformation;
    }

    public void setShiftSensorFaultInformation(int shiftSensorFaultInformation) {
        this.shiftSensorFaultInformation.postValue(shiftSensorFaultInformation);
    }

    public MutableLiveData<Integer> getDegreeOfPushBeforeWalking() {
        return degreeOfPushBeforeWalking;
    }

    public void setDegreeOfPushBeforeWalking(int degreeOfPushBeforeWalking) {
        this.degreeOfPushBeforeWalking .postValue(degreeOfPushBeforeWalking);
    }

    public MutableLiveData<Integer> getDegreeOfPushAfterWalking() {
        return degreeOfPushAfterWalking;
    }

    public void setDegreeOfPushAfterWalking(int degreeOfPushAfterWalking) {
        this.degreeOfPushAfterWalking.postValue(degreeOfPushAfterWalking);
    }

    public MutableLiveData<Integer> getDegreeOfPushBeforeShifting() {
        return degreeOfPushBeforeShifting;
    }

    public void setDegreeOfPushBeforeShifting(int degreeOfPushBeforeShifting) {
        this.degreeOfPushBeforeShifting.postValue(degreeOfPushBeforeShifting);
    }

    public MutableLiveData<Integer> getDegreeOfPushAfterShifting() {
        return degreeOfPushAfterShifting;
    }

    public void setDegreeOfPushAfterShifting(int degreeOfPushAfterShifting) {
        this.degreeOfPushAfterShifting.postValue(degreeOfPushAfterShifting);
    }
}
