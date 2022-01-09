package com.zjk.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjk.eduservice.entity.EduCourse;
import com.zjk.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zjk
 * @since 2021-12-09
 */
public interface EduTeacherService extends IService<EduTeacher> {

    EduTeacher findTeacherByID(Long id);

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage);

    List<EduCourse> getTeacherFrontInfo(String teacherId);
}
