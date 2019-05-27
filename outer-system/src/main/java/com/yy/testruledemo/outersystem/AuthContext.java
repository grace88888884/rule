package com.yy.testruledemo.outersystem;

import com.yy.testrule.common.enums.MmsDeclineReason;

import java.util.List;

public class AuthContext {
    
    String merNo;
    String merType;
    String output1;
    String output2;
    List<MmsDeclineReason> declineReasons;

    public List<MmsDeclineReason> getDeclineReasons() {
        return declineReasons;
    }

    public String getOutput1() {
        return output1;
    }

    public void setOutput1(String output1) {
        this.output1 = output1;
    }

    public String getOutput2() {
        return output2;
    }

    public void setOutput2(String output2) {
        this.output2 = output2;
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
    }

    public void setDeclineReasons(List<MmsDeclineReason> declineReasons) {
        this.declineReasons =declineReasons;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
