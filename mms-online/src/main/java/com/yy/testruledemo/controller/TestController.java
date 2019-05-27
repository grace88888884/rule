package com.yy.testruledemo.controller;

import com.yy.testrule.common.data.MmsContextInput;
import com.yy.testruleonline.rule.api.RuleGeneralRequest;
import com.yy.testruledemo.MmsRuleManager;
import com.yy.testruleonline.bo.RuleBo;
import com.yy.testruleonline.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/rule")
public class TestController {
    @Autowired
    MmsRuleManager ruleManager;

    @ResponseBody
    @RequestMapping("/init")
    public boolean init() {
        Map<String, RuleBo> ruleBoMap = ruleManager.getRuleBoList();
        Constants.ruleBoMap = ruleBoMap;
        return false;
    }


    @ResponseBody
    @RequestMapping("/refreshRuleBoList")
    public boolean refreshRuleBoList() {
        Map<String, RuleBo> ruleBoMap = ruleManager.refreshRuleBoList();
        Constants.ruleBoMap = ruleBoMap;
        return true;
    }


    
    @ResponseBody
    @RequestMapping("/test")
    public Map<String, Object> test(@RequestParam Map<String,String> param) {
//        Map<String, Object> resultMap = ruleManager.executeRule(param);
//        return resultMap;
        return null;
    }

    
    @ResponseBody
    @RequestMapping("/testMerGroup")
    public Map<String, Object> testMerGroup(@RequestBody RuleGeneralRequest<MmsContextInput> input) {
        Map<String, Object> resultMap = ruleManager.executeRule(input.getRequest(), input.getRuleName());
        return resultMap;
    }

}
