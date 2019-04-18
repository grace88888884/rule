//package com.yy.testruleonline.rule;
//
//import com.googlecode.aviator.AviatorEvaluator;
//import com.yy.testruleonline.bo.ActionDetailBo;
//import com.yy.testruleonline.bo.ConditionDetailBo;
//import com.yy.testruleonline.bo.ConditionGroupBo;
//import com.yy.testruleonline.bo.RuleBo;
//import com.yy.testruleonline.entity.*;
//import com.yy.testruleonline.service.*;
//import com.yy.testruleonline.utils.Constants;
//import org.apache.logging.log4j.util.Strings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class RuleManager {
//    @Autowired
//    public IConditionGroupService conditionGroupService;
//    @Autowired
//    public IConditionDetailService conditionDetailService;
//    @Autowired
//    public IRuleService ruleService;
//    @Autowired
//    public IActionDetailService actionDetailService;
//    @Autowired
//    public IParamService paramService;
//
//    @Autowired
//    public IParamClassifyService paramClassifyService;
//    
//    
//    public boolean test() {
//        Map<Integer, RuleBo> ruleBoMap = testBizBizService.getRuleBoList();
//        Constants.ruleBoMap = ruleBoMap;
//        String ruleId = param.get("ruleId");
//        RuleBo ruleBo = Constants.ruleBoMap.get(Integer.valueOf(ruleId));
//        ConditionGroupBo conditionGroupBo = ruleBo.getConditionGroupBo();
//
//        String expression = "conditionGroupRelation()";
//        Map<String, Object> params = new HashMap<>();
//        params.put("conditionGroupBo", conditionGroupBo);
//        params.put("input", param);
//        boolean isSatisfied  = (boolean) AviatorEvaluator.execute(expression,params);
//        System.out.println("isS:"+isSatisfied+"\n");
//        if(isSatisfied){
//            ActionDetail actionDetail = ruleBo.getActionDetailBo().getActionDetail();
//            switch (actionDetail.getActionType()){
//                case FUCTION:
//                    String actionExpress = actionDetail.getFunctionName();
//                    Object execute = AviatorEvaluator.execute(actionExpress);
//                    break;
//
//            }
//        }
//
//
////        for(ConditionDetailBo conditionDetailBo : conditionDetailBoList){
////            OperationType operation = conditionDetailBo.getConditionDetail().getOperation();
////            ParamClassify paramClassify = conditionDetailBo.getParamClassify();
////            Param param = conditionDetailBo.getParam();
////            
////        }
////        RelationType conditionRelation = conditionGroupBo.getConditionGroup().getConditionRelation();
//
//        return isSatisfied;
//    }
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
//        List<Integer> paramClassifyIdList = conditionDetails.stream().map(g -> g.getParamClassifyId()).collect(Collectors.toList());
//        List<Integer> paramClassifyIdList2 = actionDetails.stream().map(g -> g.getParamClassifyId()).collect(Collectors.toList());
//        paramClassifyIdList.addAll(paramClassifyIdList2);
//        List<ParamClassify> paramClassifies = paramClassifyService.selectBatchIds(paramClassifyIdList);
//        Map<Integer, ParamClassify> paramClassifyMap = paramClassifies.stream().collect(Collectors.toMap(ParamClassify::getId, t -> t));
//
//
//        List<Integer> paramClassifyValueList = conditionDetails.stream().map(g -> g.getParamClassifyValue()).collect(Collectors.toList());
//        List<Integer> paramClassifyValueList2 = actionDetails.stream().map(g -> g.getParamClassifyValue()).collect(Collectors.toList());
//        paramClassifyValueList.addAll(paramClassifyValueList2);
//        List<Param> params = paramService.selectBatchIds(paramClassifyValueList);
//        Map<Integer, Param> paramMap = params.stream().collect(Collectors.toMap(Param::getId, t -> t));
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
//                ParamClassify paramClassify = paramClassifyMap.get(conditionDetail.getParamClassifyId());
//                ConditionDetailBo conditionDetailBo = new ConditionDetailBo();
//                conditionDetailBo.setParamClassify(paramClassify);
//                if (conditionDetail.getParamClassifyValue() != null) {
//                    Param param = paramMap.get(conditionDetail.getParamClassifyValue());
//                    conditionDetailBo.setParam(param);
//                }
//                conditionDetailBo.setConditionDetail(conditionDetail);
//                conditionGroupBo.getConditionDetailBoList().add(conditionDetailBo);
//
//            });
//            ActionDetailBo actionDetailBo = new ActionDetailBo();
//            actionDetailBo.setActionDetail(actionDetail);
//            Optional.ofNullable(paramClassifyMap.get(actionDetail.getParamClassifyId()))
//                    .ifPresent(t -> actionDetailBo.setParamClassify(paramClassifyMap.get(actionDetail.getParamClassifyId())));
//
//            Optional.ofNullable(actionDetail.getParamClassifyValue())
//                    .ifPresent(t -> actionDetailBo.setParam(paramMap.get(actionDetail.getParamClassifyValue())));
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
//}
