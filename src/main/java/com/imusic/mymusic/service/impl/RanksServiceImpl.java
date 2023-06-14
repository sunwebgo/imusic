package com.imusic.mymusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imusic.mymusic.pojo.Ranks;
import com.imusic.mymusic.service.RanksService;
import com.imusic.mymusic.mapper.RanksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author Tony贾维斯
* @description 针对表【ranks】的数据库操作Service实现
* @createDate 2022-07-14 15:21:01
*/
@Service
public class RanksServiceImpl extends ServiceImpl<RanksMapper, Ranks>
    implements RanksService{

    @Autowired
    RanksMapper ranksMapper;

//添加评价
    public int addRank(Ranks ranks) {
        int number = 0;
        try {
            number = ranksMapper.insert(ranks);
        } catch (Exception e) {
        }

        if (number != 0){
            return 1;
        }else{
            return 0;
        }
    }


}




