package com.yy.testruledemo.vo;

import com.yy.testrule.common.enums.MmsType;
import com.yy.testruleonline.enums.TagType;

import java.lang.reflect.Field;
import java.util.List;

public class TagVo {
    String tagName;
    TagTypeVo tagType;
    String tagDesc;
    List<TagRangeVo> tagRange;


    public List<TagRangeVo> getTagRange() {
        return tagRange;
    }

    public void setTagRange(List<TagRangeVo> tagRange) {
        this.tagRange = tagRange;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public TagTypeVo getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        TagTypeVo tagTypeVo = new TagTypeVo();
        tagTypeVo.setDesc(tagType.getDesc());
        tagTypeVo.setName(tagType.name());
        this.tagType =tagTypeVo;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }

   
}
