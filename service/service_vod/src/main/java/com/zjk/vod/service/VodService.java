package com.zjk.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadAliVideo(MultipartFile file);

    void deleteBatch(List<String> vodList);
}
