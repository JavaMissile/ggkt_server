package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.model.vod.CourseDescription;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vo.vod.CourseVo;
import com.atguigu.ggkt.vod.mapper.CourseMapper;
import com.atguigu.ggkt.vod.service.CourseDescriptionService;
import com.atguigu.ggkt.vod.service.CourseService;
import com.atguigu.ggkt.vod.service.SubjectService;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-07-02
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CourseDescriptionService descriptionService;
    @Override
    public Map<String,Object> findPageCourse(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(courseQueryVo.getTeacherId()))
            queryWrapper.eq("teacher_id",courseQueryVo.getTeacherId());
        if(!StringUtils.isEmpty(courseQueryVo.getSubjectId()))
            queryWrapper.eq("subject_id",courseQueryVo.getSubjectId());
        if(!StringUtils.isEmpty(courseQueryVo.getSubjectParentId()))
            queryWrapper.eq("subject_parent_id",courseQueryVo.getSubjectParentId());
        if(!StringUtils.isEmpty(courseQueryVo.getTitle()))
            queryWrapper.like("title",courseQueryVo.getTitle());

        Page<Course> pages = baseMapper.selectPage(pageParam, queryWrapper);
        long totalCount = pages.getTotal();//总记录数
        long totalPage = pages.getPages();//总页数
        long currentPage = pages.getCurrent();//当前页
        long size = pages.getSize();//每页记录数
        //每页数据集合
        List<Course> records = pages.getRecords();
        //遍历封装讲师和分类名称
        records.stream().forEach(item -> {
            this.getTeacherOrSubjectName(item);
        });
        //封装返回数据
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",records);
        map.put("currentPage",currentPage);
        map.put("size",size);
        return map;
    }

    @Override
    public Long saveCourse(CourseFormVo courseFormVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.insert(course);
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setId(courseFormVo.getId());
        descriptionService.save(courseDescription);
        return course.getId();
    }

    @Override
    public CourseFormVo getCourseInfoById(Long id) {
        Course course = baseMapper.selectById(id);
        if (course==null){
            return  null;
        }
        CourseDescription courseDescription = descriptionService.getById(id);
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course, courseFormVo);
        if (courseDescription!=null){
            courseFormVo.setDescription(courseDescription.getDescription());
        }
        return courseFormVo;
    }

    @Override
    public void updateCourseId(CourseFormVo courseFormVo) {
        Course course = new Course();
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseFormVo,course);
        courseDescription.setDescription(courseFormVo.getDescription());
        baseMapper.updateById(course);
        descriptionService.updateById(courseDescription);
    }

    //获取讲师和分类名称
    private Course getTeacherOrSubjectName(Course course) {
        //查询讲师名称
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if(teacher != null) {
            course.getParam().put("teacherName",teacher.getName());
        }
        //查询分类名称
        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if(subjectOne != null) {
            course.getParam().put("subjectParentTitle",subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if(subjectTwo != null) {
            course.getParam().put("subjectTitle",subjectTwo.getTitle());
        }
        return course;
    }
}
