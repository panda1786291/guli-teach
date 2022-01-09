package com.zjk.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.zjk.common_utils.R;
import com.zjk.vod.service.VodService;
import com.zjk.vod.util.InitVodClient;
import org.apache.ibatis.annotations.Delete;
import org.ini4j.Ini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("/uploadAliVideo")
    public R uploadAliVideo(MultipartFile file){
        String vodId = vodService.uploadAliVideo(file);
        return R.ok().data("vodId",vodId);
    }

    /**
     * 根据视频ID删除阿里云的视频
     * @param id
     * @return
     */
    @DeleteMapping("/deleteAliVideo/{id}")
    public R deleteChapter(@PathVariable String id){

        DefaultAcsClient client = InitVodClient.initVodClient();
        DeleteVideoResponse response = new DeleteVideoResponse();
        DeleteVideoRequest request = new DeleteVideoRequest();
        try {
            request.setVideoIds(id);
            response = client.getAcsResponse(request);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
        return R.ok();
    }

    @DeleteMapping("/deleteBatch")
    public R deleteBatch(@RequestParam("vodList") List<String> vodList){
        vodService.deleteBatch(vodList);
        return R.ok();
    }
}
