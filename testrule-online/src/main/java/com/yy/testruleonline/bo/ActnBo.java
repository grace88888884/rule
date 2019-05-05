package com.yy.testruleonline.bo;

import com.yy.testruleonline.entity.TReActn;
import com.yy.testruleonline.entity.TReTagRng;
import com.yy.testruleonline.entity.TReTag;

public class ActnBo {
    private TReActn actn;
    private TReTag tag;
    private TReTagRng tagRng;

    public TReActn getActn() {
        return actn;
    }

    public void setTReActn(TReActn action) {
        this.actn = action;
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
