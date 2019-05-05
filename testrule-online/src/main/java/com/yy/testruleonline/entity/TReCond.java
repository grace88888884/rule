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

    private OperationType operation;

    @TableField("thr_value")
    private BigDecimal thrValue;

    @TableField("tag_rng_name")
    private String tagRngName;

    @TableField("fun_name")
    private String funName;

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
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
