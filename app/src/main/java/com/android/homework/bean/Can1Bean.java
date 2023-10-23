package com.android.homework.bean;

import androidx.lifecycle.MutableLiveData;

/**
 * @auther 19062
 * @date 2023/9/19
 * @time 9:12.
 */


public class Can1Bean {


    /** 0x0CFE6CEE第七，八个字节 **/
    // 1.车速
    private MutableLiveData<Integer> speedOfVehicle = new MutableLiveData<>();
    /** 0x0CF00400第四，五个字节 **/
    // 1.转速
    private MutableLiveData<Integer> rotationalSpeed = new MutableLiveData<>();
    /** 0x18FE5600第一个字节 **/
    // 1.尿素低报警灯 <10%
    private MutableLiveData<Integer> reactantAllowance = new MutableLiveData<>();
    /** 0x18FD7C00第一个字节 **/
    // 1.DPF警告灯 0-关闭 4-打开
    private MutableLiveData<Integer> dpfWarningLight = new MutableLiveData<>();
    /** 0x18FD7C00第三个字节 **/
    // 2.DPF禁止 0-关闭 4-打开
    private MutableLiveData<Integer> dpfProhibition = new MutableLiveData<>();
    /** 0x18FD7C00第四，五个字节 **/
    // 4.DPF警告灯 000-熄灭；001-常亮；010-慢闪；100-快闪
    private MutableLiveData<Integer> driverWarningLight = new MutableLiveData<>();

    @Override
    public String toString() {
        return "Can1Bean{" +
                "speedOfVehicle=" + speedOfVehicle +
                ", rotationalSpeed=" + rotationalSpeed +
                ", reactantAllowance=" + reactantAllowance +
                ", dpfWarningLight=" + dpfWarningLight +
                ", dpfProhibition=" + dpfProhibition +
                ", driverWarningLight=" + driverWarningLight +
                ", regenerationreminder=" + regenerationreminder +
                ", excessiveExhaustTemperature=" + excessiveExhaustTemperature +
                ", waterTemperature=" + waterTemperature +
                ", oilPressure=" + oilPressure +
                ", waterInOil=" + waterInOil +
                ", engineWarmUpLight=" + engineWarmUpLight +
                ", engineInputVoltage=" + engineInputVoltage +
                ", faultCode=" + faultCode +
                '}';
    }
// 再生提醒  00-熄灭；01-常亮
    private MutableLiveData<Integer> regenerationreminder = new MutableLiveData<>();
    /** 0x18FD7C00第七个字节 **/
    // 5.排气温度过高报警灯 0-关闭 4-打开
    private MutableLiveData<Integer> excessiveExhaustTemperature = new MutableLiveData<>();
    /** 0x18FEEE00第一个字节 **/
    // 1.冷却液温度过高警告灯 < 120
    private MutableLiveData<Integer> waterTemperature = new MutableLiveData<>();
    /** 0x18FEEF00第四个字节 **/
    // 1.机油压力低报警 < 0.25kpa
    private MutableLiveData<Integer> oilPressure = new MutableLiveData<>();
    /** 0x18FEFF00第四，五个字节 **/
    // 1.柴油发动机油水分离警告灯 0-关闭 3-打开
    private MutableLiveData<Integer> waterInOil = new MutableLiveData<>();
    /** 0x18FEE400第四，五个字节 **/
    // 1.柴油发动机预热警告灯
    private MutableLiveData<Integer> engineWarmUpLight = new MutableLiveData<>();
    /** 0x18FEF700第四，五个字节 **/
    // 1.充电系统故障警告灯 <18V
    private MutableLiveData<Float> engineInputVoltage = new MutableLiveData<>();
    /** 0x18FECA00第个字节 **/
    // 1.发动机故障警告灯  0-关闭 1-打开
    private MutableLiveData<Integer> faultCode = new MutableLiveData<>();

    public MutableLiveData<Integer> getSpeedOfVehicle() {
        return speedOfVehicle;
    }

    public void setSpeedOfVehicle(int speedOfVehicle) {
        this.speedOfVehicle.postValue(speedOfVehicle);
    }

    public MutableLiveData<Integer> getRotationalSpeed() {
        return rotationalSpeed;
    }

    public void setRotationalSpeed(int rotationalSpeed) {
        this.rotationalSpeed.postValue(rotationalSpeed);
    }

    public MutableLiveData<Integer> getReactantAllowance() {
        return reactantAllowance;
    }

    public void setReactantAllowance(int reactantAllowance) {
        this.reactantAllowance.postValue(reactantAllowance);
    }

    public MutableLiveData<Integer> getDpfWarningLight() {
        return dpfWarningLight;
    }

    public void setDpfWarningLight(int dpfWarningLight) {
        this.dpfWarningLight.postValue(dpfWarningLight);
    }

    public MutableLiveData<Integer> getDpfProhibition() {
        return dpfProhibition;
    }

    public void setDpfProhibition(int dpfProhibition) {
        this.dpfProhibition.postValue(dpfProhibition);
    }

    public MutableLiveData<Integer> getDriverWarningLight() {
        return driverWarningLight;
    }

    public void setDriverWarningLight(int driverWarningLight) {
        this.driverWarningLight.postValue(driverWarningLight);
    }

    public MutableLiveData<Integer> getRegenerationreminder() {
        return regenerationreminder;
    }

    public void setRegenerationreminder(int regenerationreminder) {
        this.regenerationreminder.postValue(regenerationreminder);
    }

    public MutableLiveData<Integer> getExcessiveExhaustTemperature() {
        return excessiveExhaustTemperature;
    }

    public void setExcessiveExhaustTemperature(int excessiveExhaustTemperature) {
        this.excessiveExhaustTemperature.postValue(excessiveExhaustTemperature);
    }

    public MutableLiveData<Integer> getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(int waterTemperature) {
        this.waterTemperature.postValue(waterTemperature);
    }

    public MutableLiveData<Integer> getOilPressure() {
        return oilPressure;
    }

    public void setOilPressure(int oilPressure) {
        this.oilPressure.postValue(oilPressure);
    }

    public MutableLiveData<Integer> getWaterInOil() {
        return waterInOil;
    }

    public void setWaterInOil(int waterInOil) {
        this.waterInOil.postValue(waterInOil);
    }

    public MutableLiveData<Integer> getEngineWarmUpLight() {
        return engineWarmUpLight;
    }

    public void setEngineWarmUpLight(int engineWarmUpLight) {
        this.engineWarmUpLight.postValue(engineWarmUpLight);
    }

    public MutableLiveData<Float> getEngineInputVoltage() {
        return engineInputVoltage;
    }

    public void setEngineInputVoltage(float engineInputVoltage) {
        this.engineInputVoltage.postValue(engineInputVoltage);
    }

    public MutableLiveData<Integer> getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(int faultCode) {
        this.faultCode.postValue(faultCode);
    }
}
