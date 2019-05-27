package com.yy.testruledemo.outersystem;

import com.yy.testrule.loader.RequestHelper;
import com.yy.testrule.common.data.MmsContextInput;
import com.yy.testrule.common.data.MmsContextOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/rule")
public class TestController {
    @Autowired
   private RequestHelper<AuthContext, MmsContextInput, MmsContextOutput> requestHelper;
    private AuthContext authContext = new AuthContext();
  
    
//    @Autowired
//    MmsContextInput ruleManager;
//    @Autowired
//
    @ResponseBody
    @RequestMapping("/test")
    public AuthContext init(String merNo,String merType) {
        authContext.setMerNo(merNo);
        authContext.setMerType(merType);
        boolean isSatisfied = requestHelper.executeRule(authContext, "dayLmt2");
        System.out.println("isSatisfied:"+isSatisfied);
        return authContext;
    }


   

}
