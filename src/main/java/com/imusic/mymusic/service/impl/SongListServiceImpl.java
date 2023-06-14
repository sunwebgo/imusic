package com.imusic.mymusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imusic.mymusic.pojo.SongList;
import com.imusic.mymusic.service.SongListService;
import com.imusic.mymusic.mapper.SongListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【songlist】的数据库操作Service实现
* @createDate 2022-07-14 15:21:01
*/
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList>
    implements SongListService {

    @Autowired
    SongListMapper songListMapper;

    //    查询所有歌单
    public List<SongList> selectAllSongList() {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        List<SongList> songLists = songListMapper.selectList(queryWrapper);
        return songLists;
    }

    //    添加歌单
    public boolean addSongList(SongList songList) {
        int number = songListMapper.insert(songList);
        if (number != 0){
            return true;
        }else{
            return false;
        }
    }

    //    更改歌单信息
    public boolean updateSongList(SongList songList) {
        UpdateWrapper<SongList> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id",songList.getId());
        if (songList.getTitle() != null){
            updateWrapper.set("title",songList.getTitle());
        }
        if (songList.getStyle() != null){
            updateWrapper.set("style",songList.getStyle());
        }
        if (songList.getPicture() != null){
            updateWrapper.set("picture",songList.getPicture());
        }
        if (songList.getIntroduction() != null){
            updateWrapper.set("introduction",songList.getIntroduction());
        }
        int number = songListMapper.update(null, updateWrapper);
        if (number != 0){
            return true;
        }else{
            return false;
        }

    }

    //   删除歌单
    public int deleteSongList(int id) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper();
        queryWrapper.in("id",id);
        int flag = songListMapper.delete(queryWrapper);
        return flag;
    }

    //    根据title查询歌单
    public SongList selectOne(String title) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper();
        queryWrapper.in("title",title);
        SongList songList = songListMapper.selectOne(queryWrapper);
        return songList;
    }

    //    根据风格查询歌单
    public  List<SongList> selectByStyle(String style) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper();
        queryWrapper.in("style",style);
        List<SongList> songLists = songListMapper.selectList(queryWrapper);
        return songLists;
    }

}




