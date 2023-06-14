package com.imusic.mymusic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.imusic.mymusic.pojo.SongList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【playlist】的数据库操作Service
* @createDate 2022-07-14 15:21:01
*/
public interface SongListService extends IService<SongList> {
    //    查询所有歌单
    public List<SongList> selectAllSongList();
    //    添加歌单
    public boolean addSongList(SongList songList);

    //    更改歌单信息
    public boolean updateSongList(SongList songList);

    //   删除歌单
    public int deleteSongList(int id);

    //    根据title查询歌单
    public SongList selectOne(String title);

//    根据风格查询歌单
    public  List<SongList> selectByStyle(String style);
}
