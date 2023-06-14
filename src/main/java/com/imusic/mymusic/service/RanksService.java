package com.imusic.mymusic.service;

import com.imusic.mymusic.pojo.Ranks;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Repository;

/**
* @author Tony贾维斯
* @description 针对表【ranks】的数据库操作Service
* @createDate 2022-07-14 15:21:01
*/
@Repository
public interface RanksService extends IService<Ranks> {
//   增加评价
    public int addRank(Ranks ranks);
}
