package com.imusic.mymusic.controller;

import com.alibaba.fastjson.JSONObject;
import com.imusic.mymusic.service.AdminService;
import com.imusic.mymusic.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    //判断是否登录成功
    @RequestMapping(value = "/admin/login/{username}/{password}",method = RequestMethod.GET)
    public Object loginStatus(@PathVariable("username") String username, @PathVariable("password") String password){
        JSONObject jsonObject = new JSONObject();
        boolean flag = adminService.verifyPassword(username,password);
        if (flag){
            jsonObject.put(Consts.USERNAME,username);
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MESSAGE,"登录成功");
            return jsonObject;
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MESSAGE,"用户名或密码错误");
            return jsonObject;
        }

    }
}
