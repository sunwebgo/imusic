package com.imusic.mymusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imusic.mymusic.pojo.Collect;
import com.imusic.mymusic.service.CollectService;
import com.imusic.mymusic.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【collect】的数据库操作Service实现
* @createDate 2022-07-14 15:21:01
*/
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect>
    implements CollectService{

    @Autowired
    CollectMapper collectMapper;
    //    添加收藏
    public boolean addCollention(Collect collect) {
        int i = collectMapper.insert(collect);
        if (i > 0){
            return true;
        }else{
            return false;
        }
    }

    //    根据用户id和歌曲id查询是否已经收藏
    public boolean selectCollectByUserIdAndSingerId(int userId, int songId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id",userId);
        queryWrapper.in("song_id",songId);
        Collect collect = collectMapper.selectOne(queryWrapper);
        if (collect == null){
            return true;
        }else{
            return false;
        }
    }

    //      根据用户id和歌曲id取消当前歌曲收藏
    public boolean deleteCollect(int userId, int songId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id",userId);
        queryWrapper.in("song_id",songId);
        int i = collectMapper.delete(queryWrapper);
        if (i > 0){
            return true;
        }else{
            return false;
        }
    }
}




