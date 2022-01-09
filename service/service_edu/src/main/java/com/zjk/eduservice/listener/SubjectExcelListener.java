package com.zjk.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjk.eduservice.entity.EduSubject;
import com.zjk.eduservice.entity.excel.SubjectData;
import com.zjk.eduservice.service.EduSubjectService;
import com.zjk.servicebase.exceptionhandler.GuliException;


public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener(){

    };

    public SubjectExcelListener(EduSubjectService subjectService){
        this.subjectService = subjectService;
    }


    /**
     * 一级分类判断
     * @param subjectService
     * @param name
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = subjectService.getOne(wrapper);
        return one;
    }

    /**
     * 二级分类判断
     */
    private EduSubject existOneSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject two = subjectService.getOne(wrapper);
        return two;
    }

    /**
     * 读取excel中的内容
     * @param subjectData
     * @param analysisContext
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData==null){
            throw new GuliException(20001,"文件数据为空");
        }

        EduSubject eduSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (eduSubject==null) {
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(eduSubject);
        }

        //获取1级分类的ID
        String pid = eduSubject.getId();
        EduSubject existTwoSubject = this.existOneSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject==null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

