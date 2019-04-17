package com.yy.testruleonline.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.Map;

public class FieldsFunction extends AbstractFunction {  
  
        @Override  
        public AviatorObject call(Map<String, Object> env, AviatorObject fieldsStrObj) {  
            //获取可变参数  
            String fieldStr = fieldsStrObj.stringValue(env);  
            String[] fields = fieldStr.split(",");  
            StringBuilder redisKey = new StringBuilder();  
  
            System.out.println("FieldsFunction : " + fieldStr);  
  
            for (String f : fields) {  
                Object value = env.get(f);  
                if (value != null) {  
                    redisKey.append(value.toString());  
                } else {  
                    //TODO 参数合法性校验  
                }  
                redisKey.append(":");  
            }  
  
            //TODO key 长多过长，会影响redis性能  
            return new AviatorString(redisKey.toString());  
        }  
  
        public String getName() {  
            return "fields";  
        }  
    }  