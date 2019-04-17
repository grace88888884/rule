package com.yy.testruleonline.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yy.testruleonline.enums.RelationType;

import java.io.Serializable;

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

    @TableField("condition_detail_id_list")
    private String conditionDetailIdList;

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
    public String getConditionDetailIdList() {
        return conditionDetailIdList;
    }

    public void setConditionDetailIdList(String conditionDetailIdList) {
        this.conditionDetailIdList = conditionDetailIdList;
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
        ", conditionDetailIdList=" + conditionDetailIdList +
        "}";
    }
}
