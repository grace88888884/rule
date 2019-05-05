package com.yy.testruleonline.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yy.testruleonline.enums.RelationType;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-04-17
 */
@TableName("t_re_cond_grp")
public class TReCondGrp extends Model<TReCondGrp> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
  
    @TableField("cond_grp_name")
    private String condGrpName;

    @TableField("cond_grp_desc")
    private String condGrpDesc;

    @TableField("cond_relt")
    private RelationType condRelt;

    @TableField("rt_cond_grp_name")
    private RelationType rtCondGrpName;

    @TableField("cond_name_list")
    private String condNameList;

    @TableField("child_cond_grp_name_list")
    private String childCondGrpNameList;

    @TableField("actn_name_list")
    private String actnNameList;

    @TableField("else_actn_name_list")
    private String elseActnNameList;

    @TableField(exist = false)
    private Set<String> condNameSet;

    @TableField(exist = false)
    private Set<String> childCondGrpNameSet;


    public RelationType getRtCondGrpName() {
        return rtCondGrpName;
    }

    public String getActnNameList() {
        return actnNameList;
    }

    public void setActnNameList(String actnNameList) {
        this.actnNameList = actnNameList;
    }

    public String getElseActnNameList() {
        return elseActnNameList;
    }

    public void setElseActnNameList(String elseActnNameList) {
        this.elseActnNameList = elseActnNameList;
    }

    public void setRtCondGrpName(RelationType rtCondGrpName) {
        this.rtCondGrpName = rtCondGrpName;
    }

    public Set<String> getCondNameSet() {
        return condNameSet;
    }

    public Set<String> getChildCondGrpNameSet() {
        return childCondGrpNameSet;
    }

    public void setCondNameSet(Set<String> condNameSet) {
        this.condNameSet = condNameSet;
    }

    public void setChildCondGrpNameSet(Set<String> childCondGrpNameSet) {
        this.childCondGrpNameSet = childCondGrpNameSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCondGrpName() {
        return condGrpName;
    }

    public void setCondGrpName(String condGrpName) {
        this.condGrpName = condGrpName;
    }
    public String getCondGrpDesc() {
        return condGrpDesc;
    }

    public void setCondGrpDesc(String condGrpDesc) {
        this.condGrpDesc = condGrpDesc;
    }
    public RelationType getCondRelt() {
        return condRelt;
    }

    public void setCondRelt(RelationType condRelt) {
        this.condRelt = condRelt;
    }
    public String getCondNameList() {
        return condNameList;
    }

    public void setCondNameList(String condNameList) {
        this.condNameList = condNameList;
    }

    public String getChildCondGrpNameList() {
        return childCondGrpNameList;
    }

    public void setChildCondGrpNameList(String childCondGrpNameList) {
        this.childCondGrpNameList = childCondGrpNameList;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ConditionGroup{" +
        "id=" + id +
        ", condGrpName=" + condGrpName +
        ", condGrpDesc=" + condGrpDesc +
        ", condRelt=" + condRelt +
        ", condNameList=" + condNameList +
        ", childCondGrpNameList=" + childCondGrpNameList +
        "}";
    }
}
