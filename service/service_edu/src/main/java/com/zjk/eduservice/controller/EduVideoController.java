package com.zjk.eduservice.controller;


import com.zjk.common_utils.R;
import com.zjk.eduservice.client.VodClient;
import com.zjk.eduservice.entity.EduVideo;
import com.zjk.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author zjk
 * @since 2021-12-20
 */
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {


    @Autowired EduVideoService eduVideoService;

    /**
     * @Author Zhou Jingkai
     * @Description use openFeign call microService which named service-vod
     *              to delete video in Aliyun
     * @Date 2021/12/28 23:41
     * @Param * @param videoId: video‘s id in Aliyun
     * @return
     **/
    @DeleteMapping("/{id}")
    public R deleteVideo(@PathVariable("id") String id){
        //invoke remote microservice to delete video


        eduVideoService.removeVideoById(id);

        return R.ok();
    }


}

