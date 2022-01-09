package com.zjk.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjk.common_utils.R;
import com.zjk.eduservice.entity.EduTeacher;
import com.zjk.eduservice.entity.vo.TeacherQuery;
import com.zjk.eduservice.service.EduTeacherService;
import com.zjk.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zjk
 * @since 2021-12-09
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);

        return R.ok().data("items",list);
    }

    @GetMapping("/find/{id}")
    public R findTeacherByID(@PathVariable("id") Long id){
        EduTeacher teacherByID = eduTeacherService.findTeacherByID(id);
//        int i = 10/0;
        return R.ok().data("items",teacherByID);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public R removeTeacher(@PathVariable("id")String id){
        boolean b = eduTeacherService.removeById(id);
        return R.ok().data("items",b);
    }

    @GetMapping(value = "/pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable("current")Long current,
                             @PathVariable("limit")Long limit){
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        eduTeacherService.page(teacherPage, null);
        List<EduTeacher> records = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        return R.ok().data("records",records).data("总数",total);
    }

    @PostMapping(value = "/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable("current")Long current,
                                  @PathVariable("limit")Long limit,
                                  @RequestBody(required = false)TeacherQuery teacherQuery){

        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();


        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            //构建条件
            eduTeacherQueryWrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            eduTeacherQueryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            eduTeacherQueryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            eduTeacherQueryWrapper.le("gmt_create",end);
        }

        eduTeacherService.page(teacherPage,eduTeacherQueryWrapper);
        List<EduTeacher> records = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        return R.ok().data("records",records).data("总数",total);
    }


    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }


}

