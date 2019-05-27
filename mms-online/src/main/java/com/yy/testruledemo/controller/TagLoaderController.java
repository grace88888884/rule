//package com.yy.testruledemo.controller;
//
//import com.yy.testruleonline.bo.RuleBo;
//import com.yy.testruleonline.bo.TagBo;
//import com.yy.testruleonline.rule.RuleManager;
//import com.yy.testruleonline.utils.Constants;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/loader")
//public class TagLoaderController {
//    RuleManager ruleManager;
//
//    @ResponseBody
//    @RequestMapping("/getParamName")
//    public List<String> getParamName() {
//        Map<String, RuleBo> ruleBoMap = ruleManager.getRuleBoList();
//        Collection<RuleBo> values = ruleBoMap.values();
//        List<TagBo> inputTagList = new ArrayList<>();
//        for (RuleBo ruleBo : values) {
//            inputTagList.addAll(ruleBo.getInputTagList());
//        }
//        List<String> tagNameList = inputTagList.stream().map(t -> t.getTag().getTagName()).collect(Collectors.toList());
//        return tagNameList;
//    }
//
//
//    @ResponseBody
//    @RequestMapping("/refreshRuleBoList")
//    public boolean refreshRuleBoList() {
//        Map<String, RuleBo> ruleBoMap = ruleManager.refreshRuleBoList();
//        Constants.ruleBoMap = ruleBoMap;
//        return true;
//    }
//
//
//    @ResponseBody
//    @RequestMapping("/test")
//    public Map<String, Object> test(@RequestParam Map<String, String> param) {
////        Map<String, Object> resultMap = ruleManager.executeRule(param);
////        return resultMap;
//    return null;
//    }
//
//
//
//
//}
