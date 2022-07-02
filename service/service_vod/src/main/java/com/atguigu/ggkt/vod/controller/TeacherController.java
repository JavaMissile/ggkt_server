package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-06-29
 */
@Api(tags = "讲师管理接口")
@CrossOrigin//跨域临时解决方案
@RestController
@RequestMapping("/admin/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * http://localhost:8301/admin/vod/teacher/findAll
     * 返回所有讲师列表
     *
     * @return list
     */
    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public Result findAllTeacher() {
        return Result.ok(teacherService.list());

    }

    /**
     * http://localhost:8301/admin/vod/teacher/remove/?
     * 通过id逻辑删除讲师
     *
     * @param id
     * @return true删除成功，false删除失败
     */
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true) @PathVariable Long id) {
        return teacherService.removeById(id) ? Result.ok(null) : Result.fail(null);
    }

    /**
     * 分页条件查询讲师
     * @param current 当前页
     * @param limit 每页记录数
     * @param teacherQueryVo wrapper条件实体
     * @return
     */
    @ApiOperation("分页条件查询讲师")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findQueryPage(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        Page<Teacher> pageParam = new Page<>(current, limit);
        if (teacherQueryVo == null){
            return Result.ok(teacherService.page(pageParam));
        }else{
            Integer level = teacherQueryVo.getLevel();
            String name = teacherQueryVo.getName();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            if(!StringUtils.isEmpty(level)){
                wrapper.eq("level",level);
            }
            if(!StringUtils.isEmpty(name)){
                wrapper.like("name",name);
            }
            if(!StringUtils.isEmpty(joinDateBegin)){
                wrapper.ge("join_date",joinDateBegin);
            }
            if(!StringUtils.isEmpty(joinDateEnd)){
                wrapper.le("join_date",joinDateEnd);
            }
            return Result.ok(teacherService.page(pageParam, wrapper));
        }

    }

    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody Teacher teacher) {
        return teacherService.save(teacher) ? Result.ok(null).message("添加成功!!") : Result.fail(null).message("添加失败!!") ;
    }

    @ApiOperation("根据ID查询讲师")
    @GetMapping("findTeacher/{id}")
    public Result findTeacherById(@PathVariable long id){
        return  Result.ok(teacherService.getById(id));
    }

    @ApiOperation("修改讲师")
    @PostMapping("update")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateById(teacher) ? Result.ok(null) : Result.fail(null);
    }

    @ApiOperation("批量删除讲师")
    @DeleteMapping("removeBatch")
    public Result removeBatchTeacher(@RequestBody List<Long> list) {
        return teacherService.removeByIds(list) ? Result.ok(null) : Result.fail(null);

    }
}

