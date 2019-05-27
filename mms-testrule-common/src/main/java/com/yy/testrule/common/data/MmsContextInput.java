package com.yy.testrule.common.data;

import com.yy.testrule.common.enums.MmsType;

public  class MmsContextInput {
        private String merNo;

        private String mmsType;

        public String getMerNo() {
            return merNo;
        }

        public void setMerNo(String merNo) {
            this.merNo = merNo;
        }

        public String getMmsType() {
            return mmsType;
        }

        public void setMmsType(String mmsType) {
            this.mmsType = mmsType;
        }
    }