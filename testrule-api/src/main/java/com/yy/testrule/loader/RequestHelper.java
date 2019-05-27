package com.yy.testrule.loader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yy.testruleonline.rule.api.RuleGeneralRequest;
import com.yy.testruleonline.rule.api.RuleGeneralResponse;
import com.yy.testruleonline.rule.converter.InputConverter;
import com.yy.testruleonline.rule.converter.OutputConverter;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class RequestHelper<A, I, O> {
    InputConverter<A, I> inputConverter;
    OutputConverter<A, O> outputConverter;
    private RestTemplate restTemplate;


    public RequestHelper(InputConverter<A, I> mmsInputConverter, OutputConverter<A, O> outputConverter) {
        this.inputConverter = mmsInputConverter;
        this.outputConverter = outputConverter;
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setReadTimeout(5000);//单位为ms
//        factory.setConnectTimeout(5000);//单位为m
        restTemplate = new RestTemplate(factory);

    }

    public boolean executeRule(A context, String ruleName) {
        I input = inputConverter.convert(context);
        RuleGeneralRequest ruleGeneralRequest = new RuleGeneralRequest();
        ruleGeneralRequest.setRequest(input);
        ruleGeneralRequest.setRuleName(ruleName);
        String result = restTemplate.postForObject("http://localhost:8080/rule/testMerGroup", ruleGeneralRequest, String.class);
        RuleGeneralResponse<O> ruleResponse = JSON.parseObject(result, new TypeReference<RuleGeneralResponse<O>>() {
        });
        String s = JSON.toJSONString(ruleResponse.getResult());
        O o = JSON.parseObject(s, outputConverter.getOutputClass());
        if(ruleResponse.getRuleException()!=null&&ruleResponse.getRuleException().size()>0){
            System.out.println(ruleResponse.getRuleException());
            return false;
        }
        if (ruleResponse.getResult() != null) {
            outputConverter.convert(o, context);
        }
        return ruleResponse.getSatisfied();
    }

}
