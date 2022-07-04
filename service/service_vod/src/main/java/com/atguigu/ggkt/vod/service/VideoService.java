package com.atguigu.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.Video;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-07-02
 */
public interface VideoService extends IService<Video> {

    void removeVideoByCourseId(Long id);

}
