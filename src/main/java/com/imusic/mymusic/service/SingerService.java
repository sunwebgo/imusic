package com.imusic.mymusic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imusic.mymusic.pojo.Singer;

import java.util.List;

/**
 * @author Tony贾维斯
 * @description 针对表【singer】的数据库操作Service
 * @createDate 2022-07-14 15:21:01
 */
public interface SingerService extends IService<Singer> {
    //    添加歌手
    public boolean addSinger(Singer singer);

    //    修改歌手信息
    public boolean updateSinger(Singer singer);

    //    删除歌手
    public int deleteSinger(int id);

    //    根据name查询歌手
    public Singer selectOne(String name);

    //    查询所有歌手
    public List<Singer> selectAllSinger();

    //     根据歌手名模糊查询歌手
    public List<Singer> selectSingerDim(String name);
//    根据性别查询歌手
    public List<Singer> selectSingerByGender(String gender);
}
