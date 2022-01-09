package com.zjk.vod.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;


public class InitVodClient {

    public static DefaultAcsClient initVodClient(){
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId,
                ConstantVodUtils.ACCESS_KEY_ID,
                ConstantVodUtils.ACCESS_KEY_SECRET);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
