package com.imusic.mymusic.service;

import com.imusic.mymusic.pojo.Collect;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Tony贾维斯
* @description 针对表【collect】的数据库操作Service
* @createDate 2022-07-14 15:21:01
*/
public interface CollectService extends IService<Collect> {
//    添加收藏
    public boolean addCollention(Collect collect);
//    根据用户id和歌曲id查询是否已经收藏
    public boolean selectCollectByUserIdAndSingerId(int userId,int songId);
//      根据用户id和歌曲id取消当前歌曲收藏
    public boolean deleteCollect(int userId,int songId);
}
