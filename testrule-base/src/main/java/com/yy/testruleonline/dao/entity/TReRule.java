package com.yy.testruleonline.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-04-17
 */
@TableName("t_re_rule")
public class TReRule extends Model<TReRule> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("rule_name")
    private String ruleName;
    
    @TableField("rule_desc")
    private String ruleDesc;

//    @TableField("cond_grp_name")
//    private String condGrpName;

    @TableField("rule_operation")
    private String ruleOperation;

    @TableField("actn_name_list")
    private String actnNameList;

    @TableField("else_actn_name_list")
    private String elseActnNameList;

    public String getRuleOperation() {
        return ruleOperation;
    }

    public void setRuleOperation(String ruleOperation) {
        this.ruleOperation = ruleOperation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }
//    public String getCondGrpName() {
//        return condGrpName;
//    }

    public String getActnNameList() {
        return actnNameList;
    }

//    public void setCondGrpName(String condGrpName) {
//        this.condGrpName = condGrpName;
//    }

    public void setActnNameList(String actnNameList) {
        this.actnNameList = actnNameList;
    }
    public String getElseActnNameList() {
        return elseActnNameList;
    }

    public void setElseActnNameList(String elseActnNameList) {
        this.elseActnNameList = elseActnNameList;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Rule{" +
        "id=" + id +
        ", ruleName=" + ruleName +
        ", ruleDesc=" + ruleDesc +
        ", ruleOperation=" + ruleOperation +
        ", actnNameList=" + actnNameList +
        ", elseActnNameList=" + elseActnNameList +
        "}";
    }
}
