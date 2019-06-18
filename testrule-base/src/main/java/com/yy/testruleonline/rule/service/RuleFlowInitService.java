package com.yy.testruleonline.rule.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.testruleonline.bo.*;
import com.yy.testruleonline.dao.entity.TReActn;
import com.yy.testruleonline.dao.entity.TReCond;
import com.yy.testruleonline.dao.entity.TReRule;
import com.yy.testruleonline.dao.entity.TReRuleFlow;
import com.yy.testruleonline.dao.mapper.TReActnMapper;
import com.yy.testruleonline.dao.mapper.TReCondMapper;
import com.yy.testruleonline.dao.mapper.TReRuleFlowMapper;
import com.yy.testruleonline.dao.mapper.TReRuleMapper;
import com.yy.testruleonline.dao.service.impl.RuleServiceImpl;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.rule.TagManager;
import com.yy.testruleonline.rule.annotation.RuleTag;
import com.yy.testruleonline.rule.function.action.IForceAction;
import com.yy.testruleonline.utils.SpringBeanFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public  class RuleFlowInitService<T> {
//    @Autowired
//    public TReCondGrpMapper condGrpMapper;

    @Autowired
    public TReCondMapper condMapper;
    @Autowired
    public TReRuleMapper ruleMapper; 

  
    @Autowired
    public RuleServiceImpl ruleService;
    @Autowired
    public TReRuleFlowMapper ruleFlowMapper;

    @Autowired
    public TReActnMapper actnMapper;


    Map<String, RuleBo> ruleBoMap = new HashMap<>();

    Map<String, TReCond> conditionMap = new HashMap<>();
    Map<String, CondBo> condBoMap = new HashMap<>();
    Map<String, TReActn> actionMap = new HashMap<>();
    Map<String, RuleTag> tagAnnotationMap = new HashMap<>();

    @Autowired
    private TagManager<T> tagManager;

    public Map<String, RuleFlowBo> refreshRuleFlowBoList(Class context) {
        List<TReRuleFlow> tReRuleFlows = ruleFlowMapper.selectList(null);
        Set<String> ruleNameSet = new HashSet<>();
        tReRuleFlows.forEach(r -> {
            String ruleNameListStr = r.getRuleNameList();
            if (!Strings.isBlank(ruleNameListStr)) {
                String[] ruleNameList = ruleNameListStr.split("\\|");
                for (int i = 0; i < ruleNameList.length; i++) {
                    ruleNameSet.add(ruleNameList[i]);
                }

            }
        });
        Map<String, RuleFlowBo> ruleFlowBoMap = new HashMap<>();
        Map<String, RuleBo> ruleBoMap = refreshRuleBoList(context, ruleNameSet);
        tReRuleFlows.forEach(r -> {
            RuleFlowBo ruleFlowBo = new RuleFlowBo();
            ruleFlowBo.setRuleFlow(r);
            String ruleNameListStr = r.getRuleNameList();
            if (!Strings.isBlank(ruleNameListStr)) {
                String[] ruleNameList = ruleNameListStr.split("\\|");
                for (int i = 0; i < ruleNameList.length; i++) {
                    ruleFlowBo.getRuleList().add(ruleBoMap.get(ruleNameList[i]));
                }
            }
            ruleFlowBoMap.put(r.getRuleFlowName(), ruleFlowBo);
        });


        return ruleFlowBoMap;
    }


    /**
     * 刷新ruleList对象
     *
     * @return
     */
    public Map<String, RuleBo> refreshRuleBoList(Class context, Set<String> ruleNameSet) {
        conditionMap = new HashMap<>();
        actionMap = new HashMap<>();
        tagAnnotationMap = new HashMap<>();

        Map<String, RuleBo> ruleBoMap = new HashMap<>();
        Set<String> ruleOperationSet = new HashSet<>();
        Set<String> conditionNameAllSet = new HashSet<>();
        Set<String> actionNameSet = new HashSet<>();
        Set<String> elseActionNameSet = new HashSet<>();

        QueryWrapper<TReRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .in(TReRule::getRuleName, ruleNameSet);
        List<TReRule> ruleList = ruleMapper.selectList(queryWrapper);
        ruleList.forEach(r -> {
            RuleBo ruleBo = ruleBoMap.get(r.getRuleName());
            if (ruleBo != null) {
                return;
            }
            String ruleOperation = r.getRuleOperation();
            ruleOperationSet.add(ruleOperation);
            String actionNameListStr = r.getActnNameList();
            if (!Strings.isBlank(actionNameListStr)) {
                String[] actionNameStrs = actionNameListStr.split("\\|");
                for (int i = 0; i < actionNameStrs.length; i++) {
                    actionNameSet.add(actionNameStrs[i]);
                }
            }

            String elseActionNameListStr = r.getElseActnNameList();
            if (!Strings.isBlank(elseActionNameListStr)) {
                String[] elseNameStrs = elseActionNameListStr.split("\\|");
                for (int i = 0; i < elseNameStrs.length; i++) {
                    elseActionNameSet.add(elseNameStrs[i]);
                }
            }
        });

        actionNameSet.addAll(elseActionNameSet);

        List<TReActn> actions = new ArrayList<>();
        if (actionNameSet.size() > 0) {
            actions = actnMapper.selectByActionDetailNames(actionNameSet);
        }
        actionMap = actions.stream().collect(Collectors.toMap(TReActn::getActnName, t -> t));
        
        tagAnnotationMap = tagManager.scanTagList(context);

        for (String ruleOperation : ruleOperationSet) {
            initConditionNameSet(ruleOperation, conditionNameAllSet);
        }

        List<TReCond> conditions = new ArrayList<>();
        if (conditionNameAllSet.size() > 0) {
            conditions = condMapper.selectByConditionNames(conditionNameAllSet);
        }

        conditionMap = conditions.stream().collect(Collectors.toMap(TReCond::getCondName, t -> t));
        conditionMap.forEach((key,value) ->{
            List<IForceAction> forceActionList = new ArrayList<>();
            CondBo condBo = initCondBo(value, forceActionList);
            condBo.setForceActionList(forceActionList);
            condBoMap.put(key,condBo);
        });
        
       

        ruleList.forEach(r -> {
            List<ActnBo> actnBoList = new ArrayList<>();
            if (r.getActnNameList() != null) {
                String[] actionNames = r.getActnNameList().split("\\|");
                for (int i = 0; i < actionNames.length; i++) {
                    Optional.ofNullable(actionMap.get(actionNames[i])).ifPresent(t -> {
                        ActnBo actnBo = getActionDetailBo(tagAnnotationMap, t);
                        actnBoList.add(actnBo);
                    });
                }
            }

            List<ActnBo> elseActnBoList = new ArrayList<>();
            if (r.getElseActnNameList() != null) {
                String[] actionNames = r.getElseActnNameList().split("\\|");
                for (int i = 0; i < actionNames.length; i++) {
                    Optional.ofNullable(actionMap.get(actionNames[i])).ifPresent(t -> {
                        ActnBo actnBo = getActionDetailBo(tagAnnotationMap, t);
                        elseActnBoList.add(actnBo);
                    });
                }
            }
            RuleBo ruleBo = new RuleBo();
            ruleBo.setRule(r);
            ruleBo.setActnBoList(actnBoList);
            ruleBo.setElseActnBoList(elseActnBoList);
            String ruleOperation = r.getRuleOperation();
            Set<String> conditionNameSet = new HashSet<>();
            initConditionNameSet(ruleOperation, conditionNameSet);
            List<IForceAction> ruleForceActionList = new ArrayList<>();
            for(String condName :conditionNameSet){
                ruleForceActionList.addAll(condBoMap.get(condName).getForceActionList());
            }
            ruleBo.setForceActionList(ruleForceActionList);
            ruleBo.setCondBoMap(condBoMap);
            ruleBoMap.put(r.getRuleName(), ruleBo);
        });

        return ruleBoMap;
    }

    public static void initConditionNameSet(String ruleOperation, Collection<String> conditionNameSet) {
        String[] split1 = ruleOperation.split("\\|\\|");
        for (String str1 : split1) {
            if (!"\\|\\|".equals(str1)) {
                String[] split2 = str1.split("&&");
                for (String str2 : split2) {
                    if (!"&&".equals(str2)) {
                        String sReplace1 = str2.replaceAll("[(]", "");
                        String sReplace2 = sReplace1.replaceAll("[)]", "");
                        String  s= sReplace2.replaceAll("[!]", "");
                        conditionNameSet.add(s);
                    }
                }
            }
        }
    }

    private ActnBo getActionDetailBo(Map<String, RuleTag> tagAnnotationMap, TReActn t) {
        ActnBo actnBo = new ActnBo();
        actnBo.setTReActn(t);
        Optional.ofNullable(t.getTagName())
                .ifPresent(tagName -> {
                    if(tagAnnotationMap.get(tagName)!=null){
                        actnBo.setRuleTag(tagAnnotationMap.get(tagName));
                    }else {
                        System.out.println("没有找到对应标签");
                    }
                }); 
        Optional.ofNullable(t.getFunName())
                .ifPresent(tagName -> {
                    actnBo.setFunName(t.getFunName());

                });
        return actnBo;
    }

//    private CondGrpBo initConditionGroupBo(  String conditionGroupName, List<TagBo> inputTagList, List<IForceAction> forceActionList) {
//        TReCondGrp conditionGroup = conditionGroupMap.get(conditionGroupName);
//        CondGrpBo condGrpBo = new CondGrpBo();
//        if (conditionGroup != null) {
//            condGrpBo.setCondGrp(conditionGroup);
//            conditionGroup.getCondNameSet().forEach(t -> {
//                TReCond condition = conditionMap.get(t);
//                CondBo condBo = new CondBo();
//                TagBo leftBo = initTagBo(condition.getTagName(), forceActionList);
//                if(leftBo!=null) {
//                    condBo.setLeftTagBo(leftBo);
//                }else {
//                    System.out.println("没有找到对应标签");
//                }
//                condBo.setCondType(condition.getCondType());
//                TagBo rightBo = initTagBo(condition.getResultTagName(), forceActionList);
//                if(rightBo!=null) {
//                    condBo.setRightTagBo(rightBo);
//                }else {
//                    System.out.println("没有找到对应标签");
//                }
//                inputTagList.add(condBo.getLeftTagBo());
//                inputTagList.add(condBo.getRightTagBo());
//
//                condBo.setCond(condition);
//                condGrpBo.getCondBoList().add(condBo);
//
//            });
//            if (conditionGroup.getChildCondGrpNameSet() != null) {
//                conditionGroup.getChildCondGrpNameSet().forEach(t -> {
//                    CondGrpBo childCondtionGroupBo = initConditionGroupBo( t, inputTagList, forceActionList);
//                    condGrpBo.getCondGrpBoList().add(childCondtionGroupBo);
//                });
//            }
//
//        }
//
//        return condGrpBo;
//    }

    private CondBo initCondBo(TReCond condition,List<IForceAction> forceActionList) {
        CondBo condBo = new CondBo();
                TagBo leftBo = initTagBo(condition.getTagName(), forceActionList);
                if(leftBo!=null) {
                    condBo.setLeftTagBo(leftBo);
                }else {
                    System.out.println("没有找到对应标签");
                }
                condBo.setCondType(condition.getCondType());
                TagBo rightBo = initTagBo(condition.getResultTagName(), forceActionList);
                if(rightBo!=null) {
                    condBo.setRightTagBo(rightBo);
                }else {
                    System.out.println("没有找到对应标签");
                }

                condBo.setCond(condition);
                return condBo;
    }

    private TagBo initTagBo(String tagName,  List<IForceAction> forceActionList) {
        RuleTag ruleTag = tagAnnotationMap.get(tagName);
        if (ruleTag == null) {
            System.out.println("没有找到对应标签");
            return null;
        }
        Object forceAction = SpringBeanFactory.getBean(ruleTag.tagFun());
        if (forceAction != null && forceAction instanceof IForceAction) {
            forceActionList.add((IForceAction) forceAction);
        }
        TagBo tagBo = new TagBo();
        tagBo.setRuleTag(ruleTag);
        TagType tagType = ruleTag.tagType();
        tagBo.setTagType(tagType);
        return tagBo;
    }

}
