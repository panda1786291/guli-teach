package com.zjk.eduservice.mapper;

import com.zjk.eduservice.chapter.CoursePublishVo;
import com.zjk.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjk.eduservice.entity.frontVo.CourseWebVo;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zjk
 * @since 2021-12-20
 */
@Repository
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String id);

    CourseWebVo getBaseCourseInfo(String courseId);
}
