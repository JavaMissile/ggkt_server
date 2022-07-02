package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-07-01
 */
@Api(tags = "课程接口")
@CrossOrigin
@RestController
@RequestMapping("/admin/vod/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //查询下一层课程分类
    //根据parent_id
    @ApiOperation("查询下一层的课程分类")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectList(id);
        return Result.ok(list);
    }

    @ApiOperation("文件导出")
    @GetMapping("export")
    public void exportFile(HttpServletResponse response){
        subjectService.exportData(response);

    }

    @ApiOperation("文件导入")
    @PostMapping("import")
    public Result importFile(MultipartFile file){
        subjectService.importData(file);
        return Result.ok(null);
    }
}

