package com.imusic.mymusic.controller;


import com.alibaba.fastjson.JSONObject;
import com.imusic.mymusic.pojo.Singer;
import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.service.SingerService;
import com.imusic.mymusic.service.SongListSongService;
import com.imusic.mymusic.service.impl.SongServiceImpl;
import com.imusic.mymusic.utils.Consts;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SongController {

    @Autowired
    SongServiceImpl songService;
    @Autowired
    SingerService singerService;

    @Autowired
    SongListSongService songListSongService;

    private String songUrl;


    //    查询所有歌曲
    @RequestMapping(value = "/song/select", method = RequestMethod.GET)
    public Object selectAllSong() {
        JSONObject jsonObject = new JSONObject();
        List<Song> songs = songService.selectAllSong();
        jsonObject.put(Consts.SONGLIST, songs);
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "查询成功");
        return jsonObject;
    }

    //    根据歌曲名/歌手名模糊查询
    @RequestMapping(value = "/select/song_singer/{msg}", method = RequestMethod.GET)
    public Object selectSongAndSinger(@PathVariable("msg") String msg) {
        JSONObject jsonObject = new JSONObject();
//        根据歌曲名模糊查询并添加
        List<Song> songs = new ArrayList<>();
        songs.addAll(songService.selectSongDim(msg));

//      模糊查询歌手
        List<Singer> singers = singerService.selectSingerDim(msg);
//        根据歌手id查询歌曲并添加
        if (singers != null) {
//            遍历查询到的所有歌手并添加歌曲
            for (Singer item : singers) {
                songs.addAll(songService.selectAllSongBySingerId(item.getId()));
            }
        }
        if (songs != null) {
            jsonObject.put(Consts.SONGLIST, songs);
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "查询成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "无符合条件的歌手或歌曲");
            return jsonObject;
        }

    }

    //    根据歌手id查询所有歌曲
    @RequestMapping(value = "/song/select/{singer_id}", method = RequestMethod.GET)
    public Object selectAllSong(@PathVariable("singer_id") int singer_id) {
        JSONObject jsonObject = new JSONObject();
        List<Song> songs = songService.selectAllSongBySingerId(singer_id);
        jsonObject.put(Consts.SONGLIST, songs);
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "查询成功");
        return jsonObject;
    }


    //    添加歌曲
    @RequestMapping(value = "/song/add", method = RequestMethod.POST)
    public Object addSong(@RequestBody String songMsg) {
//        从前端接收json数据格式的歌曲信息，并将json数据格式转化为Song对象格式
        Song song = JSONObject.parseObject(songMsg, Song.class);
        song.setUrl(songUrl);

//        添加歌曲
        JSONObject jsonObject = new JSONObject();
//        首先查询该歌曲在歌曲库中是否存在
        Song getSong = songService.selectOneByName(song.getName());
//        当歌曲不存在时进行添加
        if (getSong == null) {
            boolean flag = songService.addSong(song);
            songUrl = "";
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "添加成功");

                return jsonObject;
            } else {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "添加失败");
                return jsonObject;
            }
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "该歌曲已经存在！");
            return jsonObject;
        }


    }

    //    歌曲文件上传映射
    @SneakyThrows
    @RequestMapping(value = "/song/upload", method = RequestMethod.POST)
    public void uploadMusic(@RequestParam("file") MultipartFile file) {
//        添加时间戳，更改图片路径
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.mkdir();
        }
//        实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
//        储存到数据库为相对文件地址
        String storeUrlPath = "/song/" + fileName;
        file.transferTo(dest);
        songUrl = storeUrlPath;
    }

    //    删除歌曲
    @RequestMapping(value = "/song/delete/{id}", method = RequestMethod.POST)
    public Object deleteSong(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        boolean flag1 = songService.deleteSong(id);
        boolean flag2 = songListSongService.deleteSong(id);
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "删除成功");
        return jsonObject;
    }

    //    修改歌曲
    @RequestMapping(value = "/song/update", method = RequestMethod.POST)
    public Object updateSong(@RequestBody String songMsg) {
//        从前端接收json数据格式的歌曲信息，并将json数据格式转化为Song对象格式
        Song song = JSONObject.parseObject(songMsg, Song.class);

//        修改歌曲信息
        boolean flag = songService.updateSong(song);
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

    //    更新歌曲图片
    @SneakyThrows
    @RequestMapping(value = "/song/updateImg", method = RequestMethod.POST)
    public Object updateSongImg(@RequestParam("file") MultipartFile songImg, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (songImg.isEmpty()) {
            System.out.println("图片更新：");
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        }
//        添加时间戳，更改图片路径
        String fileName = System.currentTimeMillis() + songImg.getOriginalFilename();
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
        songImg.transferTo(dest);
        Song song = new Song();
        song.setId(id);
        song.setPicture(storeAvatorPath);
        boolean flag = songService.updateSong(song);

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


}
