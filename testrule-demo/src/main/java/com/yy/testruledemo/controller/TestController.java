package com.yy.testruledemo.controller;

import com.yy.testruleonline.bo.RuleBo;
import com.yy.testruleonline.rule.RuleManager;
import com.yy.testruleonline.utils.Constants;
import com.yy.testruleonline.utils.SpringBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/rule")
public class TestController {
    @Autowired
    RuleManager ruleManager;

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
        Map<String, Object> resultMap = ruleManager.executeRule(param);
        return resultMap;
    }

    
    @ResponseBody
    @RequestMapping("/testMerGroup")
    public Map<String, Object> testMerGroup(@RequestParam Map<String,String> param) {
        Map<String, Object> resultMap = ruleManager.executeRule(param);
        return resultMap;
    }

}
