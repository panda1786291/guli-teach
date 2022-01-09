package com.zjk.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjk.eduservice.chapter.CoursePublishVo;
import com.zjk.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjk.eduservice.entity.frontVo.CourseVo;
import com.zjk.eduservice.entity.frontVo.CourseWebVo;
import com.zjk.eduservice.entity.vo.CourseInfoVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zjk
 * @since 2021-12-20
 */
public interface EduCourseService extends IService<EduCourse> {

    void saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourseById(String id);

    Map<String,Object> getFrontCourseList(Page<EduCourse> eduCoursePage, CourseVo courseVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
