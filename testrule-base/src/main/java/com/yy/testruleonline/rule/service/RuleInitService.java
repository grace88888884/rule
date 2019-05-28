package com.yy.testruleonline.rule.service;

import com.yy.testruleonline.bo.*;
import com.yy.testruleonline.dao.entity.TReActn;
import com.yy.testruleonline.dao.entity.TReCond;
import com.yy.testruleonline.dao.entity.TReCondGrp;
import com.yy.testruleonline.dao.entity.TReRule;
import com.yy.testruleonline.dao.mapper.*;
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
public  class RuleInitService<T> {
    @Autowired
    public TReCondGrpMapper condGrpMapper;

    @Autowired
    public TReCondMapper condMapper;
  
    @Autowired
    public RuleServiceImpl ruleService;

    @Autowired
    public TReActnMapper actnMapper;


    Map<String, RuleBo> ruleBoMap = new HashMap<>();

    Map<String, TReCond> conditionMap = new HashMap<>();
    Map<String, TReCondGrp> conditionGroupMap = new HashMap<>();
    Map<String, TReActn> actionMap = new HashMap<>();
    Map<String, RuleTag> tagAnnotationMap = new HashMap<>();

    @Autowired
    private TagManager<T> tagManager;

    /**
     * 刷新ruleList对象
     *
     * @return
     */
    public Map<String, RuleBo> refreshRuleBoList(Class<T> context) {
        conditionMap = new HashMap<>();
        conditionGroupMap = new HashMap<>();
        actionMap = new HashMap<>();
        tagAnnotationMap = new HashMap<>();
        
        List<TReRule> ruleList = ruleService.selectList(null);
        Set<String> conditionGroupNameSet = new HashSet<>();
        Set<String> conditionNameAllSet = new HashSet<>();
        Set<String> actionNameSet = new HashSet<>();
        Set<String> elseActionNameSet = new HashSet<>();

        ruleList.forEach(r -> {
            String conditionGroupName = r.getCondGrpName();
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
            conditionGroupNameSet.add(conditionGroupName);
        });

        actionNameSet.addAll(elseActionNameSet);

        List<TReCondGrp> conditionGroups = new ArrayList<>();
        if (conditionGroupNameSet.size() > 0) {
            conditionGroups = condGrpMapper.selectByConditionGroupNames(conditionGroupNameSet);
        }

        List<String> conditionRootNameList = conditionGroups.stream().map(g -> g.getCondGrpName()).collect(Collectors.toList());
        if (conditionRootNameList.size() > 0) {
            conditionGroups = condGrpMapper.selectByRootConditionGroupName(conditionRootNameList);
        }
        conditionGroups.forEach(t -> {
            String childConditionGroupNameList = t.getChildCondGrpNameList();
            if (Strings.isNotEmpty(childConditionGroupNameList)) {
                String[] childConditionGroupName = childConditionGroupNameList.split("\\|");
                for (int i = 0; i < childConditionGroupName.length; i++) {
                    conditionGroupNameSet.add(childConditionGroupName[i]);
                }
            }
        });
        if (conditionGroupNameSet.size() > 0) {
            conditionGroups = condGrpMapper.selectByConditionGroupNames(conditionGroupNameSet);
        }
        conditionGroupMap = conditionGroups.stream().collect(Collectors.toMap(TReCondGrp::getCondGrpName, t -> t));
        conditionGroups.forEach(t -> {
            String conditionDetailNameListStr = t.getCondNameList();
            Set<String> conditionNameSet = new HashSet<>();
            if (Strings.isNotEmpty(conditionDetailNameListStr)) {
                String[] condNames = conditionDetailNameListStr.split("\\|");
                for (int i = 0; i < condNames.length; i++) {
                    conditionNameSet.add(condNames[i]);
                    conditionNameAllSet.add(condNames[i]);
                }
                t.setCondNameSet(conditionNameSet);
                Set<String> childConditionGroupNameSet = new HashSet<>();
                String childConditionGroupNameList = t.getChildCondGrpNameList();
                if (Strings.isNotEmpty(childConditionGroupNameList)) {
                    String[] childConditionGroupName = childConditionGroupNameList.split("\\|");
                    for (int i = 0; i < childConditionGroupName.length; i++) {
                        childConditionGroupNameSet.add(childConditionGroupName[i]);
                    }
                    t.setChildCondGrpNameSet(childConditionGroupNameSet);
                }
            }
        });
        List<TReCond> conditions = new ArrayList<>();
        if (conditionNameAllSet.size() > 0) {
            conditions = condMapper.selectByConditionNames(conditionNameAllSet);
        }

        conditionMap = conditions.stream().collect(Collectors.toMap(TReCond::getCondName, t -> t));
        List<TReActn> actions = new ArrayList<>();
        if (actionNameSet.size() > 0) {
            actions = actnMapper.selectByActionDetailNames(actionNameSet);
        }
        actionMap = actions.stream().collect(Collectors.toMap(TReActn::getActnName, t -> t));
        tagAnnotationMap = tagManager.scanTagList(context);

        ruleList.forEach(r -> {
            List<IForceAction> forceActionList = new ArrayList<>();
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
            List<TagBo> inputTagList = new ArrayList<>();
            RuleBo ruleBo = new RuleBo();
            ruleBo.setRule(r);
            CondGrpBo condGrpBo = initConditionGroupBo( r.getCondGrpName(), inputTagList, forceActionList);
            ruleBo.setCondGrpBo(condGrpBo);
            ruleBo.setActnBoList(actnBoList);
            ruleBo.setElseActnBoList(elseActnBoList);
            ruleBo.setForceActionList(forceActionList);
            ruleBoMap.put(r.getRuleName(), ruleBo);
        });

        return ruleBoMap;
    }

