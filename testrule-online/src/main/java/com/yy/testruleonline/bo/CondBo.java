package com.yy.testruleonline.bo;

import com.yy.testruleonline.entity.TReCond;
import com.yy.testruleonline.entity.TReTagRng;
import com.yy.testruleonline.entity.TReTag;

public class CondBo {
    private TReCond cond;
    private TReTag tag;
    private TReTagRng tagRng;

    public TReCond getCond() {
        return cond;
    }

    public void setCond(TReCond cond) {
        this.cond = cond;
    }

    public TReTag getTag() {
        return tag;
    }

    public void setTag(TReTag tag) {
        this.tag = tag;
    }

    public TReTagRng getTagRng() {
        return tagRng;
    }

    public void setTagRng(TReTagRng tagRng) {
        this.tagRng = tagRng;
    }
}
