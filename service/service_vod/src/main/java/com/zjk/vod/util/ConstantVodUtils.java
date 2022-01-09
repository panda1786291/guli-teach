package com.zjk.vod.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class ConstantVodUtils implements InitializingBean {

    @Value("${aliyun.vod.file.keyId}")
    private String accessKeyId;

    @Value("${aliyun.vod.file.keySecret}")
    private String accessKeySecret;

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID=accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
    }
}
