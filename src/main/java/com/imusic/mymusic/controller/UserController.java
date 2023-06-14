package com.imusic.mymusic.controller;


import com.alibaba.fastjson.JSONObject;
import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.pojo.User;
import com.imusic.mymusic.service.UserService;
import com.imusic.mymusic.service.impl.UserServiceImpl;
import com.imusic.mymusic.utils.Consts;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService = new UserServiceImpl();

    //    查询所有用户
    @RequestMapping(value = "/user/select", method = RequestMethod.GET)
    public Object selectAllUser() {
        JSONObject jsonObject = new JSONObject();
        List<User> users = userService.selectAllUser();
        jsonObject.put(Consts.USERLIST, users);
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "查询成功");
        return jsonObject;
    }

    //    添加用户
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public Object addUser(@RequestBody String userMsg) {
        JSONObject jsonObject = new JSONObject();
        User user = JSONObject.parseObject(userMsg, User.class);
        System.out.println(user);
//        首先查询该用户是否已经存在
        Boolean flag = userService.selectOne(user.getUsername());
        if (flag) {
//            用户不存在，添加用户
            userService.addUser(user);
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "注册成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "该用户已经存在");
            return jsonObject;
        }
    }

    //    用户登录
    @RequestMapping(value = "/user/login/{username}/{password}", method = RequestMethod.GET)
    public Object loginStatus(@PathVariable("username") String username, @PathVariable("password") String password) {
        JSONObject jsonObject = new JSONObject();
        User user = userService.verifyPassword(username, password);
        System.out.println(user);
        if (user != null) {
            jsonObject.put(Consts.USER, user);
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "登录成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "用户名或密码错误");
            return jsonObject;
        }
    }

    //根据id查询一个用户
    @RequestMapping(value = "/user/select/{id}", method = RequestMethod.GET)
    public Object selectUserById(@PathVariable("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        User user = userService.selectOneById(id);
        if (user != null) {
            jsonObject.put(Consts.USER, user);
            jsonObject.put(Consts.CODE, 1);
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            return jsonObject;
        }
    }

//    根据当前用户id查询收藏表里面的音乐
    @RequestMapping(value = "/user/getCollect/{id}",method = RequestMethod.GET)
    public Object selectCollectByUserId(@PathVariable("id") int id){
        JSONObject jsonObject = new JSONObject();
        List<Song> songs = userService.selectCollectByUserId(id);
        if (songs != null) {
            jsonObject.put(Consts.SONGLIST, songs);
            jsonObject.put(Consts.CODE, 1);
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            return jsonObject;
        }
    }

    //   根据用户id修改头像
    @SneakyThrows
    @RequestMapping(value = "/user/updateImg", method = RequestMethod.POST)
    public Object updateUserImg(@RequestParam("file") MultipartFile userImg, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (userImg.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        }
//        添加时间戳，更改图片路径
        String fileName = System.currentTimeMillis() + userImg.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "user";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
//        实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
//        储存到数据库为相对文件地址
        String storeAvatorPath = "/img/user/" + fileName;
        userImg.transferTo(dest);
        User user = new User();
        user.setId(id);
        user.setPicture(storeAvatorPath);
        boolean flag = userService.updateUser(user);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "上传成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        }

    }

    //    修改用户信息
    @RequestMapping(value = "/user/update",method = RequestMethod.POST)
    public Object updateUser(@RequestBody String userMsg) {
//        从前端接收json数据格式的歌手信息，并将json数据格式转化为user对象格式
        User user = JSONObject.parseObject(userMsg, User.class);
//        修改用户信息
        boolean flag = userService.updateUser(user);
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "修改成功");
            jsonObject.put(Consts.USERNAME, user.getUsername());
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "修改失败");
            return jsonObject;
        }
    }

    //    删除用户
    @RequestMapping(value = "/user/delete",method = RequestMethod.POST)
    public Object deleteUser(@RequestBody String userMsg) {
        JSONObject jsonObject = new JSONObject();
//        从前端接收json数据格式的歌手信息，并将json数据格式转化为user对象格式
        User user = JSONObject.parseObject(userMsg, User.class);
//        根据用户id和密码查询用户是否存在
        User user1 = userService.selectByIdAndPwd(user.getId(), user.getPassword());
//        用户存在删除用户
        if (user1 != null){
            userService.deleteUser(user.getId());
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "注销成功！");
            jsonObject.put(Consts.USERNAME, user.getUsername());
            return jsonObject;
        }else{
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "用户密码错误！");
            return jsonObject;
        }
    }
}
