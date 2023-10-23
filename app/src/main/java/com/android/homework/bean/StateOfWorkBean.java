package com.android.homework.bean;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;


/**
 * 工作状态
 */

public class StateOfWorkBean {

    /** 0x0501第一，二个字节 **/
    // 1~2.拨禾轮转速/rpm
    private MutableLiveData<Integer> rotationSpeedOfTheWheel = new MutableLiveData<>();
    /** 0x0501第三，四个字节 **/
    // 3~4.轴流滚筒转速/rpm
    private MutableLiveData<Integer> axialFlowDrumSpeed = new MutableLiveData<>();
    /** 0x0501第五，六个字节 **/
    // 5~6.籽粒搅龙转速/rpm
    private MutableLiveData<Integer> grainAugerRotationSpeed = new MutableLiveData<>();
    /** 0x0501第七，八个字节 **/
    // 7~8.风机转速/rpm
    private MutableLiveData<Integer> speedOfFan = new MutableLiveData<>();

    @Override
    public String toString() {
        return "StateOfWorkBean{" +
                "rotationSpeedOfTheWheel=" + rotationSpeedOfTheWheel +
                ", axialFlowDrumSpeed=" + axialFlowDrumSpeed +
                ", grainAugerRotationSpeed=" + grainAugerRotationSpeed +
                ", speedOfFan=" + speedOfFan +
                '}';
    }

    public MutableLiveData<Integer> getRotationSpeedOfTheWheel() {
        return rotationSpeedOfTheWheel;
    }

    public void setRotationSpeedOfTheWheel(int rotationSpeedOfTheWheel) {
        this.rotationSpeedOfTheWheel.postValue(rotationSpeedOfTheWheel);
        Log.d("mStateOfWorkList", "11111111111111111111111: " );
    }

    public MutableLiveData<Integer> getAxialFlowDrumSpeed() {
        return axialFlowDrumSpeed;
    }

    public void setAxialFlowDrumSpeed(int axialFlowDrumSpeed) {
        this.axialFlowDrumSpeed .postValue(axialFlowDrumSpeed);
    }

    public MutableLiveData<Integer> getGrainAugerRotationSpeed() {
        return grainAugerRotationSpeed;
    }

    public void setGrainAugerRotationSpeed(int grainAugerRotationSpeed) {
        this.grainAugerRotationSpeed .postValue(grainAugerRotationSpeed);
    }

    public MutableLiveData<Integer> getSpeedOfFan() {
        return speedOfFan;
    }

    public void setSpeedOfFan(int speedOfFan) {
        this.speedOfFan .postValue(speedOfFan);
    }
}
