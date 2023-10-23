package com.android.homework.bean;




import androidx.lifecycle.MutableLiveData;



/**
 * 输入诊断
 */

public class InputDiagnosticsBean {


    /** 0x0503第一个字节 **/
    // 1~2.负控信号输入1 00-有输入 01-无输入 10-输入故障 11-外部按键短路
    private MutableLiveData<Integer> negativeControlSignalInput1 = new MutableLiveData<>();
    // 3~4.负控信号输入2
    private MutableLiveData<Integer> negativeControlSignalInput2 = new MutableLiveData<>();
    // 5~6.负控信号输入3
    private MutableLiveData<Integer> negativeControlSignalInput3 = new MutableLiveData<>();
    // 7~8.负控信号输入4
    private MutableLiveData<Integer> negativeControlSignalInput4 = new MutableLiveData<>();
    /** 0x0503第二个字节 **/
    // 1~2.负控信号输入5
    private MutableLiveData<Integer> negativeControlSignalInput5 = new MutableLiveData<>();
    // 3~4.负控信号输入6
    private MutableLiveData<Integer> negativeControlSignalInput6 = new MutableLiveData<>();
    // 5~6.负控信号输入7
    private MutableLiveData<Integer> negativeControlSignalInput7 = new MutableLiveData<>();
    // 7~8.负控信号输入8
    private MutableLiveData<Integer> negativeControlSignalInput8 = new MutableLiveData<>();
    /** 0x0503第三个字节 **/
    // 1~2.负控信号输入9
    private MutableLiveData<Integer> negativeControlSignalInput9 = new MutableLiveData<>();
    // 3~4.负控信号输入10
    private MutableLiveData<Integer> negativeControlSignalInput10 = new MutableLiveData<>();
    // 5~6.负控信号输入11
    private MutableLiveData<Integer> negativeControlSignalInput11 = new MutableLiveData<>();
    // 7~8.负控信号输入12
    private MutableLiveData<Integer> negativeControlSignalInput12 = new MutableLiveData<>();
    /** 0x0503第四个字节 **/
    // 1~2.负控信号输入13
    private MutableLiveData<Integer> negativeControlSignalInput13 = new MutableLiveData<>();
    // 3~4.负控信号输入14
    private MutableLiveData<Integer> negativeControlSignalInput14 = new MutableLiveData<>();
    // 5~6.负控信号输入15
    private MutableLiveData<Integer> negativeControlSignalInput15 = new MutableLiveData<>();
    // 7~8.负控信号输入16
    private MutableLiveData<Integer> negativeControlSignalInput16 = new MutableLiveData<>();
    /** 0x0503第五个字节 **/
    // 1~2.负控信号输入17
    private MutableLiveData<Integer> negativeControlSignalInput17 = new MutableLiveData<>();
    // 3~4.负控信号输入18
    private MutableLiveData<Integer> negativeControlSignalInput18 = new MutableLiveData<>();
    // 5~6.负控信号输入19
    private MutableLiveData<Integer> negativeControlSignalInput19 = new MutableLiveData<>();
    // 7~8.负控信号输入20
    private MutableLiveData<Integer> negativeControlSignalInput20 = new MutableLiveData<>();
    /** 0x0503第六个字节 **/
    // 1~2.负控信号输入21
    private MutableLiveData<Integer> negativeControlSignalInput21 = new MutableLiveData<>();
    // 3~4.负控信号输入22
    private MutableLiveData<Integer> negativeControlSignalInput22 = new MutableLiveData<>();
    // 5~6.负控信号输入23
    private MutableLiveData<Integer> negativeControlSignalInput23 = new MutableLiveData<>();
    // 7~8.负控信号输入24
    private MutableLiveData<Integer> negativeControlSignalInput24 = new MutableLiveData<>();
    /** 0x0503第七个字节 **/
    // 1~2.负控信号输入25
    private MutableLiveData<Integer> negativeControlSignalInput25 = new MutableLiveData<>();
    // 3~4.负控信号输入26
    private MutableLiveData<Integer> negativeControlSignalInput26 = new MutableLiveData<>();
    // 5~6.负控信号输入27
    private MutableLiveData<Integer> negativeControlSignalInput27 = new MutableLiveData<>();
    // 7~8.负控信号输入28
    private MutableLiveData<Integer> negativeControlSignalInput28 = new MutableLiveData<>();
    /** 0x0503第八个字节 **/
    // 1~2.负控信号输入29
    private MutableLiveData<Integer> negativeControlSignalInput29 = new MutableLiveData<>();
    // 3~4.负控信号输入30
    private MutableLiveData<Integer> negativeControlSignalInput30 = new MutableLiveData<>();
    // 5~6.负控信号输入31
    private MutableLiveData<Integer> negativeControlSignalInput31 = new MutableLiveData<>();
    // 7~8.负控信号输入32
    private MutableLiveData<Integer> negativeControlSignalInput32 = new MutableLiveData<>();


