package com.yy.testruleonline.web;


import com.yy.testruleonline.entity.Tag;
import com.yy.testruleonline.entity.TagRange;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.service.ITagRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    ITagRangeService tagRangeService;

    @ResponseBody
    @RequestMapping("/add")
    public void addParamClassify(Tag tag, List<TagRange> params) {
        tagRangeService.addTag(tag, params);

    }

    @ResponseBody
    @RequestMapping("/addTest")
    public boolean addParamClassifyTest(String key) {
        Tag tag = new Tag();
        tag.setName(key + "MerType");
        tag.setDesc(key + "商户分类");
        tag.setType(TagType.ENUM);

        List<TagRange> tagRanges = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TagRange param = new TagRange();
            param.setName(key + "mer" + i);
            param.setDesc(key + "商户" + i);
            tagRanges.add(param);
        }
        tagRangeService.addTag(tag, tagRanges);
        return false;
    }

}
