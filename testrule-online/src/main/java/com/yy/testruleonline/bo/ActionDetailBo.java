package com.yy.testruleonline.bo;

import com.yy.testruleonline.entity.ActionDetail;
import com.yy.testruleonline.entity.ConditionGroup;
import com.yy.testruleonline.entity.TagRange;
import com.yy.testruleonline.entity.Tag;

public class ActionDetailBo {
    private ActionDetail actionDetail;
    private Tag tag;
    private TagRange tagRange;

    public ActionDetail getActionDetail() {
        return actionDetail;
    }

    public void setActionDetail(ActionDetail actionDetail) {
        this.actionDetail = actionDetail;
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
