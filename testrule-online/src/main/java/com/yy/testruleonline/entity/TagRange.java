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
 * @author yy
 * @since 2019-04-18
 */
@TableName("tag_range")
public class TagRange extends Model<TagRange> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String desc;

    @TableField("tag_id")
    private Integer tagId;

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
    public Integer getClassifyId() {
        return tagId;
    }

    public void setClassifyId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TagRange{" +
        "id=" + id +
        ", name=" + name +
        ", desc=" + desc +
        ", tagId=" + tagId +
        "}";
    }
}
