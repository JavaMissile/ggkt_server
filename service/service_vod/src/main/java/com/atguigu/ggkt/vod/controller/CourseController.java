package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vod.mapper.CourseMapper;
import com.atguigu.ggkt.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-07-02
 */
@Api(tags = "点播课程接口")
@RestController
@CrossOrigin
@RequestMapping("admin/vod/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("点播课程列表")
    @GetMapping("getCourse/{page}/{limit}")
    public Result getcourse(@PathVariable Long page,
                            @PathVariable Long limit,
                            CourseQueryVo courseQueryVo){
        Page<Course> pageParam = new Page<>(page,limit);
        Map<String,Object> map =courseService.findPageCourse(pageParam,courseQueryVo);
        return Result.ok(map);
    }

}

