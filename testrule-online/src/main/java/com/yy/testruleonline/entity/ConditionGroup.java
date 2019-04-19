package com.yy.testruleonline.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yy.testruleonline.enums.RelationType;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-04-17
 */
@TableName("condition_group")
public class ConditionGroup extends Model<ConditionGroup> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String desc;

    @TableField("condition_relation")
    private RelationType conditionRelation;

    @TableField("root_condition_group_name")
    private RelationType rootConditionGroupName;

    @TableField("condition_detail_name_list")
    private String conditionDetailNameList;

    @TableField("child_condition_group_name_list")
    private String childConditionGroupNameList;

    @TableField(exist = false)
    private Set<String> conditionDetailNameSet;

    @TableField(exist = false)
    private Set<String> childConditionGroupNameSet;


    public RelationType getRootConditionGroupName() {
        return rootConditionGroupName;
    }

    public void setRootConditionGroupName(RelationType rootConditionGroupName) {
        this.rootConditionGroupName = rootConditionGroupName;
    }

    public Set<String> getConditionDetailNameSet() {
        return conditionDetailNameSet;
    }

    public Set<String> getChildConditionGroupNameSet() {
        return childConditionGroupNameSet;
    }

    public void setConditionDetailNameSet(Set<String> conditionDetailNameSet) {
        this.conditionDetailNameSet = conditionDetailNameSet;
    }

    public void setChildConditionGroupNameSet(Set<String> childConditionGroupNameSet) {
        this.childConditionGroupNameSet = childConditionGroupNameSet;
    }

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
    public RelationType getConditionRelation() {
        return conditionRelation;
    }

    public void setConditionRelation(RelationType conditionRelation) {
        this.conditionRelation = conditionRelation;
    }
    public String getConditionDetailNameList() {
        return conditionDetailNameList;
    }

    public void setConditionDetailNameList(String conditionDetailNameList) {
        this.conditionDetailNameList = conditionDetailNameList;
    }

    public String getChildConditionGroupNameList() {
        return childConditionGroupNameList;
    }

    public void setChildConditionGroupNameList(String childConditionGroupNameList) {
        this.childConditionGroupNameList = childConditionGroupNameList;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ConditionGroup{" +
        "id=" + id +
        ", name=" + name +
        ", desc=" + desc +
        ", conditionRelation=" + conditionRelation +
        ", conditionDetailNameList=" + conditionDetailNameList +
        ", childConditionGroupNameList=" + childConditionGroupNameList +
        "}";
    }
}
