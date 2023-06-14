package com.imusic.mymusic.mapper;

import com.imusic.mymusic.pojo.Singer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【singer】的数据库操作Mapper
* @createDate 2022-07-14 15:21:01
* @Entity com.imusic.mymusic.pojo.Singer
*/

@Repository
public interface SingerMapper extends BaseMapper<Singer> {
}




