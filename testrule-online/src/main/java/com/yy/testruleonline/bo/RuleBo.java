package com.yy.testruleonline.bo;


import java.util.List;

public class RuleBo {
    private List<ActnBo> actnBoList;
    private List<ActnBo> elseActnBoList;
    private CondGrpBo condGrpBo;

    public List<ActnBo> getActnBoList() {
        return actnBoList;
    }

    public void setActnBoList(List<ActnBo> actnBoList) {
        this.actnBoList = actnBoList;
    }

    public List<ActnBo> getElseActnBoList() {
        return elseActnBoList;
    }

    public void setElseActnBoList(List<ActnBo> elseActnBoList) {
        this.elseActnBoList = elseActnBoList;
    }

    public CondGrpBo getCondGrpBo() {
        return condGrpBo;
    }

    public void setCondGrpBo(CondGrpBo condGrpBo) {
        this.condGrpBo = condGrpBo;
    }

    
}
