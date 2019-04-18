package com.yy.testruleonline.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.enums.FunctionType;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class RedisCountFunction extends AbstractRuleFunction {  
  
        @Override  
        public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3) {  
            String period = FunctionUtils.getStringValue(arg1, env);  
            String timeUnit = FunctionUtils.getStringValue(arg2, env);  
            String redisKey = FunctionUtils.getStringValue(arg3, env);  
  
            System.out.println("FieldsFunction : " + period + " , " + timeUnit + " , " + redisKey);  
  
            //TODO 读取redis  
            int redisCount = redisGetAndIncrease(redisKey);  
  
            return new AviatorLong(redisCount);  
        }  
  
        private int redisGetAndIncrease(String redisKey) {  
            System.out.println("get redis : " + redisKey);  
            //这里查询redis获得活动的值；  
            return 10000;  
        }

    @Override
    public FunctionType getFuctionType() {
        return FunctionType.REDIS_COUNT;
    }
    }  