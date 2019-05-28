package com.yy.testruledemo.controller;


import com.yy.testrule.common.enums.MmsType;
import com.yy.testruledemo.MmsContext;
import com.yy.testruledemo.vo.TagRangeVo;
import com.yy.testruledemo.vo.TagVo;
import com.yy.testruleonline.rule.TagManager;
import com.yy.testruleonline.rule.annotation.RuleTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yy
 * @since 2019-04-18
 */
@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagManager<MmsContext> tagManager;

    @ResponseBody
    @RequestMapping("/getTagList")
    public List<TagVo> getTagList() {
        Map<String, RuleTag> ruleTagMap = tagManager.scanTagList(MmsContext.class);
        List<TagVo> tagVoList = new ArrayList<>();
        Iterator<RuleTag> iterator = ruleTagMap.values().iterator();
        while (iterator.hasNext()) {
            RuleTag next = iterator.next();
            TagVo tagBo = new TagVo();
            tagBo.setTagDesc(next.tagDesc());
            tagBo.setTagName(next.tagName());
            tagBo.setTagType(next.tagType());
            if(!void.class.equals(next.tagRange())) {
                List<TagRangeVo> mmsTypeList = new ArrayList<>();
                Field[] fields = next.tagRange().getFields();
                for(Field field : fields){
                    String name = field.getName();
                    MmsType mmsType = MmsType.valueOf(name);
                    TagRangeVo tagRangeVo = new TagRangeVo();
                    tagRangeVo.setDesc(mmsType.getDesc());
                    tagRangeVo.setName(mmsType.name());
                    mmsTypeList.add(tagRangeVo);
                }
                tagBo.setTagRange(mmsTypeList);
            }
            tagBo.setTagType(next.tagType());
            tagVoList.add(tagBo);
        }
        return tagVoList;
    }


}
