package com.yy.testruleonline.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.yy.testruleonline.enums.TagType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yy
 * @since 2019-04-18
 */
public class Tag extends Model<Tag> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String desc;

    private TagType type;

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
    public TagType getType() {
        return type;
    }

    public void setType(TagType type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Tag{" +
        "id=" + id +
        ", name=" + name +
        ", desc=" + desc +
        ", type=" + type +
        "}";
    }
}