    @Override
    public String toString() {
        return "InputDiagnosticsBean{" +
                "negativeControlSignalInput1=" + negativeControlSignalInput1 +
                ", negativeControlSignalInput2=" + negativeControlSignalInput2 +
                ", negativeControlSignalInput3=" + negativeControlSignalInput3 +
                ", negativeControlSignalInput4=" + negativeControlSignalInput4 +
                ", negativeControlSignalInput5=" + negativeControlSignalInput5 +
                ", negativeControlSignalInput6=" + negativeControlSignalInput6 +
                ", negativeControlSignalInput7=" + negativeControlSignalInput7 +
                ", negativeControlSignalInput8=" + negativeControlSignalInput8 +
                ", negativeControlSignalInput9=" + negativeControlSignalInput9 +
                ", negativeControlSignalInput10=" + negativeControlSignalInput10 +
                ", negativeControlSignalInput11=" + negativeControlSignalInput11 +
                ", negativeControlSignalInput12=" + negativeControlSignalInput12 +
                ", negativeControlSignalInput13=" + negativeControlSignalInput13 +
                ", negativeControlSignalInput14=" + negativeControlSignalInput14 +
                ", negativeControlSignalInput15=" + negativeControlSignalInput15 +
                ", negativeControlSignalInput16=" + negativeControlSignalInput16 +
                ", negativeControlSignalInput17=" + negativeControlSignalInput17 +
                ", negativeControlSignalInput18=" + negativeControlSignalInput18 +
                ", negativeControlSignalInput19=" + negativeControlSignalInput19 +
                ", negativeControlSignalInput20=" + negativeControlSignalInput20 +
                ", negativeControlSignalInput21=" + negativeControlSignalInput21 +
                ", negativeControlSignalInput22=" + negativeControlSignalInput22 +
                ", negativeControlSignalInput23=" + negativeControlSignalInput23 +
                ", negativeControlSignalInput24=" + negativeControlSignalInput24 +
                ", negativeControlSignalInput25=" + negativeControlSignalInput25 +
                ", negativeControlSignalInput26=" + negativeControlSignalInput26 +
                ", negativeControlSignalInput27=" + negativeControlSignalInput27 +
                ", negativeControlSignalInput28=" + negativeControlSignalInput28 +
                ", negativeControlSignalInput29=" + negativeControlSignalInput29 +
                ", negativeControlSignalInput30=" + negativeControlSignalInput30 +
                ", negativeControlSignalInput31=" + negativeControlSignalInput31 +
                ", negativeControlSignalInput32=" + negativeControlSignalInput32 +
                '}';
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput1() {
        return negativeControlSignalInput1;
    }

    public void setNegativeControlSignalInput1(int negativeControlSignalInput1) {
        this.negativeControlSignalInput1.postValue(negativeControlSignalInput1);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput2() {
        return negativeControlSignalInput2;
    }

    public void setNegativeControlSignalInput2(int negativeControlSignalInput2) {
        this.negativeControlSignalInput2.postValue(negativeControlSignalInput2);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput3() {
        return negativeControlSignalInput3;
    }

    public void setNegativeControlSignalInput3(int negativeControlSignalInput3) {
        this.negativeControlSignalInput3.postValue(negativeControlSignalInput3);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput4() {
        return negativeControlSignalInput4;
    }

    public void setNegativeControlSignalInput4(int negativeControlSignalInput4) {
        this.negativeControlSignalInput4.postValue(negativeControlSignalInput4);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput5() {
        return negativeControlSignalInput5;
    }

    public void setNegativeControlSignalInput5(int negativeControlSignalInput5) {
        this.negativeControlSignalInput5 .postValue(negativeControlSignalInput5);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput6() {
        return negativeControlSignalInput6;
    }

    public void setNegativeControlSignalInput6(int negativeControlSignalInput6) {
        this.negativeControlSignalInput6 .postValue(negativeControlSignalInput6);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput7() {
        return negativeControlSignalInput7;
    }

    public void setNegativeControlSignalInput7(int negativeControlSignalInput7) {
        this.negativeControlSignalInput7 .postValue(negativeControlSignalInput7);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput8() {
        return negativeControlSignalInput8;
    }

    public void setNegativeControlSignalInput8(int negativeControlSignalInput8) {
        this.negativeControlSignalInput8 .postValue(negativeControlSignalInput8);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput9() {
        return negativeControlSignalInput9;
    }

    public void setNegativeControlSignalInput9(int negativeControlSignalInput9) {
        this.negativeControlSignalInput9.postValue(negativeControlSignalInput9);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput10() {
        return negativeControlSignalInput10;
    }

    public void setNegativeControlSignalInput10(int negativeControlSignalInput10) {
        this.negativeControlSignalInput10 .postValue(negativeControlSignalInput10);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput11() {
        return negativeControlSignalInput11;
    }

    public void setNegativeControlSignalInput11(int negativeControlSignalInput11) {
        this.negativeControlSignalInput11 .postValue(negativeControlSignalInput11);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput12() {
        return negativeControlSignalInput12;
    }

    public void setNegativeControlSignalInput12(int negativeControlSignalInput12) {
        this.negativeControlSignalInput12 .postValue(negativeControlSignalInput12);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput13() {
        return negativeControlSignalInput13;
    }

    public void setNegativeControlSignalInput13(int negativeControlSignalInput13) {
        this.negativeControlSignalInput13 .postValue(negativeControlSignalInput13);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput14() {
        return negativeControlSignalInput14;
    }

    public void setNegativeControlSignalInput14(int negativeControlSignalInput14) {
        this.negativeControlSignalInput14 .postValue(negativeControlSignalInput14);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput15() {
        return negativeControlSignalInput15;
    }

    public void setNegativeControlSignalInput15(int negativeControlSignalInput15) {
        this.negativeControlSignalInput15.postValue(negativeControlSignalInput15);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput16() {
        return negativeControlSignalInput16;
    }

    public void setNegativeControlSignalInput16(int negativeControlSignalInput16) {
        this.negativeControlSignalInput16 .postValue(negativeControlSignalInput16);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput17() {
        return negativeControlSignalInput17;
    }

    public void setNegativeControlSignalInput17(int negativeControlSignalInput17) {
        this.negativeControlSignalInput17 .postValue(negativeControlSignalInput17);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput18() {
        return negativeControlSignalInput18;
    }

    public void setNegativeControlSignalInput18(int negativeControlSignalInput18) {
        this.negativeControlSignalInput18.postValue(negativeControlSignalInput18);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput19() {
        return negativeControlSignalInput19;
    }

    public void setNegativeControlSignalInput19(int negativeControlSignalInput19) {
        this.negativeControlSignalInput19.postValue(negativeControlSignalInput19);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput20() {
        return negativeControlSignalInput20;
    }

    public void setNegativeControlSignalInput20(int negativeControlSignalInput20) {
        this.negativeControlSignalInput20.postValue(negativeControlSignalInput20);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput21() {
        return negativeControlSignalInput21;
    }

    public void setNegativeControlSignalInput21(int negativeControlSignalInput21) {
        this.negativeControlSignalInput21.postValue(negativeControlSignalInput21);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput22() {
        return negativeControlSignalInput22;
    }

    public void setNegativeControlSignalInput22(int negativeControlSignalInput22) {
        this.negativeControlSignalInput22.postValue(negativeControlSignalInput22);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput23() {
        return negativeControlSignalInput23;
    }

    public void setNegativeControlSignalInput23(int negativeControlSignalInput23) {
        this.negativeControlSignalInput23.postValue(negativeControlSignalInput23);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput24() {
        return negativeControlSignalInput24;
    }

    public void setNegativeControlSignalInput24(int  negativeControlSignalInput24) {
        this.negativeControlSignalInput24.postValue(negativeControlSignalInput24);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput25() {
        return negativeControlSignalInput25;
    }

    public void setNegativeControlSignalInput25(int negativeControlSignalInput25) {
        this.negativeControlSignalInput25.postValue(negativeControlSignalInput25);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput26() {
        return negativeControlSignalInput26;
    }

    public void setNegativeControlSignalInput26(int negativeControlSignalInput26) {
        this.negativeControlSignalInput26 .postValue(negativeControlSignalInput26);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput27() {
        return negativeControlSignalInput27;
    }

    public void setNegativeControlSignalInput27(int negativeControlSignalInput27) {
        this.negativeControlSignalInput27.postValue(negativeControlSignalInput27);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput28() {
        return negativeControlSignalInput28;
    }

    public void setNegativeControlSignalInput28(int negativeControlSignalInput28) {
        this.negativeControlSignalInput28.postValue(negativeControlSignalInput28);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput29() {
        return negativeControlSignalInput29;
    }

    public void setNegativeControlSignalInput29(int negativeControlSignalInput29) {
        this.negativeControlSignalInput29 .postValue(negativeControlSignalInput29);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput30() {
        return negativeControlSignalInput30;
    }

    public void setNegativeControlSignalInput30(int negativeControlSignalInput30) {
        this.negativeControlSignalInput30.postValue(negativeControlSignalInput30);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput31() {
        return negativeControlSignalInput31;
    }

    public void setNegativeControlSignalInput31(int negativeControlSignalInput31) {
        this.negativeControlSignalInput31.postValue(negativeControlSignalInput31);
    }

    public MutableLiveData<Integer> getNegativeControlSignalInput32() {
        return negativeControlSignalInput32;
    }

    public void setNegativeControlSignalInput32(int negativeControlSignalInput32) {
        this.negativeControlSignalInput32.postValue(negativeControlSignalInput32);
    }


}
