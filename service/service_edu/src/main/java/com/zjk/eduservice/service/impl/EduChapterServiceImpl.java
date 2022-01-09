package com.zjk.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.ArrayListMultimap;
import com.zjk.eduservice.chapter.ChapterVo;
import com.zjk.eduservice.chapter.VideoVo;
import com.zjk.eduservice.entity.EduChapter;
import com.zjk.eduservice.entity.EduVideo;
import com.zjk.eduservice.mapper.EduChapterMapper;
import com.zjk.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjk.eduservice.service.EduVideoService;
import com.zjk.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zjk
 * @since 2021-12-20
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
    /**
     * @Author Zhou Jingkai
     * @Description 根据课程Id查询里面所有的章节和小节
     * @Date 2022/1/4 22:00
     * @Param * @param null:
     * @return
    **/
    @Override
    public List<ChapterVo>  getChapterVideoByCourseId(String courseId) {

        ArrayList<ChapterVo> chapterVos = new ArrayList<>();

        //1 根据课程ID查询课程里面的所有章节
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(eduChapterQueryWrapper);


        //2 根据课程ID查询课程里面的所有小节
        for (EduChapter eduChapter:eduChapters){
            ChapterVo chapterVo = new ChapterVo();
            chapterVos.add(chapterVo);
            BeanUtils.copyProperties(eduChapter,chapterVo);
            QueryWrapper<EduVideo> videoVoQueryWrapper = new QueryWrapper<>();
            videoVoQueryWrapper.eq("course_id",eduChapter.getCourseId());
            List<EduVideo> eduVideos = eduVideoService.list(videoVoQueryWrapper);
            for (EduVideo eduVideo:eduVideos){
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(eduVideo,videoVo);
                chapterVo.getChildren().add(videoVo);
            }
        }


        //3 遍历查询章节list集合进行封装

        //4 遍历查询小节list集合，进行封装
        return chapterVos;
    }



    @Override
    /**
     * @Author Zhou Jingkai
     * @Description delete whole chapter include video in Aliyun and data in database
     * @Date 2021/12/28 23:51
     * @Param * @param chapterId: chapter's id
     * @return boolean
     **/
    public boolean deleteChapter(String id) {
        //query videoId from database "edu_video"
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        List<EduVideo> list = eduVideoService.list(wrapper);
        boolean b = eduVideoService.removeById(id);
        for (EduVideo eduVideo:list){
            eduVideoService.removeVideoById(eduVideo.getId());
        }
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public void removeChapterByCourseId(String id) {
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",id);
        baseMapper.delete(eduChapterQueryWrapper);
    }
}
