package com.yy.testruleonline.entity;

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
@TableName("action_detail")
public class ActionDetail extends Model<ActionDetail> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String desc;

    @TableField("action_type")
    private ActionType actionType;

    @TableField("function_name")
    private String functionName;

    @TableField("param_classify_id")
    private Integer paramClassifyId;

    @TableField("param_classify_value")
    private Integer paramClassifyValue;

    @TableField("threshold_value")
    private String thresholdValue;

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
    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
    public Integer getParamClassifyId() {
        return paramClassifyId;
    }

    public void setParamClassifyId(Integer paramClassifyId) {
        this.paramClassifyId = paramClassifyId;
    }
    public Integer getParamClassifyValue() {
        return paramClassifyValue;
    }

    public void setParamClassifyValue(Integer paramClassifyValue) {
        this.paramClassifyValue = paramClassifyValue;
    }
    public String getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(String thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ActionDetail{" +
        "id=" + id +
        ", name=" + name +
        ", desc=" + desc +
        ", actionType=" + actionType +
        ", functionName=" + functionName +
        ", paramClassifyId=" + paramClassifyId +
        ", paramClassifyValue=" + paramClassifyValue +
        ", thresholdValue=" + thresholdValue +
        "}";
    }
}
