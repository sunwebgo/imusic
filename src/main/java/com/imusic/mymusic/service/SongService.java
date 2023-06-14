package com.imusic.mymusic.service;

import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.pojo.Song;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【song】的数据库操作Service
* @createDate 2022-07-14 15:21:01
*/


public interface SongService extends IService<Song> {
    //    添加歌曲
    public boolean addSong(Song Song);
    //    修改歌曲信息
    public boolean updateSong(Song Song);
    //    删除歌曲
    public boolean deleteSong(int id);
    //    根据id查询所有歌曲
    public List<Song> selectAllSong();
    //    根据歌手id查询所有歌曲
    public List<Song> selectAllSongBySingerId(int id);
    //    根据歌曲名字查询歌曲
    public Song selectOneByName(String name);
//    根据歌曲名模糊查询歌曲
    public List<Song> selectSongDim(String name);
}
