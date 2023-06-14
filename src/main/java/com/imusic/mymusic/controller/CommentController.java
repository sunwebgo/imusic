package com.imusic.mymusic.controller;

import com.alibaba.fastjson.JSONObject;
import com.imusic.mymusic.pojo.Comment;
import com.imusic.mymusic.service.CommentService;
import com.imusic.mymusic.service.UserService;
import com.imusic.mymusic.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    //    添加评论
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public Object addComment(@RequestBody String commentMsg) {
        Comment comment = JSONObject.parseObject(commentMsg, Comment.class);
        comment.setNumber(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comment.setTime(formatter.format(new Date()));
        JSONObject jsonObject = new JSONObject();
        boolean flag = commentService.addComment(comment);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "评论成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "评论失败");
            return jsonObject;
        }
    }

    //    根据歌单id查询歌单内的所有评价
    @RequestMapping(value = "/comment/selectByPlayListId/{playListId}", method = RequestMethod.GET)
    public Object selectCommentByPlayListId(@PathVariable("playListId") int playListId) {
        List<Comment> comments = commentService.selectCommentByPlayListId(playListId);
        JSONObject jsonObject = new JSONObject();
        if (comments != null) {
            jsonObject.put(Consts.COMMENTLIST, comments);
            jsonObject.put(Consts.CODE, 1);
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 2);
            jsonObject.put(Consts.MESSAGE, "暂无评论");
            return jsonObject;
        }
    }

    //    更新点赞数
    @RequestMapping(value = "/comment/update/{id}/{number}", method = RequestMethod.POST)
    public Object selectCommentByPlayListId(@PathVariable("id") Integer id,@PathVariable("number") int number) {
        commentService.addNumber(id,number);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.CODE, 1);
        return jsonObject;
    }

}
