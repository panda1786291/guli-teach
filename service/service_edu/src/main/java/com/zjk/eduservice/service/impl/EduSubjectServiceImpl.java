package com.zjk.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjk.eduservice.entity.EduSubject;
import com.zjk.eduservice.entity.excel.SubjectData;
import com.zjk.eduservice.entity.subject.OneSubject;
import com.zjk.eduservice.entity.subject.TwoSubject;
import com.zjk.eduservice.listener.SubjectExcelListener;
import com.zjk.eduservice.mapper.EduSubjectMapper;
import com.zjk.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zjk
 * @since 2021-12-18
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private EduSubjectMapper subjectMapper;

    /**
     * 读取excel中的内容
     * @param file
     * @param eduSubjectService
     */
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream,
                    SubjectData.class,
                    new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 课程分类，树形结构
     * @return
     */
    @Override
    public List<OneSubject> getAllSubject() {

        return this.getOneSubjectBySql();
    }

    private List<OneSubject> getTreeByListAll(){
        List<OneSubject> oneSubjectList = new ArrayList<>();
        List<EduSubject> eduSubjects = this.list(null);
        for (EduSubject eduSubject : eduSubjects) {
            if ("0".equals(eduSubject.getParentId())) {
                OneSubject oneSubject = new OneSubject();
                oneSubject.setId(eduSubject.getId());
                oneSubject.setTitle(eduSubject.getTitle());
                oneSubjectList.add(oneSubject);
//                eduSubjects.remove(eduSubject);
            };
        }

        for (EduSubject eduSubject : eduSubjects) {
            TwoSubject twoSubject = new TwoSubject();
            String parentId = eduSubject.getParentId();
            twoSubject.setId(eduSubject.getId());
            twoSubject.setTitle(eduSubject.getTitle());
            for (OneSubject oneSubject:oneSubjectList){
                if (parentId.equals(oneSubject.getId())){
                    oneSubject.getChildren().add(twoSubject);
                }
            }
        }
        return oneSubjectList;
    }

    private List<OneSubject> getOneSubjectBySql(){
        List<OneSubject> oneSubjectList = new ArrayList<>();

        //查询所有一级分类
        QueryWrapper<EduSubject> oneSubjectWrapper = new QueryWrapper<>();
        oneSubjectWrapper.eq("parent_id","0");
        List<EduSubject> oneEduSubjects = this.list(oneSubjectWrapper);

        //查询所有二级分类
        QueryWrapper<EduSubject> twoSubjectWrapper = new QueryWrapper<>();
        twoSubjectWrapper.ne("parent_id","0");
        List<EduSubject> twoEduSubjects = this.list(twoSubjectWrapper);

        for (EduSubject oneEduSubject:oneEduSubjects){
            OneSubject oneSubject = new OneSubject();
            oneSubject.setId(oneEduSubject.getId());
            oneSubject.setTitle(oneEduSubject.getTitle());
            oneSubjectList.add(oneSubject);

            for (EduSubject twoEduSubject:twoEduSubjects){
                if (twoEduSubject.getParentId().equals(oneSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    twoSubject.setId(twoEduSubject.getId());
                    twoSubject.setTitle(twoEduSubject.getTitle());
                    oneSubject.getChildren().add(twoSubject);
                }
            }
        }
        return oneSubjectList;
    }
}
