package com.yy.testruleonline.bo;

import com.yy.testruleonline.entity.ConditionDetail;
import com.yy.testruleonline.entity.ConditionGroup;
import com.yy.testruleonline.entity.TagRange;
import com.yy.testruleonline.entity.Tag;
import com.yy.testruleonline.entity.Tag;
import com.yy.testruleonline.entity.TagRange;

public class ConditionDetailBo {
    private ConditionDetail conditionDetail;
    private Tag tag;
    private TagRange tagRange;

    public ConditionDetail getConditionDetail() {
        return conditionDetail;
    }

    public void setConditionDetail(ConditionDetail conditionDetail) {
        this.conditionDetail = conditionDetail;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public TagRange getTagRange() {
        return tagRange;
    }

    public void setTagRange(TagRange tagRange) {
        this.tagRange = tagRange;
    }
}
