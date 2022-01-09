package com.zjk.eduservice.controller;


import com.zjk.common_utils.R;
import com.zjk.eduservice.chapter.ChapterVo;
import com.zjk.eduservice.entity.EduChapter;
import com.zjk.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zjk
 * @since 2021-12-20
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

//    根据课程Id进行查询课程大纲列表
    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> lis = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("list",lis);
    }

    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return R.ok();
    }

    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        boolean b = chapterService.updateById(eduChapter);
        return R.ok();
    }


    @DeleteMapping("/{id}")
    /**
     * @Author Zhou Jingkai
     * @Description delete chapter by chapterId
     * @Date 2021/12/29 0:09
     * @Param * @param chapterId:
     * @return com.zjk.common_utils.R
     **/
    public R deleteChapter(@PathVariable String id) {

        boolean flag = chapterService.deleteChapter(id);

        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }

    }



}

