package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Comment;
import com.atguigu.ggkt.vod.mapper.CommentMapper;
import com.atguigu.ggkt.vod.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-07-02
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
