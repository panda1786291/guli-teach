package com.zjk.eduservice.service;

import com.zjk.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjk.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zjk
 * @since 2021-12-18
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllSubject();
}
