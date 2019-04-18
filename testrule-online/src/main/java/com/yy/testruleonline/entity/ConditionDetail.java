package com.yy.testruleonline.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yy.testruleonline.enums.OperationType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yy
 * @since 2019-04-17
 */
@TableName("condition_detail")
public class ConditionDetail extends Model<ConditionDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String desc;

    @TableField("param_classify_id")
    private Integer paramClassifyId;

    private OperationType operation;

    @TableField("threshold_value")
    private String thresholdValue;

    @TableField("param_classify_value")
    private Integer paramClassifyValue;

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
    public Integer getParamClassifyId() {
        return paramClassifyId;
    }

    public void setParamClassifyId(Integer paramClassifyId) {
        this.paramClassifyId = paramClassifyId;
    }
    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
    public String getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(String thresholdValue) {
        this.thresholdValue = thresholdValue;
    }
    public Integer getParamClassifyValue() {
        return paramClassifyValue;
    }

    public void setParamClassifyValue(Integer paramClassifyValue) {
        this.paramClassifyValue = paramClassifyValue;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ConditionDetail{" +
        "id=" + id +
        ", name=" + name +
        ", desc=" + desc +
        ", paramClassifyId=" + paramClassifyId +
        ", operation=" + operation +
        ", thresholdValue=" + thresholdValue +
        ", paramClassifyValue=" + paramClassifyValue +
        "}";
    }
}
