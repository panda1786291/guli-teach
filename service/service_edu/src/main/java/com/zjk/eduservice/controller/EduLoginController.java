package com.zjk.eduservice.controller;

import com.zjk.common_utils.R;
import org.springframework.web.bind.annotation.*;

@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    //登录方法
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //获取用户信息
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}