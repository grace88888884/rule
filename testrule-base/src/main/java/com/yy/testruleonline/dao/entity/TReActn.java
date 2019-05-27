package com.yy.testruleonline.dao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yy.testruleonline.enums.ActionType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yy
 * @since 2019-04-17
 */
@TableName("t_re_actn")
public class TReActn extends Model<TReActn> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("actn_name")
    private String actnName;
    
    @TableField("actn_desc")
    private String actnDesc;

    @TableField("actn_type")
    private ActionType actnType;

    @TableField("fun_name")
    private String funName;

    @TableField("tag_name")
    private String tagName;

    @TableField("tag_rng_name")
    private String tagRngName;

    @TableField("thr_value")
    private String thrValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getActnName() {
        return actnName;
    }

    public void setActnName(String actnName) {
        this.actnName = actnName;
    }
    public String getActnDesc() {
        return actnDesc;
    }

    public void setActnDesc(String actnDesc) {
        this.actnDesc = actnDesc;
    }
    public ActionType getActnType() {
        return actnType;
    }

    public void setActnType(ActionType actnType) {
        this.actnType = actnType;
    }
    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public String getTagRngName() {
        return tagRngName;
    }

    public void setTagRngName(String tagRngName) {
        this.tagRngName = tagRngName;
    }
    public String getThrValue() {
        return thrValue;
    }

    public void setThrValue(String thrValue) {
        this.thrValue = thrValue;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ActionDetail{" +
        "id=" + id +
        ", actnName=" + actnName +
        ", actnDesc=" + actnDesc +
        ", actnType=" + actnType +
        ", funName=" + funName +
        ", tagName=" + tagName +
        ", tagRngName=" + tagRngName +
        ", thrValue=" + thrValue +
        "}";
    }
}
