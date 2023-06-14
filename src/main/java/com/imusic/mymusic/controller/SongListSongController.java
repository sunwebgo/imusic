package com.imusic.mymusic.controller;


import com.alibaba.fastjson.JSONObject;
import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.pojo.SongListSong;
import com.imusic.mymusic.service.SongListSongService;
import com.imusic.mymusic.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongListSongController {
    @Autowired
    SongListSongService songListSongService;

    //    根据歌单id查询该歌单内的所有音乐
    @RequestMapping(value = "/playList_song/select/{playList_id}", method = RequestMethod.GET)
    public Object selectAllSong(@PathVariable("playList_id") int playList_id) {
        JSONObject jsonObject = new JSONObject();
        List<SongListSong> songListSongs = songListSongService.selectAllSongByPlayListId(playList_id);
        jsonObject.put(Consts.PLAYLISTSONG, songListSongs);
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "查询成功");
        return jsonObject;
    }

    //    向歌单中添加歌曲
//    添加歌曲
    @RequestMapping(value = "/playList_song/add", method = RequestMethod.POST)
    public Object addSong(@RequestBody String songMsg) {
        JSONObject jsonObject = new JSONObject();
        SongListSong songListSong = JSONObject.parseObject(songMsg, SongListSong.class);
        System.out.println(songListSong);
        //      根据参数歌曲名在歌曲库中进行查询是否存在
        Song getSong1 = songListSongService.selectOneByBig(songListSong.getName());
        //      根据参数歌曲名和歌单id在歌单库中进行查询是否存在
        SongListSong getSong2 = songListSongService.selectOneBySmall(songListSong.getName(), songListSong.getSonglistId());
        System.out.println(getSong1+"lalalala"+getSong2);
        //        在歌曲库中存在且在歌单库中不存的时候才进行添加
        if (getSong1 != null && getSong2 == null) {
            SongListSong songListSong1 = new SongListSong();
            songListSong1.setName(songListSong.getName());
            songListSong1.setPicture(getSong1.getPicture());
            songListSong1.setStyle(getSong1.getStyle());
            songListSong1.setAlbum(getSong1.getAlbum());
            songListSong1.setLyric(getSong1.getLyric());
            songListSong1.setUrl(getSong1.getUrl());
            songListSong1.setSongId(getSong1.getId());
            songListSong1.setSonglistId(songListSong.getSonglistId());
            songListSong1.setSingerName(getSong1.getSingerName());

            songListSongService.addSong(songListSong1);
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "添加成功");
            return jsonObject;
        } else if (getSong1 == null) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "该歌曲不存在");
            return jsonObject;
        } else if (getSong2 != null) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "该歌曲已存在");
            return jsonObject;
        }
        return null;
    }

    //    将歌单中的歌曲移除
//    删除歌单
    @RequestMapping(value = "/playList_song/remove/{id}", method = RequestMethod.POST)
    public Object removeSong(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        boolean flag = songListSongService.removeSong(id);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "删除成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "删除失败");
            return jsonObject;
        }
    }
}
