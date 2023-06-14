package com.imusic.mymusic.controller;


import com.alibaba.fastjson.JSONObject;
import com.imusic.mymusic.pojo.Singer;
import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.pojo.SongList;
import com.imusic.mymusic.service.SongListService;
import com.imusic.mymusic.utils.Consts;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
public class SongListController {
    @Autowired
    SongListService songListService;

    //    查询所有歌单
    @RequestMapping(value = "/songList/select", method = RequestMethod.GET)
    public Object selectAllSongList() {
        JSONObject jsonObject = new JSONObject();
        List<SongList> songList = songListService.selectAllSongList();
        jsonObject.put(Consts.PLAYLIST, songList);
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "查询成功");
        return jsonObject;
    }
    //    添加歌单
    @SneakyThrows
    @RequestMapping(value = "/songList/add", method = RequestMethod.POST)
    public Object addSong(@RequestBody String songListMsg) {
        System.out.println(songListMsg);

//        从前端接收json数据格式的歌曲信息，并将json数据格式转化为Song对象格式
        SongList songList = JSONObject.parseObject(songListMsg, SongList.class);

//        添加歌单前查询是否存在
        SongList selectOne = songListService.selectOne(songList.getTitle());
        JSONObject jsonObject = new JSONObject();
        if (selectOne == null){
            //        添加歌曲
            boolean flag = songListService.addSongList(songList);
            if (flag == false){
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MESSAGE, "添加失败");
                return jsonObject;
            }else{
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "添加成功");
                return jsonObject;
            }
        }else{
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "该歌单已存在");
            return jsonObject;
        }
    }


    //    删除歌单
    @RequestMapping(value = "/songList/delete/{id}",method = RequestMethod.POST)
    public Object deleteSong(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        int flag = songListService.deleteSongList(id);
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

    //    更新歌单图片
    @SneakyThrows
    @RequestMapping(value = "/songList/updateImg", method = RequestMethod.POST)
    public Object updateSongImg(@RequestParam("file") MultipartFile songListImg, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (songListImg.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        }
//        添加时间戳，更改图片路径
        String fileName = System.currentTimeMillis() + songListImg.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "song";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
//        实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
//        储存到数据库为相对文件地址
        String storeAvatorPath = "/img/song/" + fileName;
        songListImg.transferTo(dest);
       SongList songList = new SongList();
        songList.setId(id);
        songList.setPicture(storeAvatorPath);
        boolean flag = songListService.updateSongList(songList);

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

    //    修改歌单
    @RequestMapping(value = "/songList/update",method = RequestMethod.POST)
    public Object updateSinger(@RequestBody String songListMsg) {
//        从前端接收json数据格式的歌手信息，并将json数据格式转化为Singer对象格式
        SongList songList = JSONObject.parseObject(songListMsg, SongList.class);
//        修改用户信息
        boolean flag = songListService.updateSongList(songList);
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

    //    根据风格查询所有歌单
    @RequestMapping(value = "/songList/select/{style}", method = RequestMethod.GET)
    public Object selectSongListByStyle(@PathVariable("style") String style) {
        JSONObject jsonObject = new JSONObject();
        List<SongList> songList = songListService.selectByStyle(style);
        jsonObject.put(Consts.PLAYLIST, songList);
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "查询成功");
        return jsonObject;
    }

}
