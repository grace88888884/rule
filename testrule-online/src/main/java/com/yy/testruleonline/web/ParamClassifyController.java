package com.yy.testruleonline.web;


import com.yy.testruleonline.entity.Param;
import com.yy.testruleonline.entity.ParamClassify;
import com.yy.testruleonline.enums.ParamClassifyType;
import com.yy.testruleonline.service.IParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mht
 * @since 2019-04-17
 */
@Controller
@RequestMapping("/paramClassify")
public class ParamClassifyController {
    @Autowired
    IParamService paramService;

    @ResponseBody
    @RequestMapping("/add")
    public void addParamClassify(ParamClassify paramClassify, List<Param> params) {
        paramService.addParamClassify(paramClassify, params);

    }

    @ResponseBody
    @RequestMapping("/addTest")
    public boolean addParamClassifyTest(String key) {
        ParamClassify paramClassify = new ParamClassify();
        paramClassify.setName(key + "MerType");
        paramClassify.setDesc(key + "商户分类");
        paramClassify.setType(ParamClassifyType.ENUM);

        List<Param> params = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Param param = new Param();
            param.setName(key + "mer" + i);
            param.setDesc(key + "商户" + i);
            params.add(param);
        }
        paramService.addParamClassify(paramClassify, params);
        return false;
    }


}
