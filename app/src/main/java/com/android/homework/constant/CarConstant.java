package com.android.homework.constant;

public class CarConstant {

    public static final String DEVICES_CAN1= "/dev/vircan";
    public static final String DEVICES_CAN2= "/dev/vircan1";

    /** 接收端报文帧ID **/
    // 实时状态
    public static final String REALTIME_STATUS_ID = "0x0500";
    // 工作状态
    public static final String WORKING_CONDITION_ID = "0x0501";
    // 输出控制
    public static final String OUTPUT_CONTROL_ID = "0x0502";
    // 输入诊断
    public static final String INPUT_DIAGNOSIS_ID = "0x0503";
    // 基座控制
    public static final String BASE_CONTROL_ID = "0x0100";

    /** 发送端报文帧ID **/
    // 车辆设置
    public static final String VEHICLE_SETTINGS_ID = "0x0300";
    // 比例阀阈值设置
    public static final String PROPORTIONAL_VALUE_THRESHOLD_SETTING_ID = "0x0301";
    // UDS升级
    public static final String UDS_UPGRADE_ID = "0x0000";

    /** CAN1接收端报文帧ID **/
    // 车速
    public static final String SPEED_OF_VEHICLE_ID = "0x0CFE6CEE";
    // 转速
    public static final String ROTATIONAL_SPEED_ID = "0x0CF00400";

    // 尿素低报警灯
    public static final String REACTANT_ALLOWANCE_ID = "0x18FE5600";
    // 排气温度过高报警灯 DPF警告灯 DPF禁止
    public static final String ALARM_LIGHT_ID = "0x18FD7C00";
    // 冷却液温度过高警告灯
    public static final String WATER_TEMPERATURE_ID = "0x18FEEE00";
    // 机油压力低报警
    public static final String OIL_PRESSURE_ID = "0x18FEEF00";
    // 柴油发动机油水分离警告灯
    public static final String WATER_IN_OIL_ID = "0x18FEFF00";
    // 柴油发动机预热警告灯
    public static final String ENGINE_WARM_UP_LIGHT_ID = "0x18FEE400";
    // 充电系统故障警告灯
    public static final String ENGINE_INPUT_VOLTAGE_ID = "0x18FEF700";
    // 信息通告报文
    public static final String INFORMATION_CIRCULAR_ID = "0x18ECFF00";
    // 多个故障码报文
    public static final String MULTIPLE_FAULT_CODES_ID = "0x18EBFF00";
    /*单个故障码报文*/
    public static final String SINGLE_FAULT_CODE_ID = "0x18FECA00";

}
