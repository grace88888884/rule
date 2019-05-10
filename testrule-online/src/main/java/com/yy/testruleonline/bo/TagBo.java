package com.yy.testruleonline.bo;

import com.yy.testruleonline.entity.TReFun;
import com.yy.testruleonline.entity.TReTag;
import com.yy.testruleonline.enums.TagType;

public class TagBo {

    private TReTag tag;
    private TReFun fun;
    private TagType tagType;

    public TReTag getTag() {
        return tag;
    }

    public void setTag(TReTag tag) {
        this.tag = tag;
    }

    public TReFun getFun() {
        return fun;
    }

    public void setFun(TReFun fun) {
        this.fun = fun;
    }

    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType = tagType;
    }
}
