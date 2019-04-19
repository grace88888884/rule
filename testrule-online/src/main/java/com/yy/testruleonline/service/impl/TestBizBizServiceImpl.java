//package com.yy.testruleonline.service.impl;
//
//import com.yy.testruleonline.bo.ActionDetailBo;
//import com.yy.testruleonline.bo.ConditionDetailBo;
//import com.yy.testruleonline.bo.ConditionGroupBo;
//import com.yy.testruleonline.bo.RuleBo;
//import com.yy.testruleonline.entity.*;
//import com.yy.testruleonline.service.*;
//import org.apache.logging.log4j.util.Strings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * <p>
// * 服务实现类
// * </p>
// *
// * @author Mht
// * @since 2019-04-17
// */
//@Service
//public class TestBizBizServiceImpl implements ITestBizService {
//    @Autowired
//    public IConditionGroupService conditionGroupService;
//    @Autowired
//    public IConditionDetailService conditionDetailService;
//    @Autowired
//    public IRuleService ruleService;
//    @Autowired
//    public IActionDetailService actionDetailService;
//    @Autowired
//    public ITagRangeService tagRangeService;
//
//    @Autowired
//    public ITagService paramClassifyService;
//
//
//    public Map<Integer,RuleBo> getRuleBoList() {
//        List<Rule> ruleList = ruleService.selectList(null);
//        List<Integer> conditionGroupNameList = new ArrayList<>();
//        Set<Integer> actionNameList = new HashSet<>();
//        Set<Integer> elseActionNameList = new HashSet<>();
//        ruleList.forEach(r -> {
//            Integer conditionGroupName = r.getConditionGroupName();
//            Integer actionName = r.getActionName();
//            Integer elseActionName = r.getElseActionName();
//            conditionGroupNameList.add(conditionGroupName);
//            actionNameList.add(actionName);
//            Optional.ofNullable(elseActionName).ifPresent(t -> elseActionNameList.add(t));
//        });
//
//        actionNameList.addAll(elseActionNameList);
//        List<ConditionGroup> conditionGroups = conditionGroupService.selectBatchIds(conditionGroupNameList);
//        Map<Integer, ConditionGroup> conditionGroupMap = conditionGroups.stream().collect(Collectors.toMap(ConditionGroup::getId, t -> t));
//
//        Set<Integer> conditionDetailNameSet = new HashSet<>();
//        conditionGroups.forEach(t -> {
//            String conditionDetailNameListStr = t.getConditionDetailIdList();
//            if (Strings.isNotEmpty(conditionDetailNameListStr)) {
//                String[] conditionDetailIds = conditionDetailNameListStr.split("|");
//                for (int i = 0; i < conditionDetailIds.length; i++) {
//                    if(!"|".equals(conditionDetailIds[i])){
//                        conditionDetailNameSet.add(Integer.parseInt(conditionDetailIds[i]));
//                    }
//                }
//                t.setConditionDetailIdSet(conditionDetailNameSet);
//            }
//        });
//        List<ConditionDetail> conditionDetails = conditionDetailService.selectBatchIds(conditionDetailNameSet);
//        Map<Integer, ConditionDetail> conditionDetailMap = conditionDetails.stream().collect(Collectors.toMap(ConditionDetail::getId, t -> t));
//        List<ActionDetail> actionDetails = actionDetailService.selectBatchIds(actionNameList);
//        Map<Integer, ActionDetail> actionDetailMap = actionDetails.stream().collect(Collectors.toMap(ActionDetail::getId, t -> t));
//
//        List<Integer> tagNameList = conditionDetails.stream().map(g -> g.getTagName()).collect(Collectors.toList());
//        List<Integer> tagNameList2 = actionDetails.stream().map(g -> g.getTagName()).collect(Collectors.toList());
//        tagNameList.addAll(tagNameList2);
//        List<Tag> tags = paramClassifyService.selectBatchIds(tagNameList);
//        Map<Integer, Tag> tagMap = tags.stream().collect(Collectors.toMap(Tag::getId, t -> t));
//
//
//        List<Integer> tagValueList = conditionDetails.stream().map(g -> g.getTagValue()).collect(Collectors.toList());
//        List<Integer> tagValueList2 = actionDetails.stream().map(g -> g.getTagValue()).collect(Collectors.toList());
//        tagValueList.addAll(tagValueList2);
//        List<TagRange> tagRanges = tagRangeService.selectBatchIds(tagValueList);
//        Map<Integer, TagRange> paramMap = params.stream().collect(Collectors.toMap(TagRange::getId, t -> t));
//
//
//        Map<Integer,RuleBo> ruleBoMap = new HashMap();
//        ruleList.forEach(r -> {
//            ConditionGroup conditionGroup = conditionGroupMap.get(r.getConditionGroupName());
//            ActionDetail actionDetail = actionDetailMap.get(r.getActionName());
//            ActionDetail elseActionName = actionDetailMap.get(r.getElseActionName());
//            RuleBo ruleBo = new RuleBo();
//            ConditionGroupBo conditionGroupBo = new ConditionGroupBo();
//            conditionGroupBo.setConditionGroup(conditionGroup);
//            
//            conditionGroup.getConditionDetailNameSet().forEach(t -> {
//                ConditionDetail conditionDetail = conditionDetailMap.get(t);
//                Tag tag = tagMap.get(conditionDetail.getTagName());
//                ConditionDetailBo conditionDetailBo = new ConditionDetailBo();
//                conditionDetailBo.setTag(tag);
//                if (conditionDetail.getTagValue() != null) {
//                    TagRange param = paramMap.get(conditionDetail.getTagValue());
//                    conditionDetailBo.setTagRange(param);
//                }
//                conditionDetailBo.setConditionDetail(conditionDetail);
//                conditionGroupBo.getConditionDetailBoList().add(conditionDetailBo);
//
//            });
//            ActionDetailBo actionDetailBo = new ActionDetailBo();
//            actionDetailBo.setActionDetail(actionDetail);
//            Optional.ofNullable(tagMap.get(actionDetail.getTagName()))
//                    .ifPresent(t -> actionDetailBo.setTag(tagMap.get(actionDetail.getTagName())));
//
//            Optional.ofNullable(actionDetail.getTagValue())
//                    .ifPresent(t -> actionDetailBo.setTagRange(paramMap.get(actionDetail.getTagValue())));
//
//
//            ruleBo.setConditionGroupBo(conditionGroupBo);
//            ruleBo.setActionDetailBo(actionDetailBo);
////            ruleBo.setElseActionDetailBo(actionDetailBo);//todo
//            ruleBoMap.put(r.getId(),ruleBo);
//        });
//
//        return ruleBoMap;
//    }
//
//
//}
