package com.yy.testruledemo.outersystem;

import com.yy.testrule.loader.RequestHelper;
import com.yy.testruledemo.outersystem.converter.MmsInputConverter;
import com.yy.testruledemo.outersystem.converter.MmsOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MmsConfig {
    @Autowired
    MmsInputConverter mmsInputConverter;
    @Autowired
    MmsOutputConverter mmsOutputConverter;

    @Bean
    public RequestHelper mmsRequestHelper() {
        return new RequestHelper(mmsInputConverter, mmsOutputConverter);
    }
}
