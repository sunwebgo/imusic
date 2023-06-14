package com.imusic.mymusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.service.SongService;
import com.imusic.mymusic.mapper.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【song】的数据库操作Service实现
* @createDate 2022-07-14 15:21:01
*/
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song>
    implements SongService{
    
    @Autowired
    SongMapper songMapper;

    //    根据歌手id查询所有歌曲
    public List<Song> selectAllSongBySingerId(int singer_id) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("singer_id",singer_id);
        queryWrapper.select("*");
        List<Song> songs = songMapper.selectList(queryWrapper);
        return songs;
    }

    //    根据歌曲名字查询歌曲
    public Song selectOneByName(String name) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("name",name);
        Song song = songMapper.selectOne(queryWrapper);
        return song;
    }

    //    根据歌曲名模糊查询歌曲
    public List<Song> selectSongDim(String name) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        List<Song> songs = songMapper.selectList(queryWrapper);
        return songs;
    }


    //    添加歌曲
    public boolean addSong(Song song) {
        int number = songMapper.insert(song);
        if (number != 0){
            return true;
        }else{
            return false;
        }
    }

    //    更改歌曲信息
    public boolean updateSong(Song song) {
        UpdateWrapper<Song> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id",song.getId());
        if (song.getName() != null){
            updateWrapper.set("name",song.getName());
        }
        if (song.getStyle() != null){
            updateWrapper.set("style",song.getStyle());
        }
        if (song.getPicture() != null){
            updateWrapper.set("picture",song.getPicture());
        }
        if (song.getAlbum() != null){
            updateWrapper.set("album",song.getAlbum());
        }
        if (song.getLyric() != null){
            updateWrapper.set("lyric",song.getLyric());
        }
        int number = songMapper.update(null, updateWrapper);
        if (number != 0){
            return true;
        }else{
            return false;
        }

    }

    //   删除歌曲
    public boolean deleteSong(int id) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper();
        queryWrapper.in("id",id);
        int number = songMapper.delete(queryWrapper);
        if (number > 0){
            return true;
        }else{
            return false;
        }
    }


    //   查询所有歌曲
    public List<Song> selectAllSong() {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        List<Song> songs = songMapper.selectList(queryWrapper);
        return songs;
    }

}




