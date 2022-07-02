package com.atguigu.ggkt.vod.utils;

import com.sun.org.apache.xml.internal.security.Init;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Missile
 * @Date 2022-07-01-13:18
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${tencent.cos.file.region}")
    private String region;

    @Value("${tencent.cos.file.secretId}")
    private String secretId;

    @Value("${tencent.cos.file.secretKey}")
    private String secretKey;

    @Value("${tencent.cos.file.bucketName}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = region;
        ACCESS_KEY_ID = secretId;
        ACCESS_KEY_SECRET = secretKey;
        BUCKET_NAME = bucketName;
    }
}
