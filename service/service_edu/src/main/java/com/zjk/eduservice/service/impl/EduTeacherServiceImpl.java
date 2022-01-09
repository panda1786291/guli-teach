package com.zjk.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjk.common_utils.R;
import com.zjk.eduservice.entity.EduCourse;
import com.zjk.eduservice.entity.EduTeacher;
import com.zjk.eduservice.mapper.EduTeacherMapper;
import com.zjk.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author zjk
 * @since 2021-12-09
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public EduTeacher findTeacherByID(Long id) {
        EduTeacher eduTeacher = eduTeacherMapper.findTeacherByID(id);
        return eduTeacher;
    }

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        baseMapper.selectPage(teacherPage,teacherQueryWrapper);

        List<EduTeacher> records = teacherPage.getRecords();
        long current = teacherPage.getCurrent();
        long size = teacherPage.getSize();
        long total = teacherPage.getTotal();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();

        HashMap<String, Object> teacherMap = new HashMap<>();
        teacherMap.put("records",records);
        teacherMap.put("current",current);
        teacherMap.put("size",size);
        teacherMap.put("total",total);
        teacherMap.put("hasNext",hasNext);
        teacherMap.put("hasPrevious",hasPrevious);


        return teacherMap;
    }

    @Override
    public List<EduCourse> getTeacherFrontInfo(String teacherId) {

        return null;
    }


}
