package com.android.homework.bean;




import androidx.lifecycle.MutableLiveData;



/**
 * 输出控制
 */

public class OutputControlBean {

    /**
     * 0x0502第一个字节
     **/
    // 1~2.Power_OUT1 00-无输出 01-输出高电平 10-开路故障 11-短路故障
    private MutableLiveData<Integer> powerOut1 = new MutableLiveData<>();
    // 3~4.Power_OUT2
    private MutableLiveData<Integer> powerOut2 = new MutableLiveData<>();
    // 5~6.Power_OUT3
    private MutableLiveData<Integer> powerOut3 = new MutableLiveData<>();
    // 7~8.Power_OUT4
    private MutableLiveData<Integer> powerOut4 = new MutableLiveData<>();
    /**
     * 0x0502第二个字节
     **/
    // 1~2.Power_OUT5
    private MutableLiveData<Integer> powerOut5 = new MutableLiveData<>();
    // 3~4.Power_OUT6
    private MutableLiveData<Integer> powerOut6 = new MutableLiveData<>();
    // 5~6.Power_OUT7
    private MutableLiveData<Integer> powerOut7 = new MutableLiveData<>();
    // 7~8.Power_OUT8
    private MutableLiveData<Integer> powerOut8 = new MutableLiveData<>();
    /**
     * 0x0502第三个字节
     **/
    // 1~2.Power_OUT9
    private MutableLiveData<Integer> powerOut9 = new MutableLiveData<>();
    // 3~4.Power_OUT10
    private MutableLiveData<Integer> powerOut10 = new MutableLiveData<>();
    // 5~6.Power_OUT11
    private MutableLiveData<Integer> powerOut11 = new MutableLiveData<>();
    // 7~8.Power_OUT12
    private MutableLiveData<Integer> powerOut12 = new MutableLiveData<>();
    /**
     * 0x0502第四个字节
     **/
    // 1~2.Power_OUT13
    private MutableLiveData<Integer> powerOut13 = new MutableLiveData<>();
    // 3~4.Power_OUT14
    private MutableLiveData<Integer> powerOut14 = new MutableLiveData<>();
    // 5~6.Power_OUT15
    private MutableLiveData<Integer> powerOut15 = new MutableLiveData<>();
    // 7~8.Power_OUT16
    private MutableLiveData<Integer> powerOut16 = new MutableLiveData<>();
    /**
     * 0x0502第五个字节
     **/
    // 1~2.Power_OUT17
    private MutableLiveData<Integer> powerOut17 = new MutableLiveData<>();
    // 3~4.Power_OUT18
    private MutableLiveData<Integer> powerOut18 = new MutableLiveData<>();
    // 5~6.Power_OUT19
    private MutableLiveData<Integer> powerOut19 = new MutableLiveData<>();
    // 7~8.Power_OUT20
    private MutableLiveData<Integer> powerOut20 = new MutableLiveData<>();
    /**
     * 0x0502第六个字节
     **/
    // 1~2.Power_OUT21
    private MutableLiveData<Integer> powerOut21 = new MutableLiveData<>();
    // 3~4.Power_OUT22
    private MutableLiveData<Integer> powerOut22 = new MutableLiveData<>();
    // 5~6.Power_OUT23
    private MutableLiveData<Integer> powerOut23 = new MutableLiveData<>();
    // 7~8.Power_OUT24
    private MutableLiveData<Integer> powerOut24 = new MutableLiveData<>();
    /**
     * 0x0502第七个字节
     **/
    // 1~2.比例阀输出1
    private MutableLiveData<Integer> proportionalValveOutput1 = new MutableLiveData<>();
    // 3~4.比例阀输出2
    private MutableLiveData<Integer> proportionalValveOutput2 = new MutableLiveData<>();
    // 5~6.比例阀输出3
    private MutableLiveData<Integer> proportionalValveOutput3 = new MutableLiveData<>();
    // 7~8.比例阀输出4
    private MutableLiveData<Integer> proportionalValveOutput4 = new MutableLiveData<>();
    /**
     * 0x0502第八个字节
     **/
    // 1.NEGATIVE_OUT1
    private MutableLiveData<Integer> negativeOut1 = new MutableLiveData<>();
    // 2.NEGATIVE_OUT2
    private MutableLiveData<Integer> negativeOut2 = new MutableLiveData<>();
    // 3.NEGATIVE_OUT3
    private MutableLiveData<Integer> negativeOut3 = new MutableLiveData<>();
    // 4.NEGATIVE_OUT4
    private MutableLiveData<Integer> negativeOut4 = new MutableLiveData<>();
    // 5.推杆2H输出
    private MutableLiveData<Integer> pushRod2HOutput = new MutableLiveData<>();
    // 6.推杆2L输出
    private MutableLiveData<Integer> pushRod2LOutput = new MutableLiveData<>();
    // 7.推杆4H输出
    private MutableLiveData<Integer> pushRod4HOutput = new MutableLiveData<>();
    // 8.推杆4L输出
    private MutableLiveData<Integer> pushRod4LOutput = new MutableLiveData<>();

