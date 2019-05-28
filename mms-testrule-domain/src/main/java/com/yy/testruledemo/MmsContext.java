package com.yy.testruledemo;

import com.yy.testrule.common.data.MmsContextInput;
import com.yy.testrule.common.data.MmsContextOutput;
import com.yy.testrule.common.enums.MmsDeclineReason;
import com.yy.testrule.common.enums.MmsType;
import com.yy.testruledemo.tag.DayAmtPolicyLmt1Tag;
import com.yy.testruledemo.tag.DayAmtStatLmt1Tag;
import com.yy.testruledemo.tag.MmsTypeTag;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.rule.annotation.RuleTag;
import com.yy.testruleonline.rule.context.RuleContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MmsContext implements RuleContext<MmsContextInput> {
    @RuleTag(tagName = "dayAmtPolicyLmt", tagType = TagType.CACULATION, tagDesc = "日金额限额", tagFun = DayAmtPolicyLmt1Tag.class)
    BigDecimal dayAmtPolicyLmt;

    @RuleTag(tagName = "dayAmtStatLmt",tagType = TagType.CACULATION,tagDesc = "日金额统计", tagFun = DayAmtStatLmt1Tag.class)
    BigDecimal dayAmtStatLmt;

    @RuleTag(tagName = "merNo", tagType = TagType.STRING, tagDesc = "商户号码")
    String merNo;

    @RuleTag(tagName = "mmsType", tagType = TagType.CACULATION, tagDesc = "商户类型",tagFun = MmsTypeTag.class, tagRange = MmsType.class)
    String mmsType;
    
    
    List<MmsDeclineReason> declineReasonList = new ArrayList<>();
    MmsContextInput mmsContextInput ;
    MmsContextOutput mmsContextOutput ;

    public List<MmsDeclineReason> getDeclineReasonList() {
        return declineReasonList;
    }

    public String getMmsType() {
        return mmsType;
    }

    public void setMmsType(String mmsType) {
        this.mmsType = mmsType;
    }

    @Override
    public void dealWithContextInput(MmsContextInput mmsContextInput){
        this.mmsContextInput = mmsContextInput;
        this.merNo = mmsContextInput.getMerNo();
        this.mmsType = mmsContextInput.getMmsType();
    }

    public void addDeclineReason(MmsDeclineReason declineReason) {
        declineReasonList.add(declineReason);
    }
   

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }


    public BigDecimal getDayAmtPolicyLmt() {
        return dayAmtPolicyLmt;
    }

    public void setDayAmtPolicyLmt(BigDecimal dayAmtPolicyLmt) {
        this.dayAmtPolicyLmt = dayAmtPolicyLmt;
    }

    public BigDecimal getDayAmtStatLmt() {
        return dayAmtStatLmt;
    }

    public void setDayAmtStatLmt(BigDecimal dayAmtStatLmt) {
        this.dayAmtStatLmt = dayAmtStatLmt;
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
