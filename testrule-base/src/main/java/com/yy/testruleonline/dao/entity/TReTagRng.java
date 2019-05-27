package com.yy.testruleonline.dao.entity;

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
@TableName("t_re_tag_rng")
public class TReTagRng extends Model<TReTagRng> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("tag_rng_name")
    private String tagRngName;
    @TableField("tag_rng_desc")
    private String tagRngDesc;

    @TableField("tag_name")
    private String tagName;

    @TableField("tag_value")
    private String tagValue;

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTagRngName() {
        return tagRngName;
    }

    public void setTagRngName(String tagRngName) {
        this.tagRngName = tagRngName;
    }
    public String getTagRngDesc() {
        return tagRngDesc;
    }

    public void setTagRngDesc(String tagRngDesc) {
        this.tagRngDesc = tagRngDesc;
    }
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TagRange{" +
        "id=" + id +
        ", tagRngName=" + tagRngName +
        ", tagRngDesc=" + tagRngDesc +
        ", tagName=" + tagName +
        "}";
    }
}
