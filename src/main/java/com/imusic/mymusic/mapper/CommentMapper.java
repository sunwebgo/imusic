package com.imusic.mymusic.mapper;

import com.imusic.mymusic.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author Tony贾维斯
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2022-07-14 15:21:01
* @Entity com.imusic.mymusic.pojo.Comment
*/
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}




