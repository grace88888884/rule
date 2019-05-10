package com.yy.testruledemo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * @author yy
 * @since 2019-05-06
 */
@TableName("t_re_mer_policy")
public class TReMerPolicy extends Model<TReMerPolicy> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("mer_type")
    private String merType;

    @TableField("day_amt_lmt")
    private BigDecimal dayAmtLmt;

    @TableField("day_cnt_lmt")
    private BigDecimal dayCntLmt;

    @TableField("mer_desc")
    private String merDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
    }
    public BigDecimal getDayAmtLmt() {
        return dayAmtLmt;
    }

    public void setDayAmtLmt(BigDecimal dayAmtLmt) {
        this.dayAmtLmt = dayAmtLmt;
    }
    public BigDecimal getDayCntLmt() {
        return dayCntLmt;
    }

    public void setDayCntLmt(BigDecimal dayCntLmt) {
        this.dayCntLmt = dayCntLmt;
    }
    public String getMerDesc() {
        return merDesc;
    }

    public void setMerDesc(String merDesc) {
        this.merDesc = merDesc;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TReMerPolicy{" +
        "id=" + id +
        ", merType=" + merType +
        ", dayAmtLmt=" + dayAmtLmt +
        ", dayCntLmt=" + dayCntLmt +
        ", merDesc=" + merDesc +
        "}";
    }
}
