package com.atguigu.ggkt.vod.service;

import java.io.InputStream;

/**
 * @author Missile
 * @Date 2022-07-05-16:04
 */
public interface VodService {
    //上传视频
    String uploadVideo(InputStream inputStream, String originalFilename);
    //删除视频
    void removeVideo(String videoSourceId);
}
