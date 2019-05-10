package com.yy.testruleonline.bo;

import com.yy.testruleonline.entity.TReCond;
import com.yy.testruleonline.entity.TReTag;
import com.yy.testruleonline.entity.TReTagRng;

public class CondBo {
    private TReCond cond;
    private TagBo tagBo;

    private TagBo resultTagBo;

    public TagBo getTagBo() {
        return tagBo;
    }

    public void setTagBo(TagBo tagBo) {
        this.tagBo = tagBo;
    }

    public TagBo getResultTagBo() {
        return resultTagBo;
    }

    public void setResultTagBo(TagBo resultTagBo) {
        this.resultTagBo = resultTagBo;
    }

    public TReCond getCond() {
        return cond;
    }

    public void setCond(TReCond cond) {
        this.cond = cond;
    }

    private TReTag tag;
    private TReTagRng tagRng;

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
