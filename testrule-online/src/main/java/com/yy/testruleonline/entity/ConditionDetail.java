package com.yy.testruleonline.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
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

    @TableField("operation_id")
    private Integer operationId;

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
    public Integer getParamClassifyId() {
        return paramClassifyId;
    }

    public void setParamClassifyId(Integer paramClassifyId) {
        this.paramClassifyId = paramClassifyId;
    }
    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
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
        return "ConditionDetail{" +
        "id=" + id +
        ", name=" + name +
        ", desc=" + desc +
        ", paramClassifyId=" + paramClassifyId +
        ", operationId=" + operationId +
        ", thresholdValue=" + thresholdValue +
        "}";
    }
}
