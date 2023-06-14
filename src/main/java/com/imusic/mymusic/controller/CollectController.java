package com.imusic.mymusic.controller;

import com.alibaba.fastjson.JSONObject;
import com.imusic.mymusic.pojo.Collect;
import com.imusic.mymusic.service.CollectService;
import com.imusic.mymusic.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollectController {

    @Autowired
    CollectService collectService;

    //    添加收藏
    @RequestMapping(value = "/collection/add", method = RequestMethod.POST)
    public Object addCollection(@RequestBody String collectMsg) {
        JSONObject jsonObject = new JSONObject();
        Collect collect = JSONObject.parseObject(collectMsg, Collect.class);

        if (collect.getSongId() == null) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MESSAGE, "请先选择歌曲");
            return jsonObject;
        } else {
            //        添加之前首先查询一下是否已经收藏
            Boolean flag1 = collectService.selectCollectByUserIdAndSingerId(collect.getUserId(), collect.getSongId());
//        当前用户没有收藏该歌曲
            if (flag1) {
                collectService.addCollention(collect);
                jsonObject.put(Consts.CODE, "1");
                jsonObject.put(Consts.MESSAGE, "收藏成功");
                return jsonObject;
            } else {
                jsonObject.put(Consts.CODE, "0");
                return jsonObject;
            }
        }
    }

    //        根据用户id和歌曲id查询用户是否收藏该歌曲
    @RequestMapping(value = "/collection/select/{userId}/{songId}", method = RequestMethod.GET)
    public Object selectCollection(@PathVariable("userId") int userId,@PathVariable("songId") int songId) {
        JSONObject jsonObject = new JSONObject();
        //查询用户是否已经收藏
        Boolean flag = collectService.selectCollectByUserIdAndSingerId(userId, songId);
//        当前用户没有收藏该歌曲
        if (flag) {
            jsonObject.put(Consts.CODE, "1");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, "0");
            return jsonObject;
        }
    }

    //      根据用户id和歌曲id取消当前歌曲收藏
    @RequestMapping(value = "/collection/delete/{userId}/{songId}", method = RequestMethod.POST)
    public Object deleteCollection(@PathVariable("userId") int userId,@PathVariable("songId") int songId) {
        JSONObject jsonObject = new JSONObject();
        //查询用户是否已经收藏
        Boolean flag = collectService.deleteCollect(userId, songId);
//        当前用户没有收藏该歌曲
        if (flag) {
            jsonObject.put(Consts.CODE, "1");
            jsonObject.put(Consts.MESSAGE, "取消收藏成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MESSAGE, "取消收藏失败");
            return jsonObject;
        }
    }

}
