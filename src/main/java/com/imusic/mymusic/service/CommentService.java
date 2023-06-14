package com.imusic.mymusic.service;

import com.imusic.mymusic.mapper.CommentMapper;
import com.imusic.mymusic.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【comment】的数据库操作Service
* @createDate 2022-07-14 15:21:01
*/
@Repository
public interface CommentService extends IService<Comment> {
//    添加评论
    public boolean addComment(Comment comment);
//    根据歌单id查询评论
    public List<Comment> selectCommentByPlayListId(int playListId);
//    增加点赞数
    public void addNumber(int id,int number);
}
