package com.yy.testruleonline.dao.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.yy.testruleonline.enums.CondType;
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
@TableName("t_re_cond")
public class TReCond extends Model<TReCond> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("cond_name")
    private String condName;
    @TableField("cond_desc")
    private String condDesc;

    @TableField("tag_name")
    private String tagName;

    @TableField("result_tag_name")
    private String resultTagName;

    private OperationType operation;

    @TableField("thr_value")
    private BigDecimal thrValue;

    @TableField("tag_rng_name")
    private String tagRngName;
    @TableField("cond_type")
    private CondType condType;
    @TableField("decline_reason")
    private String declineReason;

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }

    public CondType getCondType() {
        return condType;
    }

    public void setCondType(CondType condType) {
        this.condType = condType;
    }

    public String getResultTagName() {
        return resultTagName;
    }

    public void setResultTagName(String resultTagName) {
        this.resultTagName = resultTagName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCondName() {
        return condName;
    }

    public void setCondName(String condName) {
        this.condName = condName;
    }
    public String getCondDesc() {
        return condDesc;
    }

    public void setCondDesc(String condDesc) {
        this.condDesc = condDesc;
    }
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
    public BigDecimal getThrValue() {
        return thrValue;
    }

    public void setThrValue(BigDecimal thrValue) {
        this.thrValue = thrValue;
    }
    public String getTagRngName() {
        return tagRngName;
    }

    public void setTagRngName(String tagRngName) {
        this.tagRngName = tagRngName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ConditionDetail{" +
        "id=" + id +
        ", cond_name=" + condName +
        ", cond_desc=" + condDesc +
        ", tagName=" + tagName +
        ", operation=" + operation +
        ", thrValue=" + thrValue +
        ", tagRngName=" + tagRngName +
        "}";
    }
}
