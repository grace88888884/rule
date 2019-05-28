package com.yy.testruleonline.bo;

import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.rule.annotation.RuleTag;

public class TagBo {

    private TagType tagType;
    RuleTag ruleTag;

    public RuleTag getRuleTag() {
        return ruleTag;
    }

    public void setRuleTag(RuleTag ruleTag) {
        this.ruleTag = ruleTag;
    }



    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType = tagType;
    }
}
