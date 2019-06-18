package com.yy.testruleonline.bo;

import com.yy.testruleonline.dao.entity.TReCond;
import com.yy.testruleonline.enums.CondType;
import com.yy.testruleonline.rule.function.action.IForceAction;

import java.util.ArrayList;
import java.util.List;

public class CondBo {
    private TReCond cond;
    private TagBo leftTagBo;
    private TagBo rightTagBo;
    private CondType condType;
    private List<IForceAction> forceActionList = new ArrayList<>();

    public List<IForceAction> getForceActionList() {
        return forceActionList;
    }

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

    public void setForceActionList(List<IForceAction> forceActionList) {
        this.forceActionList = forceActionList;
    }
}
