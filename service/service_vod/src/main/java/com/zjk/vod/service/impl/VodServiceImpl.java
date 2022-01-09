package com.zjk.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.zjk.vod.service.VodService;
import com.zjk.vod.util.ConstantVodUtils;
import com.zjk.vod.util.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadAliVideo(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String title = file.getOriginalFilename();
            String fileName = file.getOriginalFilename();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID,
                    ConstantVodUtils.ACCESS_KEY_SECRET,
                    title,
                    fileName,
                    inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID

            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
            return response.getVideoId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteBatch(List<String> vodList) {
        DefaultAcsClient client = InitVodClient.initVodClient();
        DeleteVideoResponse response = new DeleteVideoResponse();
        DeleteVideoRequest request = new DeleteVideoRequest();
        try {
            StringBuilder vods = new StringBuilder();

            for (String vod:vodList){
                vods.append(vod).append(",");
            }

            String s = new String(vods.toString());
            request.setVideoIds(s);

            response = client.getAcsResponse(request);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");

    }
}
