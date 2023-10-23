package com.android.homework.bean;




import androidx.lifecycle.MutableLiveData;



/**
 * 灯光
 */

public class RealTimeStatusBean {



    /** 0x0500第一个字节 **/
    // 1.左转灯信号
    private MutableLiveData<Integer> turnLeftLightSignal = new MutableLiveData<>();
    // 2.右转灯信号
    private MutableLiveData<Integer> rightTurnLightSignal = new MutableLiveData<>();
    // 3.远光灯信号
    private MutableLiveData<Integer> highBeamSignal = new MutableLiveData<>();
    // 4.近光灯信号
    private MutableLiveData<Integer> nearLightSignal = new MutableLiveData<>();
    // 5.示廓灯信号
    private MutableLiveData<Integer> signalOfProfileLight = new MutableLiveData<>();
    // 6.后雾灯
    private MutableLiveData<Integer> rearFogLamp = new MutableLiveData<>();
    // 7.主驾驶安全带
    private MutableLiveData<Integer> mainDrivingSeatBelt = new MutableLiveData<>();
    // 8.副驾驶安全带
    private MutableLiveData<Integer> passengerSeatBelt = new MutableLiveData<>();
    /** 0x0500第二个字节 **/
    // 1.刹车油液位过低
    private MutableLiveData<Integer> positionLight = new MutableLiveData<>();
    // 2.驻车信号（手刹信号）
    private MutableLiveData<Integer> parkingSignal = new MutableLiveData<>();
    // 3.发电信号
    private MutableLiveData<Integer> powerGenerationSignal = new MutableLiveData<>();
    // 4.倒车信号
    private MutableLiveData<Integer> reverseSignal = new MutableLiveData<>();
    // 5.未定义
//    private int positionLight = -1;
    // 6~8.燃油液位/格
    private MutableLiveData<Integer> fuelLevel = new MutableLiveData<>();
    /** 0x0500第三个字节 **/
    // 1~8.液压油温/℃
    private MutableLiveData<Integer> hydraulicOilTemperature = new MutableLiveData<>();
    /** 0x0500第四个字节 **/
    // 1~8.气罐压力/Mpa
    private MutableLiveData<Float> gasTankPressure = new MutableLiveData<>();
    /** 0x0500第五个字节 **/
    // 1~8.割台角度传感器
    private MutableLiveData<Integer> cuttingTableAngleSensor = new MutableLiveData<>();
    /** 0x0500第六个字节 **/
    // 1~8.卸粮筒角度传感器
    private MutableLiveData<Integer> unloadingCylinderAngleSensor = new MutableLiveData<>();
    /** 0x0500第七个字节 **/
    // 1.工作模式 0-作业模式 1-行走模式
    private MutableLiveData<Integer> modeOfWork = new MutableLiveData<>();
    // 2~3.自动驾驶状态 00-处于准备状态（灰） 01-启用状态（绿色） 10-激活状态（橙黄色） 11-功能故障（红色）
    private MutableLiveData<Integer> autopilotStatus = new MutableLiveData<>();
    // 4~8.未定义

    @Override
    public String toString() {
        return "RealTimeStatusBean{" +
                "turnLeftLightSignal=" + turnLeftLightSignal +
                ", rightTurnLightSignal=" + rightTurnLightSignal +
                ", highBeamSignal=" + highBeamSignal +
                ", nearLightSignal=" + nearLightSignal +
                ", signalOfProfileLight=" + signalOfProfileLight +
                ", rearFogLamp=" + rearFogLamp +
                ", mainDrivingSeatBelt=" + mainDrivingSeatBelt +
                ", passengerSeatBelt=" + passengerSeatBelt +
                ", positionLight=" + positionLight +
                ", parkingSignal=" + parkingSignal +
                ", powerGenerationSignal=" + powerGenerationSignal +
                ", reverseSignal=" + reverseSignal +
                ", fuelLevel=" + fuelLevel +
                ", hydraulicOilTemperature=" + hydraulicOilTemperature +
                ", gasTankPressure=" + gasTankPressure +
                ", cuttingTableAngleSensor=" + cuttingTableAngleSensor +
                ", unloadingCylinderAngleSensor=" + unloadingCylinderAngleSensor +
                ", modeOfWork=" + modeOfWork +
                ", autopilotStatus=" + autopilotStatus +
                '}';
    }

    /** 0x0500第八个字节 **/
    // 1～8.未定义



    public MutableLiveData<Integer> getTurnLeftLightSignal() {
        return turnLeftLightSignal;
    }