    public int getNumber() {

        if (powerOut1.getValue() == null && powerOut2.getValue() == null && powerOut3.getValue() == null && powerOut4.getValue() == null&& powerOut5.getValue() == null
                && powerOut6.getValue() == null&& powerOut7.getValue() == null&& powerOut8.getValue() == null&& powerOut9.getValue() == null&& powerOut10.getValue() == null
                && powerOut11.getValue() == null&& powerOut12.getValue() == null&& powerOut13.getValue() == null&& powerOut14.getValue() == null&& powerOut15.getValue() == null
                && powerOut16.getValue() == null&& powerOut17.getValue() == null&& powerOut18.getValue() == null&& powerOut19.getValue() == null&& powerOut20.getValue() == null
                && powerOut21.getValue() == null&& powerOut22.getValue() == null

                && proportionalValveOutput1.getValue() == null&& proportionalValveOutput2.getValue() == null&& proportionalValveOutput3.getValue() == null
                && proportionalValveOutput4.getValue() == null&& negativeOut1.getValue() == null && negativeOut2.getValue() == null&& negativeOut3.getValue() == null
                && negativeOut4.getValue() == null&& pushRod2HOutput.getValue() == null&& pushRod2LOutput.getValue() == null
                && pushRod4HOutput.getValue() == null&& pushRod4LOutput.getValue() == null){
            return 0;
        }else {
            return powerOut1.getValue().intValue() + powerOut2.getValue().intValue() + powerOut3.getValue().intValue() + powerOut4.getValue().intValue()
                    + powerOut5.getValue().intValue() + powerOut6.getValue().intValue() + powerOut7.getValue().intValue() + powerOut8.getValue().intValue()
                    + powerOut9.getValue().intValue() + powerOut10.getValue().intValue() + powerOut11.getValue().intValue() + powerOut12.getValue().intValue()
                    + powerOut13.getValue().intValue() + powerOut14.getValue().intValue() + powerOut15.getValue().intValue() + powerOut16.getValue().intValue()
                    + powerOut17.getValue().intValue() + powerOut18.getValue().intValue() + powerOut19.getValue().intValue() + powerOut20.getValue().intValue()
                    + powerOut21.getValue().intValue() + powerOut22.getValue().intValue()
                    + proportionalValveOutput1.getValue().intValue() + proportionalValveOutput2.getValue().intValue()
                    + proportionalValveOutput3.getValue().intValue() + proportionalValveOutput4.getValue().intValue()
                    + negativeOut1.getValue().intValue() + negativeOut2.getValue().intValue() + negativeOut3.getValue().intValue() + negativeOut4.getValue().intValue()
                    + pushRod2HOutput.getValue().intValue() + pushRod2LOutput.getValue().intValue() + pushRod4HOutput.getValue().intValue() + pushRod4LOutput.getValue().intValue();
        }


    }



    private MutableLiveData<Integer> number = new MutableLiveData<>();

