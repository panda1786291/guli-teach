package com.zjk.eduservice.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjk.common_utils.R;
import com.zjk.eduservice.chapter.ChapterVo;
import com.zjk.eduservice.entity.EduCourse;
import com.zjk.eduservice.entity.frontVo.CourseVo;
import com.zjk.eduservice.entity.frontVo.CourseWebVo;
import com.zjk.eduservice.service.EduChapterService;
import com.zjk.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:CourseFrontController
 * @Description: 条件查询带分页的查询课程
 * @Author:Zhou Jingkai
 * @Data:2022-01-04 01:25
 * @Version:1.0
 **/
@RestController
@RequestMapping("/eduService/courseFront")
public class CourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page,
                                @PathVariable long limit,
                                @RequestBody(required = false) CourseVo courseVo){

        Page<EduCourse> eduCoursePage = new Page<>(page,limit);


        Map<String, Object> frontCourseList = eduCourseService.getFrontCourseList(eduCoursePage, courseVo);
        return R.ok().data("map",frontCourseList);
    }


    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
        CourseWebVo courseWebVo =  eduCourseService.getBaseCourseInfo(courseId);

        List<ChapterVo> chapterList =
                eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterList",chapterList);
    }
}
