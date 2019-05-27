package com.yy.testruleonline.bo;

import com.yy.testruleonline.dao.entity.TReCondGrp;

import java.util.ArrayList;
import java.util.List;

public class CondGrpBo {
    private List<CondBo> condBoList = new ArrayList<>();
    private List<CondGrpBo> condGrpBoList = new ArrayList<>();
    private List<ActnBo> actnBoList = new ArrayList<>();
    private List<ActnBo> elseActnBoList = new ArrayList<>();
    private TReCondGrp condGrp;

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

    public List<CondGrpBo> getCondGrpBoList() {
        return condGrpBoList;
    }

    public void setCondGrpBoList(List<CondGrpBo> condGrpBoList) {
        this.condGrpBoList = condGrpBoList;
    }

    public List<CondBo> getCondBoList() {
        return condBoList;
    }

    public void setCondBoList(List<CondBo> condBoList) {
        this.condBoList = condBoList;
    }

    public TReCondGrp getCondGrp() {
        return condGrp;
    }

    public void setCondGrp(TReCondGrp condGrp) {
        this.condGrp = condGrp;
    }
}
