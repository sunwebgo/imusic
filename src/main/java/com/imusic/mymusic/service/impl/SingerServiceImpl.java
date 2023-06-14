package com.imusic.mymusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imusic.mymusic.pojo.Singer;
import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.pojo.SongList;
import com.imusic.mymusic.service.SingerService;
import com.imusic.mymusic.mapper.SingerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【singer】的数据库操作Service实现
* @createDate 2022-07-14 15:21:01
*/
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer>
    implements SingerService{

    @Autowired
    SingerMapper singerMapper;

//    添加歌手
    public boolean addSinger(Singer singer) {
        int number = singerMapper.insert(singer);
        if (number != 0){
            return true;
        }else{
            return false;
        }
    }

//    更改歌手信息
    public boolean updateSinger(Singer singer) {
        UpdateWrapper<Singer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id",singer.getId());
        if (singer.getName() != null){
            updateWrapper.set("name",singer.getName());
        }
        if (singer.getGender() != null){
            updateWrapper.set("gender",singer.getGender());
        }
        if (singer.getBirth() != null){
            updateWrapper.set("birth",singer.getBirth());
        }
        if (singer.getPicture() != null){
            updateWrapper.set("picture",singer.getPicture());
        }
        if (singer.getLocation() != null){
            updateWrapper.set("location",singer.getLocation());
        }
        if (singer.getIntroduction() != null){
            updateWrapper.set("introduction",singer.getIntroduction());
        }
        int number = singerMapper.update(null, updateWrapper);
        System.out.println("Service层图片更新情况："+number);
        if (number != 0){
            return true;
        }else{
            return false;
        }

    }

//   删除歌手
    public int deleteSinger(int id) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper();
        queryWrapper.in("id",id);
        int flag = singerMapper.delete(queryWrapper);
        return flag;
    }

    //    根据name查询歌手
    public Singer selectOne(String name) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper();
        queryWrapper.in("name",name);
        Singer singer = singerMapper.selectOne(queryWrapper);
        return singer;
    }

    //    查询所有歌手
    public List<Singer> selectAllSinger() {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        List<Singer> singers = singerMapper.selectList(queryWrapper);
        return singers;
    }

    //     根据歌手名模糊查询歌手
    public List<Singer> selectSingerDim(String name) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        List<Singer> singers = singerMapper.selectList(queryWrapper);
        return singers;
    }

    //    根据性别查询歌手
    public List<Singer> selectSingerByGender(String gender) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper();
        queryWrapper.in("gender",gender);
        List<Singer> singers = singerMapper.selectList(queryWrapper);
        return singers;
    }

}




