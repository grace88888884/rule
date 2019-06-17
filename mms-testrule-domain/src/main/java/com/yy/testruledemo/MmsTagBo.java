package com.yy.testruledemo;

import com.yy.testrule.common.enums.MmsType;
import com.yy.testruledemo.tag.DayAmtPolicyLmt1Tag;
import com.yy.testruledemo.tag.DayAmtStatLmt1Tag;
import com.yy.testruledemo.tag.MmsTypeTag;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.rule.annotation.RuleTag;
import com.yy.testruleonline.rule.annotation.RuleTagCollection;

import java.math.BigDecimal;

@RuleTagCollection
public class MmsTagBo {
    @RuleTag(tagName = "dayAmtPolicyLmt", tagType = TagType.CACULATION_NUM, tagDesc = "日金额限额", tagFun = DayAmtPolicyLmt1Tag.class)
    BigDecimal dayAmtPolicyLmt;

    @RuleTag(tagName = "dayAmtStatLmt", tagType = TagType.CACULATION_NUM, tagDesc = "日金额统计", tagFun = DayAmtStatLmt1Tag.class)
    BigDecimal dayAmtStatLmt;

    @RuleTag(tagName = "merNo", tagType = TagType.STRING, tagDesc = "商户号码")
    String merNo;

    @RuleTag(tagName = "mmsType", tagType = TagType.CACULATION_ENUM, tagDesc = "商户类型", tagFun = MmsTypeTag.class, tagRange = MmsType.class)
    String mmsType;


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


    public String getMmsType() {
        return mmsType;
    }

    public void setMmsType(String mmsType) {
        this.mmsType = mmsType;
    }
}
