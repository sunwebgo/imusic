package com.imusic.mymusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imusic.mymusic.pojo.Comment;
import com.imusic.mymusic.service.CommentService;
import com.imusic.mymusic.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2022-07-14 15:21:01
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    CommentMapper commentMapper;

//  添加评论
    public boolean addComment(Comment comment) {
        int i = commentMapper.insert(comment);
        if (i != 0){
            return true;
        }else{
            return false;
        }
    }

    //    根据歌单id查询评论
    public List<Comment> selectCommentByPlayListId(int playListId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("playlist_id",playListId);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return comments;
    }

    //    增加点赞数
    public void addNumber(int id,int number) {
        UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id",id);
        updateWrapper.set("number",number);
        commentMapper.update(null, updateWrapper);
    }
}




