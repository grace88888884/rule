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

    @TableField("condition_group_id")
    private Integer conditionGroupId;

    @TableField("action_id")
    private Integer actionId;

    @TableField("else_action_id")
    private Integer elseActionId;

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
    public Integer getConditionGroupId() {
        return conditionGroupId;
    }

    public void setConditionGroupId(Integer conditionGroupId) {
        this.conditionGroupId = conditionGroupId;
    }
    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }
    public Integer getElseActionId() {
        return elseActionId;
    }

    public void setElseActionId(Integer elseActionId) {
        this.elseActionId = elseActionId;
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
        ", conditionGroupId=" + conditionGroupId +
        ", actionId=" + actionId +
        ", elseActionId=" + elseActionId +
        "}";
    }
}
