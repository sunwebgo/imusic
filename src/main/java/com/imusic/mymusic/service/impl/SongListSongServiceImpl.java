package com.imusic.mymusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imusic.mymusic.mapper.SongMapper;
import com.imusic.mymusic.pojo.Singer;
import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.pojo.SongListSong;
import com.imusic.mymusic.service.SongListSongService;
import com.imusic.mymusic.mapper.SongListSongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【playlist_song】的数据库操作Service实现
* @createDate 2022-07-14 15:21:01
*/
@Service
public class SongListSongServiceImpl extends ServiceImpl<SongListSongMapper, SongListSong>
    implements SongListSongService {

    @Autowired
    SongListSongMapper songListSongMapper;

    @Autowired
    SongMapper songMapper;

    //    根据歌单id查询歌单内的所有歌曲
    public List<SongListSong> selectAllSongByPlayListId(int playList_id) {
        QueryWrapper<SongListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("songList_id",playList_id);
        queryWrapper.select("*");
        List<SongListSong> songListSongs = songListSongMapper.selectList(queryWrapper);
        return songListSongs;
    }

    // 根据歌名在歌曲库中查询歌曲
    public Song selectOneByBig(String name) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper();
        queryWrapper.in("name",name);
        Song song = songMapper.selectOne(queryWrapper);
        return song;
    }

    // 根据歌名和歌单id在歌单库中查询歌曲
    public SongListSong selectOneBySmall(String name,int songListId) {
        SongListSong song = null;
        try {
            QueryWrapper<SongListSong> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("name",name);
            queryWrapper.in("songlist_id",songListId);
            song = songListSongMapper.selectOne(queryWrapper);
        } catch (Exception e) {
        }
        return song;
    }

    //    在歌单中添加歌曲
    public void addSong(SongListSong songListSong) {
        songListSongMapper.insert(songListSong);
    }

    //    根据当前歌曲id移除此歌曲
    public boolean removeSong(int id) {
        QueryWrapper<SongListSong> queryWrapper = new QueryWrapper();
        queryWrapper.in("id",id);
        int number = songListSongMapper.delete(queryWrapper);
        if (number > 0){
            return true;
        }else{
            return false;
        }
    }

    //    根据song_id删除对应的歌曲（多表删除）
    public boolean deleteSong(int songId) {
        QueryWrapper<SongListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("song_id",songId);
        int number = songListSongMapper.delete(queryWrapper);
        if (number > 0){
            return true;
        }else{
            return false;
        }
    }


}




