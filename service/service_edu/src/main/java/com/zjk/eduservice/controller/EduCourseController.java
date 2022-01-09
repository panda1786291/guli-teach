package com.zjk.eduservice.controller;


import com.zjk.common_utils.R;
import com.zjk.eduservice.chapter.CoursePublishVo;
import com.zjk.eduservice.chapter.VideoVo;
import com.zjk.eduservice.client.VodClient;
import com.zjk.eduservice.entity.vo.CourseInfoVo;
import com.zjk.eduservice.service.EduChapterService;
import com.zjk.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zjk
 * @since 2021-12-20
 */
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;
    @Value("${config.info}")
    private String info;

    @GetMapping("/getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = eduCourseService.publishCourseInfo(id);
        return R.ok().data("coursePublishVo",coursePublishVo);
    }

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程查询课程基本信息
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfo",courseInfoVo);
    }

    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    /**
     * @Author Zhou Jingkai
     * @Description delelte course include chapter and video
     * @Date 2021/12/29 20:58
     * @Param * @param id: course Id
     * @return com.zjk.common_utils.R
     **/
    public R deleteCourse(@PathVariable("id") String id){
        eduCourseService.removeCourseById(id);

        return R.ok();
    }


}

