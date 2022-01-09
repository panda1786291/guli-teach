package com.zjk.eduservice.controller;


import com.zjk.common_utils.R;
import com.zjk.eduservice.entity.EduSubject;
import com.zjk.eduservice.entity.subject.OneSubject;
import com.zjk.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.security.PublicKey;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zjk
 * @since 2021-12-18
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }

    @GetMapping("/getAllSubject")
    public R getAllSubject(){
        List<OneSubject> oneSubjectList = eduSubjectService.getAllSubject();
        return R.ok().data("AllSubject",oneSubjectList);
    }

}

