package com.zjk.eduservice.mapper;

import com.zjk.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author zjk
 * @since 2021-12-09
 */
@Repository
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

    EduTeacher findTeacherByID(Long id);
}
