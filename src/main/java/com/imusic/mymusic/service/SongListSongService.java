package com.imusic.mymusic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.pojo.SongListSong;

import java.util.List;

/**
 * @author Tony贾维斯
 * @description 针对表【playlist_song】的数据库操作Service
 * @createDate 2022-07-14 15:21:01
 */
public interface SongListSongService extends IService<SongListSong> {
    //    根据歌单id查询歌单内的所有歌曲
    public List<SongListSong> selectAllSongByPlayListId(int playList_id);
    // 根据歌名在歌曲库中查询歌曲
    public Song selectOneByBig(String name);
    // 根据歌名在歌单库中查询歌曲
    public SongListSong selectOneBySmall(String name,int songListId);
//    在歌单中添加歌曲
    public void addSong(SongListSong songListSong);
//    根据当前歌曲id移除此歌曲
    public boolean removeSong(int id);
//    根据song_id删除对应的歌曲（多表删除）
    public boolean deleteSong(int songId);
}