    private ActnBo getActionDetailBo(Map<String, RuleTag> tagAnnotationMap, TReActn t) {
        ActnBo actnBo = new ActnBo();
        actnBo.setTReActn(t);
        Optional.ofNullable(t.getTagName())
                .ifPresent(tagName -> {
                    actnBo.setRuleTag(tagAnnotationMap.get(tagName));

                }); 
        Optional.ofNullable(t.getFunName())
                .ifPresent(tagName -> {
                    actnBo.setFunName(t.getFunName());

                });
        return actnBo;
    }

    private CondGrpBo initConditionGroupBo(  String conditionGroupName, List<TagBo> inputTagList, List<IForceAction> forceActionList) {
        TReCondGrp conditionGroup = conditionGroupMap.get(conditionGroupName);
        CondGrpBo condGrpBo = new CondGrpBo();
        if (conditionGroup != null) {
            condGrpBo.setCondGrp(conditionGroup);
            conditionGroup.getCondNameSet().forEach(t -> {
                TReCond condition = conditionMap.get(t);
                CondBo condBo = new CondBo();
                condBo.setLeftTagBo(initTagBo(condition.getTagName(), forceActionList));
                condBo.setCondType(condition.getCondType());
                condBo.setRightTagBo(initTagBo(condition.getResultTagName(),  forceActionList));
                inputTagList.add(condBo.getLeftTagBo());
                inputTagList.add(condBo.getRightTagBo());

                condBo.setCond(condition);
                condGrpBo.getCondBoList().add(condBo);

            });
            if (conditionGroup.getChildCondGrpNameSet() != null) {
                conditionGroup.getChildCondGrpNameSet().forEach(t -> {
                    CondGrpBo childCondtionGroupBo = initConditionGroupBo( t, inputTagList, forceActionList);
                    condGrpBo.getCondGrpBoList().add(childCondtionGroupBo);
                });
            }

        }

        return condGrpBo;
    }

    private TagBo initTagBo(String tagName,  List<IForceAction> forceActionList) {
        RuleTag ruleTag = tagAnnotationMap.get(tagName);
        if (ruleTag == null) {
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
