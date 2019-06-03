package com.yy.testruleonline.dao.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yy.testruleonline.enums.RuleFlowType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yy
 * @since 2019-05-29
 */
@TableName("t_re_rule_flow")
public class TReRuleFlow extends Model<TReRuleFlow> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableField("rule_flow_name")
    private String ruleFlowName;

    @TableField("rule_flow_desc")
    private String ruleFlowDesc;

    @TableField("rule_name_list")
    private String ruleNameList;

    @TableField("rule_rlt_type")
    private RuleFlowType ruleRltType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRuleFlowName() {
        return ruleFlowName;
    }

    public void setRuleFlowName(String ruleFlowName) {
        this.ruleFlowName = ruleFlowName;
    }
    public String getRuleFlowDesc() {
        return ruleFlowDesc;
    }

    public void setRuleFlowDesc(String ruleFlowDesc) {
        this.ruleFlowDesc = ruleFlowDesc;
    }
    public String getRuleNameList() {
        return ruleNameList;
    }

    public void setRuleNameList(String ruleNameList) {
        this.ruleNameList = ruleNameList;
    }
    public RuleFlowType getRuleRltType() {
        return ruleRltType;
    }

    public void setRuleRltType(RuleFlowType ruleRltType) {
        this.ruleRltType = ruleRltType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TReRuleFlow{" +
        "id=" + id +
        ", ruleFlowName=" + ruleFlowName +
        ", ruleFlowDesc=" + ruleFlowDesc +
        ", ruleNameList=" + ruleNameList +
        ", ruleRltType=" + ruleRltType +
        "}";
    }
}
