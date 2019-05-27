package com.yy.testruleonline.bo;

import com.yy.testruleonline.dao.entity.TReTag;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.rule.annotation.RuleTag;

public class TagBo {

    private TReTag tag;
    private TagType tagType;
    RuleTag ruleTag;

    public RuleTag getRuleTag() {
        return ruleTag;
    }

    public void setRuleTag(RuleTag ruleTag) {
        this.ruleTag = ruleTag;
    }

    public TReTag getTag() {
        return tag;
    }

    public void setTag(TReTag tag) {
        this.tag = tag;
    }


    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType = tagType;
    }
}
