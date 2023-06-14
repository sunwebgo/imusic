package com.imusic.mymusic.controller;


import com.alibaba.fastjson.JSONObject;
import com.imusic.mymusic.pojo.Singer;
import com.imusic.mymusic.pojo.SongList;
import com.imusic.mymusic.service.SingerService;
import com.imusic.mymusic.utils.Consts;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
public class SingerController {

    @Autowired
    SingerService singerService;

    //    添加歌手
    @RequestMapping(value = "/singer/add",method = RequestMethod.POST)
    public Object addSinger(@RequestBody String singerMsg) {
//        从前端接收json数据格式的歌手信息，并将json数据格式转化为Singer对象格式
        Singer singer = JSONObject.parseObject(singerMsg, Singer.class);
//        判断歌手是否存在
        Singer singerById = singerService.selectOne(singer.getName());

        JSONObject jsonObject = new JSONObject();
        if (singerById != null) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "该歌手已经存在");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "添加成功");
//            添加歌手
            singerService.addSinger(singer);
            System.out.println(singer);
            return jsonObject;
        }
    }

    //    查询所有歌手
    @RequestMapping(value = "/singer/select", method = RequestMethod.GET)
    public Object selectAllSinger() {
        JSONObject jsonObject = new JSONObject();
        List<Singer> singers = singerService.selectAllSinger();
        jsonObject.put(Consts.SINGERLIST, singers);
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "查询成功");
        return jsonObject;
    }

    //    更新歌手图片
    @SneakyThrows
    @RequestMapping(value = "/singer/updateImg", method = RequestMethod.POST)
    public Object updateSingerImg(@RequestParam("file") MultipartFile singerImg, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (singerImg.isEmpty()) {
            System.out.println("图片更新：");
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        }
//        添加时间戳，更改图片路径
        String fileName = System.currentTimeMillis() + singerImg.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singer";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
//        实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
//        储存到数据库为相对文件地址
        String storeAvatorPath = "/img/singer/" + fileName;
        System.out.println(storeAvatorPath.length());
        singerImg.transferTo(dest);
        Singer singer = new Singer();
        singer.setId(id);
        singer.setPicture(storeAvatorPath);
        boolean flag = singerService.updateSinger(singer);
        System.out.println("当前对象" + singer);

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

    //    修改歌手
    @RequestMapping(value = "/singer/update",method = RequestMethod.POST)
    public Object updateSinger(@RequestBody String singerMsg) {
//        从前端接收json数据格式的歌手信息，并将json数据格式转化为Singer对象格式
        Singer singer = JSONObject.parseObject(singerMsg, Singer.class);
//        修改用户信息
        boolean flag = singerService.updateSinger(singer);
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "修改成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "修改失败");
            return jsonObject;
        }
    }

    //    删除歌手
    @RequestMapping(value = "/singer/delete/{id}",method = RequestMethod.POST)
    public Object deleteSinger(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        int flag = singerService.deleteSinger(id);
        if (flag > 0) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "删除成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "删除失败");
            return jsonObject;
        }
    }

    //    根据性别查询所有歌手
    @RequestMapping(value = "/singer/select/{gender}", method = RequestMethod.GET)
    public Object selectSongListByStyle(@PathVariable("gender") String gender) {
        JSONObject jsonObject = new JSONObject();
        List<Singer> singers = singerService.selectSingerByGender(gender);
        jsonObject.put(Consts.SINGERLIST, singers);
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "查询成功");
        return jsonObject;
    }
}
