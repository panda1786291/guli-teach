package com.zjk.eduservice.client;

import com.zjk.common_utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Component
@FeignClient(name ="service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    @DeleteMapping("/eduvod/video/deleteAliVideo/{id}")
    public R removeAliyVideo(@PathVariable String id);


    @DeleteMapping("/eduvod/video/deleteBatch")
    public R removeAliyVideoBatch(List<String> list);
}
