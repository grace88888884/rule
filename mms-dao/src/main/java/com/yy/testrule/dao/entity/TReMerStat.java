package com.yy.testrule.dao.entity;

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
@TableName("t_re_mer_stat")
public class TReMerStat extends Model<TReMerStat> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("mer_no")
    private String merNo;

    @TableField("day_amt_stat")
    private BigDecimal dayAmtStat;

    @TableField("day_cnt_stat")
    private BigDecimal dayCntStat;

    @TableField("mer_desc")
    private String merDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }
    public BigDecimal getDayAmtStat() {
        return dayAmtStat;
    }

    public void setDayAmtStat(BigDecimal dayAmtStat) {
        this.dayAmtStat = dayAmtStat;
    }
    public BigDecimal getDayCntStat() {
        return dayCntStat;
    }

    public void setDayCntStat(BigDecimal dayCntStat) {
        this.dayCntStat = dayCntStat;
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
        return "TReMerStat{" +
        "id=" + id +
        ", merNo=" + merNo +
        ", dayAmtStat=" + dayAmtStat +
        ", dayCntStat=" + dayCntStat +
        ", merDesc=" + merDesc +
        "}";
    }
}
