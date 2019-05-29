package com.yy.testruledemo;

import com.yy.testrule.common.data.MmsContextInput;
import com.yy.testrule.common.data.MmsContextOutput;
import com.yy.testrule.common.enums.MmsDeclineReason;
import com.yy.testruleonline.rule.context.RuleContext;

import java.util.ArrayList;
import java.util.List;

public class MmsContext implements RuleContext<MmsContextInput> {
    
    MmsTagBo mmsTagBo = new MmsTagBo();
    
    List<MmsDeclineReason> declineReasonList = new ArrayList<>();
    MmsContextInput mmsContextInput ;
    MmsContextOutput mmsContextOutput ;

    public List<MmsDeclineReason> getDeclineReasonList() {
        return declineReasonList;
    }

    public MmsTagBo getMmsTagBo() {
        return mmsTagBo;
    }

    public void setMmsTagBo(MmsTagBo mmsTagBo) {
        this.mmsTagBo = mmsTagBo;
    }

    @Override
    public void dealWithContextInput(MmsContextInput mmsContextInput){
        this.mmsContextInput = mmsContextInput;
        mmsTagBo.setMerNo( mmsContextInput.getMerNo());
        mmsTagBo.setMmsType(mmsContextInput.getMmsType()); 
    }

    public void addDeclineReason(MmsDeclineReason declineReason) {
        declineReasonList.add(declineReason);
    }
   

 
    
    public MmsContextInput getMmsContextInput() {
        return mmsContextInput;
    }

    public void setMmsContextInput(MmsContextInput mmsContextInput) {
        this.mmsContextInput = mmsContextInput;
    }

    public MmsContextOutput getMmsContextOutput() {
        return mmsContextOutput;
    }

    public void setMmsContextOutput(MmsContextOutput mmsContextOutput) {
        this.mmsContextOutput = mmsContextOutput;
    }
}
