package com.zjk.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjk.eduservice.client.VodClient;
import com.zjk.eduservice.entity.EduVideo;
import com.zjk.eduservice.mapper.EduVideoMapper;
import com.zjk.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author zjk
 * @since 2021-12-20
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String id) {
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",id);
        baseMapper.delete(eduVideoQueryWrapper);
    }

    @Override
    public void removeAliyVideo(String videoId) {
        vodClient.removeAliyVideo(videoId);
    }

    @Override
    /**
     * @Author Zhou Jingkai
     * @Description delete from both database and aliyun
     * @Date 2021/12/29 0:18
     * @Param * @param id: videoId
     * @return void
     **/
    public void removeVideoById(String id) {
        EduVideo eduVideo = baseMapper.selectById(id);
        baseMapper.deleteById(id);
        vodClient.removeAliyVideo(eduVideo.getVideoSourceId());
    }


}
