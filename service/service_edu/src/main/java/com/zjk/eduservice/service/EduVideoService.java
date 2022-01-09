package com.zjk.eduservice.service;

import com.zjk.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author zjk
 * @since 2021-12-20
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String id);


    void removeAliyVideo(String videoId);

    void removeVideoById(String id);
}