    public void setTurnLeftLightSignal(int turnLeftLightSignal) {
        this.turnLeftLightSignal.postValue(turnLeftLightSignal);
    }

    public MutableLiveData<Integer> getRightTurnLightSignal() {
        return rightTurnLightSignal;
    }

    public void setRightTurnLightSignal(int rightTurnLightSignal) {
        this.rightTurnLightSignal.postValue(rightTurnLightSignal);
    }

    public MutableLiveData<Integer> getHighBeamSignal() {
        return highBeamSignal;
    }

    public void setHighBeamSignal(int highBeamSignal) {
        this.highBeamSignal .postValue(highBeamSignal);
    }

    public MutableLiveData<Integer> getNearLightSignal() {
        return nearLightSignal;
    }

    public void setNearLightSignal(int nearLightSignal) {
        this.nearLightSignal.postValue(nearLightSignal);
    }

    public MutableLiveData<Integer> getSignalOfProfileLight() {
        return signalOfProfileLight;
    }

    public void setSignalOfProfileLight(int signalOfProfileLight) {
        this.signalOfProfileLight.postValue(signalOfProfileLight);
    }

    public MutableLiveData<Integer> getRearFogLamp() {
        return rearFogLamp;
    }

    public void setRearFogLamp(int rearFogLamp) {
        this.rearFogLamp.postValue(rearFogLamp);
    }

    public MutableLiveData<Integer> getMainDrivingSeatBelt() {
        return mainDrivingSeatBelt;
    }

    public void setMainDrivingSeatBelt(int mainDrivingSeatBelt) {
        this.mainDrivingSeatBelt.postValue(mainDrivingSeatBelt);
    }

    public MutableLiveData<Integer> getPassengerSeatBelt() {
        return passengerSeatBelt;
    }

    public void setPassengerSeatBelt(int passengerSeatBelt) {
        this.passengerSeatBelt.postValue(passengerSeatBelt);
    }

    public MutableLiveData<Integer> getPositionLight() {
        return positionLight;
    }

    public void setPositionLight(int positionLight) {
        this.positionLight.postValue(positionLight);
    }

    public MutableLiveData<Integer> getParkingSignal() {
        return parkingSignal;
    }

    public void setParkingSignal(int parkingSignal) {
        this.parkingSignal .postValue(parkingSignal);
    }

    public MutableLiveData<Integer> getPowerGenerationSignal() {
        return powerGenerationSignal;
    }

    public void setPowerGenerationSignal(int powerGenerationSignal) {
        this.powerGenerationSignal.postValue(powerGenerationSignal);
    }

    public MutableLiveData<Integer> getReverseSignal() {
        return reverseSignal;
    }

    public void setReverseSignal(int reverseSignal) {
        this.reverseSignal.postValue(reverseSignal);
    }

    public MutableLiveData<Integer> getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel .postValue(fuelLevel);
    }

    public MutableLiveData<Integer> getHydraulicOilTemperature() {
        return hydraulicOilTemperature;
    }

    public void setHydraulicOilTemperature(int hydraulicOilTemperature) {
        this.hydraulicOilTemperature .postValue(hydraulicOilTemperature);
    }

    public MutableLiveData<Float> getGasTankPressure() {
        return gasTankPressure;
    }

    public void setGasTankPressure( float gasTankPressure) {
        this.gasTankPressure .postValue(gasTankPressure);
    }

    public MutableLiveData<Integer> getCuttingTableAngleSensor() {
        return cuttingTableAngleSensor;
    }

    public void setCuttingTableAngleSensor(int cuttingTableAngleSensor) {
        this.cuttingTableAngleSensor .postValue(cuttingTableAngleSensor);
    }

    public MutableLiveData<Integer> getUnloadingCylinderAngleSensor() {
        return unloadingCylinderAngleSensor;
    }

    public void setUnloadingCylinderAngleSensor(int unloadingCylinderAngleSensor) {
        this.unloadingCylinderAngleSensor .postValue(unloadingCylinderAngleSensor);
    }

    public MutableLiveData<Integer> getModeOfWork() {
        return modeOfWork;
    }

    public void setModeOfWork(int modeOfWork) {
        this.modeOfWork.postValue(modeOfWork);
    }

    public MutableLiveData<Integer> getAutopilotStatus() {
        return autopilotStatus;
    }

    public void setAutopilotStatus(int autopilotStatus) {
        this.autopilotStatus.postValue(autopilotStatus);
    }
}
