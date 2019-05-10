package com.yy.testruledemo.controller;


import com.yy.testruleonline.entity.TReTag;
import com.yy.testruleonline.entity.TReTagRng;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.service.impl.TagRangeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    TagRangeServiceImpl tagRangeService;

    @ResponseBody
    @RequestMapping("/add")
    public void addParamClassify(TReTag TReTag, List<TReTagRng> params) {
        tagRangeService.addTag(TReTag, params);

    }

    @ResponseBody
    @RequestMapping("/addTest")
    public boolean addParamClassifyTest(String key) {
        TReTag TReTag = new TReTag();
        TReTag.setTagName(key + "MerType");
        TReTag.setTagDesc(key + "商户分类");
        TReTag.setTagType(TagType.ENUM);

        List<TReTagRng> TReTagRngs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TReTagRng param = new TReTagRng();
            param.setTagRngName(key + "mer" + i);
            param.setTagRngDesc(key + "商户" + i);
            TReTagRngs.add(param);
        }
        tagRangeService.addTag(TReTag, TReTagRngs);
        return false;
    }

}