    @Override
    public String toString() {
        return "OutputControlBean{" +
                "powerOut1=" + powerOut1 +
                ", powerOut2=" + powerOut2 +
                ", powerOut3=" + powerOut3 +
                ", powerOut4=" + powerOut4 +
                ", powerOut5=" + powerOut5 +
                ", powerOut6=" + powerOut6 +
                ", powerOut7=" + powerOut7 +
                ", powerOut8=" + powerOut8 +
                ", powerOut9=" + powerOut9 +
                ", powerOut10=" + powerOut10 +
                ", powerOut11=" + powerOut11 +
                ", powerOut12=" + powerOut12 +
                ", powerOut13=" + powerOut13 +
                ", powerOut14=" + powerOut14 +
                ", powerOut15=" + powerOut15 +
                ", powerOut16=" + powerOut16 +
                ", powerOut17=" + powerOut17 +
                ", powerOut18=" + powerOut18 +
                ", powerOut19=" + powerOut19 +
                ", powerOut20=" + powerOut20 +
                ", powerOut21=" + powerOut21 +
                ", powerOut22=" + powerOut22 +
                ", powerOut23=" + powerOut23 +
                ", powerOut24=" + powerOut24 +
                ", proportionalValveOutput1=" + proportionalValveOutput1 +
                ", proportionalValveOutput2=" + proportionalValveOutput2 +
                ", proportionalValveOutput3=" + proportionalValveOutput3 +
                ", proportionalValveOutput4=" + proportionalValveOutput4 +
                ", negativeOut1=" + negativeOut1 +
                ", negativeOut2=" + negativeOut2 +
                ", negativeOut3=" + negativeOut3 +
                ", negativeOut4=" + negativeOut4 +
                ", pushRod2HOutput=" + pushRod2HOutput +
                ", pushRod2LOutput=" + pushRod2LOutput +
                ", pushRod4HOutput=" + pushRod4HOutput +
                ", pushRod4LOutput=" + pushRod4LOutput +
                '}';
    }

    public MutableLiveData<Integer> getPowerOut1() {
        return powerOut1;
    }

    public void setPowerOut1(int powerOut1) {
        this.powerOut1.postValue(powerOut1);
    }

    public MutableLiveData<Integer> getPowerOut2() {
        return powerOut2;
    }

    public void setPowerOut2(int powerOut2) {
        this.powerOut2.postValue(powerOut2);
    }

    public MutableLiveData<Integer> getPowerOut3() {
        return powerOut3;
    }

    public void setPowerOut3(int powerOut3) {
        this.powerOut3.postValue(powerOut3);
    }

    public MutableLiveData<Integer> getPowerOut4() {
        return powerOut4;
    }

    public void setPowerOut4(int powerOut4) {
        this.powerOut4.postValue(powerOut4);
    }

    public MutableLiveData<Integer> getPowerOut5() {
        return powerOut5;
    }

    public void setPowerOut5(int powerOut5) {
        this.powerOut5.postValue(powerOut5);
    }

    public MutableLiveData<Integer> getPowerOut6() {
        return powerOut6;
    }

    public void setPowerOut6(int powerOut6) {
        this.powerOut6.postValue(powerOut6);
    }

    public MutableLiveData<Integer> getPowerOut7() {
        return powerOut7;
    }

    public void setPowerOut7(int powerOut7) {
        this.powerOut7.postValue(powerOut7);
    }

    public MutableLiveData<Integer> getPowerOut8() {
        return powerOut8;
    }

    public void setPowerOut8(int powerOut8) {
        this.powerOut8.postValue(powerOut8);
    }

    public MutableLiveData<Integer> getPowerOut9() {
        return powerOut9;
    }

    public void setPowerOut9(int powerOut9) {
        this.powerOut9.postValue(powerOut9);
    }

    public MutableLiveData<Integer> getPowerOut10() {
        return powerOut10;
    }

    public void setPowerOut10(int powerOut10) {
        this.powerOut10.postValue(powerOut10);
    }

    public MutableLiveData<Integer> getPowerOut11() {
        return powerOut11;
    }

    public void setPowerOut11(int powerOut11) {
        this.powerOut11 .postValue(powerOut11);
    }

    public MutableLiveData<Integer> getPowerOut12() {
        return powerOut12;
    }

    public void setPowerOut12(int powerOut12) {
        this.powerOut12.postValue(powerOut12);
    }

    public MutableLiveData<Integer> getPowerOut13() {
        return powerOut13;
    }

    public void setPowerOut13(int powerOut13) {
        this.powerOut13.postValue(powerOut13);
    }

    public MutableLiveData<Integer> getPowerOut14() {
        return powerOut14;
    }

    public void setPowerOut14(int powerOut14) {
        this.powerOut14.postValue(powerOut14);
    }

    public MutableLiveData<Integer> getPowerOut15() {
        return powerOut15;
    }

    public void setPowerOut15(int powerOut15) {
        this.powerOut15.postValue(powerOut15);
    }

    public MutableLiveData<Integer> getPowerOut16() {
        return powerOut16;
    }

    public void setPowerOut16(int powerOut16) {
        this.powerOut16.postValue(powerOut16);
    }

    public MutableLiveData<Integer> getPowerOut17() {
        return powerOut17;
    }

