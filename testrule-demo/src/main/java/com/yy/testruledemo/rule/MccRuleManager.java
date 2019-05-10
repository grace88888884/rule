//package com.yy.testruledemo.rule;
//
//import com.yy.testruledemo.rule.rulebo.MccContext;
//import com.yy.testruleonline.rule.RuleManager;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.TreeSet;
//
//@Component
//public class MccRuleManager extends RuleManager<MccContext> {
//    TreeSet<MmsDeclineReason> mmsDeclineReasonTreeSet = new TreeSet<>(new Comparator<MmsDeclineReason>() {
//        @Override
//        public int compare(MmsDeclineReason o1, MmsDeclineReason o2) {
//            return 0;
//        }
//    });
//    
//    
//    @Override
//    public MccContext initContext() {
//        return new MccContext();
//    }
//}
