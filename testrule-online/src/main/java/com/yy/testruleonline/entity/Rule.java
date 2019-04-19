package com.yy.testruleonline.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-04-17
 */
public class Rule extends Model<Rule> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String desc;

    @TableField("condition_group_name")
    private String conditionGroupName;

    @TableField("action_name")
    private String actionName;

    @TableField("else_action_name")
    private String elseActionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getConditionGroupName() {
        return conditionGroupName;
    }

    public void setConditionGroupName(String conditionGroupName) {
        this.conditionGroupName = conditionGroupName;
    }
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    public String getElseActionName() {
        return elseActionName;
    }

    public void setElseActionName(String elseActionName) {
        this.elseActionName = elseActionName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Rule{" +
        "id=" + id +
        ", name=" + name +
        ", desc=" + desc +
        ", conditionGroupName=" + conditionGroupName +
        ", actionName=" + actionName +
        ", elseActionName=" + elseActionName +
        "}";
    }
}
