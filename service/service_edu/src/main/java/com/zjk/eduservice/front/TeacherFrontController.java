package com.zjk.eduservice.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjk.common_utils.R;
import com.zjk.eduservice.entity.EduCourse;
import com.zjk.eduservice.entity.EduTeacher;
import com.zjk.eduservice.service.EduCourseService;
import com.zjk.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:TeacherFrontController
 * @Description:
 * @Author:Zhou Jingkai
 * @Data:2022-01-04 00:24
 * @Version:1.0
 **/

@RestController
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService eduCourseService;


    @PostMapping("/getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable Long page,
                                 @PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(teacherPage);
        return R.ok().data(map);
    }

    @GetMapping("/getTeacherInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable("teacherId") String teacherId){
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        eduCourseQueryWrapper.eq("teacher_id",teacherId);
        List<EduCourse> list = eduCourseService.list(eduCourseQueryWrapper);
        return R.ok().data("teacher",eduTeacher).data("courseList",list);
    }
}
