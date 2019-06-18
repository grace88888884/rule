package com.yy.testruleonline.utils;

import com.yy.testruleonline.bo.RuleBo;
import com.yy.testruleonline.bo.RuleFlowBo;

import java.util.Map;

public class Constants {
   public static Map<String, RuleFlowBo> ruleFlowBoMap;

   public final static String conditionDetailBo = "conditionDetailBo";
//   public final static String conditionInput = "input";
   public final static String ruleOperation = "ruleOperation";
   public final static String context = "context";
   public final static String condBoMap = "condBoMap";
   public final static String ruleException = "ruleException";


   public static class resultMap {
      public final static String ruleResult = "ruleResult";
      public final static String isSatisfied = "isSatisfied";
      public final static String ruleException = Constants.ruleException;
      public final static String result = "result";

   }
}
