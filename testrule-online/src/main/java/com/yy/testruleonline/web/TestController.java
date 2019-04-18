package com.yy.testruleonline.web;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.bo.ConditionGroupBo;
import com.yy.testruleonline.bo.RuleBo;
import com.yy.testruleonline.entity.ActionDetail;
import com.yy.testruleonline.entity.Param;
import com.yy.testruleonline.entity.ParamClassify;
import com.yy.testruleonline.enums.OperationType;
import com.yy.testruleonline.enums.RelationType;
import com.yy.testruleonline.service.impl.TestBizBizServiceImpl;
import com.yy.testruleonline.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rule")
public class TestController {

    @Autowired
    TestBizBizServiceImpl testBizBizService;

    @ResponseBody
    @RequestMapping("/init")
    public boolean init() {
        Map<Integer, RuleBo> ruleBoMap = testBizBizService.getRuleBoList();
        Constants.ruleBoMap = ruleBoMap;
        return false;
    }


    
    @ResponseBody
    @RequestMapping("/test")
    public boolean test(@RequestParam Map<String,String> param) {
            Map<Integer, RuleBo> ruleBoMap = testBizBizService.getRuleBoList();
            Constants.ruleBoMap = ruleBoMap;
        String ruleId = param.get("ruleId");
        RuleBo ruleBo = Constants.ruleBoMap.get(Integer.valueOf(ruleId));
        ConditionGroupBo conditionGroupBo = ruleBo.getConditionGroupBo();
        
        String expression = "conditionGroupRelation()";
        Map<String, Object> params = new HashMap<>();
        params.put("conditionGroupBo", conditionGroupBo);
        params.put("input", param);
        boolean isSatisfied  = (boolean) AviatorEvaluator.execute(expression,params);
        System.out.println("isS:"+isSatisfied+"\n");
        if(isSatisfied){
            ActionDetail actionDetail = ruleBo.getActionDetailBo().getActionDetail();
            switch (actionDetail.getActionType()){
                case FUCTION:
                    String actionExpress = actionDetail.getFunctionName();
                    Object execute = AviatorEvaluator.execute(actionExpress);
                    break;
                
            }
        }


//        for(ConditionDetailBo conditionDetailBo : conditionDetailBoList){
//            OperationType operation = conditionDetailBo.getConditionDetail().getOperation();
//            ParamClassify paramClassify = conditionDetailBo.getParamClassify();
//            Param param = conditionDetailBo.getParam();
//            
//        }
//        RelationType conditionRelation = conditionGroupBo.getConditionGroup().getConditionRelation();

        return isSatisfied;
    }

}
