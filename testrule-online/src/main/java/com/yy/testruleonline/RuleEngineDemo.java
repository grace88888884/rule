package com.yy.testruleonline;  
  
import java.util.HashMap;  
import java.util.Map;  
  
import com.googlecode.aviator.AviatorEvaluator;  
import com.googlecode.aviator.Expression;
import com.yy.testruleonline.function.FieldsFunction;
import com.yy.testruleonline.function.RedisCountFunction;

public class RuleEngineDemo {  
  
    public static void main(String[] args) {  
        //注册自定义表达式函数  
        AviatorEvaluator.addFunction(new FieldsFunction());  
        AviatorEvaluator.addFunction(new RedisCountFunction());  
  
  
        //用户指定规则  
        String expression = "redisCount('1','hour',fields('userid,ip,action')) >= 100";  
        Expression compiledExp = AviatorEvaluator.compile(expression);  
  
  
        //运行时收到数据  
        Map<String, Object> fields = new HashMap<String, Object>();  
        fields.put("userid", "9527");  
        fields.put("ip", "127.0.0.1");  
        fields.put("phone", "18811223344");  
        fields.put("action", "click");  
  
        Boolean needAlarm = (Boolean) compiledExp.execute(fields);  
  
        if (needAlarm) {  
            System.out.printf("报警");  
        }  
    }  
  
  
    
}  