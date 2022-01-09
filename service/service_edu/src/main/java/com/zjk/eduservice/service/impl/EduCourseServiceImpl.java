package com.zjk.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjk.common_utils.R;
import com.zjk.eduservice.chapter.CoursePublishVo;
import com.zjk.eduservice.entity.EduChapter;
import com.zjk.eduservice.entity.EduCourse;
import com.zjk.eduservice.entity.EduCourseDescription;
import com.zjk.eduservice.entity.EduTeacher;
import com.zjk.eduservice.entity.frontVo.CourseVo;
import com.zjk.eduservice.entity.frontVo.CourseWebVo;
import com.zjk.eduservice.entity.vo.CourseInfoVo;
import com.zjk.eduservice.mapper.EduCourseMapper;
import com.zjk.eduservice.service.EduChapterService;
import com.zjk.eduservice.service.EduCourseDescriptionService;
import com.zjk.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjk.eduservice.service.EduVideoService;
import com.zjk.servicebase.exceptionhandler.GuliException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zjk
 * @since 2021-12-20
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);

        //向课程表中添加课程基本信息
        int insert = baseMapper.insert(eduCourse);
        if (insert<=0){
            throw new GuliException(20001,"添加课程失败");
        }



        String id = eduCourse.getId();
        //向课程简介表中添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        eduCourseDescription.setId(id);
        eduCourseDescriptionService.save(eduCourseDescription);

    }

    @Override
    @Cacheable(key = "'courseInfo'",value = "course")
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        //查询课程描述
        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(description,courseInfoVo);

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);

        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,description);
        boolean b = eduCourseDescriptionService.updateById(description);

    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    /**
     * @Author Zhou Jingkai
     * @Description
     * @Date 2021/12/29 21:18
     * @Param * @param id:courseId
     * @return void
     **/
    public void removeCourseById(String id) {
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",id);

        List<EduChapter> chapters = eduChapterService.list(eduChapterQueryWrapper);

        for (EduChapter chapter:chapters){
            eduChapterService.deleteChapter(chapter.getId());

        }
        int i = baseMapper.deleteById(id);
        if (i==0){
            throw new GuliException(20001,"删除失败");
        }
    }

    @Override
    public Map<String, Object> getFrontCourseList(Page<EduCourse> eduCoursePage, CourseVo courseVo) {

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseVo.getSubjectParentId())){
            wrapper.eq("subject_parent_id",courseVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseVo.getSubjectId())){
            wrapper.eq("subject_id",courseVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseVo.getBuyCountSort())){
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseVo.getPriceSort())){
            wrapper.orderByDesc("price");
        }
        if (!StringUtils.isEmpty(courseVo.getGmtCreatSort())){
            wrapper.orderByDesc("gmt_creat");
        }
        baseMapper.selectPage(eduCoursePage,wrapper);

        List<EduCourse> records = eduCoursePage.getRecords();
        long current = eduCoursePage.getCurrent();
        long size = eduCoursePage.getSize();
        long total = eduCoursePage.getTotal();
        boolean hasNext = eduCoursePage.hasNext();
        boolean hasPrevious = eduCoursePage.hasPrevious();


        HashMap<String, Object> map = new HashMap<>();
        map.put("records",records);
        map.put("current",current);
        map.put("size",size);
        map.put("total",total);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        CourseWebVo courseWebVo = eduCourseMapper.getBaseCourseInfo(courseId);
        return courseWebVo;
    }


}
