package com.yy.testruleonline.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yy.testruleonline.enums.OperationType;

import java.io.Serializable;
import java.math.BigDecimal;

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

    @TableField("tag_id")
    private Integer tagId;

    private OperationType operation;

    @TableField("threshold_value")
    private BigDecimal thresholdValue;

    @TableField("tag_value")
    private Integer tagValue;

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
    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
    public BigDecimal getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(BigDecimal thresholdValue) {
        this.thresholdValue = thresholdValue;
    }
    public Integer getTagValue() {
        return tagValue;
    }

    public void setTagValue(Integer tagValue) {
        this.tagValue = tagValue;
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
        ", tagId=" + tagId +
        ", operation=" + operation +
        ", thresholdValue=" + thresholdValue +
        ", tagValue=" + tagValue +
        "}";
    }
}
