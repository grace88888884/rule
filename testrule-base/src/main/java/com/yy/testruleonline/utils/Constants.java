package com.yy.testruleonline.utils;

import com.yy.testruleonline.bo.RuleBo;

import java.util.Map;

public class Constants {
   public static Map<String, RuleBo> ruleBoMap;

   public final static String conditionGroupEquation = "conditionGroupRelation";
   public final static String conditionOperationEquation = "conditionOperation";
   public final static String conditionDetailBo = "conditionDetailBo";
//   public final static String conditionInput = "input";
   public final static String conditionGroupBo = "conditionGroupBo";
   public final static String context = "context";
   public final static String ruleException = "ruleException";


   public static class resultMap {
      public final static String ruleResult = "ruleResult";
      public final static String isSatisfied = "isSatisfied";
      public final static String ruleException = Constants.ruleException;
      public final static String result = "result";

   }
}
