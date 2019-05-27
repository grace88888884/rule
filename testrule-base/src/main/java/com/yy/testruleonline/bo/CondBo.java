package com.yy.testruleonline.bo;

import com.yy.testruleonline.dao.entity.TReCond;
import com.yy.testruleonline.dao.entity.TReTag;
import com.yy.testruleonline.dao.entity.TReTagRng;
import com.yy.testruleonline.enums.CondType;

public class CondBo {
    private TReCond cond;
    private TagBo leftTagBo;
    private TagBo rightTagBo;
    private CondType condType;

    public CondType getCondType() {
        return condType;
    }

    public void setCondType(CondType condType) {
        this.condType = condType;
    }

    public TagBo getLeftTagBo() {
        return leftTagBo;
    }

    public void setLeftTagBo(TagBo leftTagBo) {
        this.leftTagBo = leftTagBo;
    }

    public TagBo getRightTagBo() {
        return rightTagBo;
    }

    public void setRightTagBo(TagBo rightTagBo) {
        this.rightTagBo = rightTagBo;
    }

    public TReCond getCond() {
        return cond;
    }

    public void setCond(TReCond cond) {
        this.cond = cond;
    }
   
}