    public void setPowerOut17(int powerOut17) {
        this.powerOut17.postValue(powerOut17);
    }

    public MutableLiveData<Integer> getPowerOut18() {
        return powerOut18;
    }

    public void setPowerOut18(int powerOut18) {
        this.powerOut18.postValue(powerOut18);
    }

    public MutableLiveData<Integer> getPowerOut19() {
        return powerOut19;
    }

    public void setPowerOut19(int powerOut19) {
        this.powerOut19 .postValue(powerOut19);
    }

    public MutableLiveData<Integer> getPowerOut20() {
        return powerOut20;
    }

    public void setPowerOut20(int powerOut20) {
        this.powerOut20.postValue(powerOut20);
    }

    public MutableLiveData<Integer> getPowerOut21() {
        return powerOut21;
    }

    public void setPowerOut21(int powerOut21) {
        this.powerOut21.postValue(powerOut21);
    }

    public MutableLiveData<Integer> getPowerOut22() {
        return powerOut22;
    }

    public void setPowerOut22(int powerOut22) {
        this.powerOut22 .postValue(powerOut22);
    }

    public MutableLiveData<Integer> getPowerOut23() {
        return powerOut23;
    }

    public void setPowerOut23(int powerOut23) {
        this.powerOut23.postValue(powerOut23);
    }

    public MutableLiveData<Integer> getPowerOut24() {
        return powerOut24;
    }

    public void setPowerOut24(int powerOut24) {
        this.powerOut24 .postValue(powerOut24);
    }

    public MutableLiveData<Integer> getProportionalValveOutput1() {
        return proportionalValveOutput1;
    }

    public void setProportionalValveOutput1(int proportionalValveOutput1) {
        this.proportionalValveOutput1.postValue(proportionalValveOutput1);
    }

    public MutableLiveData<Integer> getProportionalValveOutput2() {
        return proportionalValveOutput2;
    }

    public void setProportionalValveOutput2(int proportionalValveOutput2) {
        this.proportionalValveOutput2.postValue(proportionalValveOutput2);
    }

    public MutableLiveData<Integer> getProportionalValveOutput3() {
        return proportionalValveOutput3;
    }

    public void setProportionalValveOutput3(int proportionalValveOutput3) {
        this.proportionalValveOutput3.postValue(proportionalValveOutput3);
    }

    public MutableLiveData<Integer> getProportionalValveOutput4() {
        return proportionalValveOutput4;
    }

    public void setProportionalValveOutput4(int proportionalValveOutput4) {
        this.proportionalValveOutput4.postValue(proportionalValveOutput4);
    }

    public MutableLiveData<Integer> getNegativeOut1() {
        return negativeOut1;
    }

    public void setNegativeOut1(int negativeOut1) {
        this.negativeOut1.postValue(negativeOut1);
    }

    public MutableLiveData<Integer> getNegativeOut2() {
        return negativeOut2;
    }

    public void setNegativeOut2(int negativeOut2) {
        this.negativeOut2 .postValue(negativeOut2);
    }

    public MutableLiveData<Integer> getNegativeOut3() {
        return negativeOut3;
    }

    public void setNegativeOut3(int negativeOut3) {
        this.negativeOut3.postValue(negativeOut3);
    }

    public MutableLiveData<Integer> getNegativeOut4() {
        return negativeOut4;
    }

    public void setNegativeOut4(int negativeOut4) {
        this.negativeOut4.postValue(negativeOut4);
    }

    public MutableLiveData<Integer> getPushRod2HOutput() {
        return pushRod2HOutput;
    }

    public void setPushRod2HOutput(int pushRod2HOutput) {
        this.pushRod2HOutput.postValue(pushRod2HOutput);
    }

    public MutableLiveData<Integer> getPushRod2LOutput() {
        return pushRod2LOutput;
    }

    public void setPushRod2LOutput(int pushRod2LOutput) {
        this.pushRod2LOutput.postValue(pushRod2LOutput);
    }

    public MutableLiveData<Integer> getPushRod4HOutput() {
        return pushRod4HOutput;
    }

    public void setPushRod4HOutput(int pushRod4HOutput) {
        this.pushRod4HOutput.postValue(pushRod4HOutput);
    }

    public MutableLiveData<Integer> getPushRod4LOutput() {
        return pushRod4LOutput;
    }

    public void setPushRod4LOutput(int pushRod4LOutput) {
        this.pushRod4LOutput.postValue(pushRod4LOutput);
    }


}
