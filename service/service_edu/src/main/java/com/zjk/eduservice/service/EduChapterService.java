package com.zjk.eduservice.service;

import com.zjk.eduservice.chapter.ChapterVo;
import com.zjk.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zjk
 * @since 2021-12-20
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String id);
}
