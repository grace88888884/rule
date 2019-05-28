package com.yy.testrule.common.data;

import com.yy.testrule.common.enums.MmsDeclineReason;

import java.util.List;

public  class MmsContextOutput {
        private String output1;
        private String output2;
        private String merType;
        private List<MmsDeclineReason> declineReasons;

    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
    }

    public List<MmsDeclineReason> getDeclineReasons() {
        return declineReasons;
    }

    public void setDeclineReasons(List<MmsDeclineReason> declineReasons) {
        this.declineReasons = declineReasons;
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
    }