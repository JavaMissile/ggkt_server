package com.atguigu.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Missile
 * @Date 2022-07-01-13:23
 */
public interface FileService {
    String upload(MultipartFile file);
}
