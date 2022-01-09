package com.zjk.eduservice.client;

import com.zjk.common_utils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeAliyVideo(String id) {
        return R.error().message("视频删除出错");
    }

    @Override
    public R removeAliyVideoBatch(List<String> list) {
        return R.error().message("多组视频删除失败");
    }
}
