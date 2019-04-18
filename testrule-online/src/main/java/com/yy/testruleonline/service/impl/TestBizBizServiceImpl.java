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
//        List<Integer> conditionGroupIdList = new ArrayList<>();
//        Set<Integer> actionIdList = new HashSet<>();
//        Set<Integer> elseActionIdList = new HashSet<>();
//        ruleList.forEach(r -> {
//            Integer conditionGroupId = r.getConditionGroupId();
//            Integer actionId = r.getActionId();
//            Integer elseActionId = r.getElseActionId();
//            conditionGroupIdList.add(conditionGroupId);
//            actionIdList.add(actionId);
//            Optional.ofNullable(elseActionId).ifPresent(t -> elseActionIdList.add(t));
//        });
//
//        actionIdList.addAll(elseActionIdList);
//        List<ConditionGroup> conditionGroups = conditionGroupService.selectBatchIds(conditionGroupIdList);
//        Map<Integer, ConditionGroup> conditionGroupMap = conditionGroups.stream().collect(Collectors.toMap(ConditionGroup::getId, t -> t));
//
//        Set<Integer> conditionDetailIdSet = new HashSet<>();
//        conditionGroups.forEach(t -> {
//            String conditionDetailIdListStr = t.getConditionDetailIdList();
//            if (Strings.isNotEmpty(conditionDetailIdListStr)) {
//                String[] conditionDetailIds = conditionDetailIdListStr.split("|");
//                for (int i = 0; i < conditionDetailIds.length; i++) {
//                    if(!"|".equals(conditionDetailIds[i])){
//                        conditionDetailIdSet.add(Integer.parseInt(conditionDetailIds[i]));
//                    }
//                }
//                t.setConditionDetailIdSet(conditionDetailIdSet);
//            }
//        });
//        List<ConditionDetail> conditionDetails = conditionDetailService.selectBatchIds(conditionDetailIdSet);
//        Map<Integer, ConditionDetail> conditionDetailMap = conditionDetails.stream().collect(Collectors.toMap(ConditionDetail::getId, t -> t));
//        List<ActionDetail> actionDetails = actionDetailService.selectBatchIds(actionIdList);
//        Map<Integer, ActionDetail> actionDetailMap = actionDetails.stream().collect(Collectors.toMap(ActionDetail::getId, t -> t));
//
//        List<Integer> tagIdList = conditionDetails.stream().map(g -> g.getTagId()).collect(Collectors.toList());
//        List<Integer> tagIdList2 = actionDetails.stream().map(g -> g.getTagId()).collect(Collectors.toList());
//        tagIdList.addAll(tagIdList2);
//        List<Tag> tags = paramClassifyService.selectBatchIds(tagIdList);
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
//            ConditionGroup conditionGroup = conditionGroupMap.get(r.getConditionGroupId());
//            ActionDetail actionDetail = actionDetailMap.get(r.getActionId());
//            ActionDetail elseActionId = actionDetailMap.get(r.getElseActionId());
//            RuleBo ruleBo = new RuleBo();
//            ConditionGroupBo conditionGroupBo = new ConditionGroupBo();
//            conditionGroupBo.setConditionGroup(conditionGroup);
//            
//            conditionGroup.getConditionDetailIdSet().forEach(t -> {
//                ConditionDetail conditionDetail = conditionDetailMap.get(t);
//                Tag tag = tagMap.get(conditionDetail.getTagId());
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
//            Optional.ofNullable(tagMap.get(actionDetail.getTagId()))
//                    .ifPresent(t -> actionDetailBo.setTag(tagMap.get(actionDetail.getTagId())));
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
