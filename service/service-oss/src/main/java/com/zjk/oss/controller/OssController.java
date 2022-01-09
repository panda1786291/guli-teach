package com.zjk.oss.controller;

import com.zjk.common_utils.R;
import com.zjk.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    private OssService ossService;
    @PostMapping
    public R uploadOssFile(MultipartFile multipartFile){
        String url = ossService.uploadFileAvatar(multipartFile);
        return R.ok().data("url",url);
    }
}
