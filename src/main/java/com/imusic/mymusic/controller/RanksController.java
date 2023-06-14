package com.imusic.mymusic.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imusic.mymusic.pojo.Ranks;
import com.imusic.mymusic.service.RanksService;
import com.imusic.mymusic.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RanksController {
    @Autowired
    RanksService ranksService;

    //   增加评价
    @RequestMapping(value = "/ranks/add/{playListId}/{userId}/{score}", method = RequestMethod.POST)
    public Object addRank(@PathVariable("playListId") int playListId, @PathVariable("userId") int userId, @PathVariable("score") int score) {
        Ranks ranks = new Ranks();
        ranks.setPlaylistId(playListId);
        ranks.setUserId(userId);
        ranks.setScore(score);
        int i = ranksService.addRank(ranks);
        JSONObject jsonObject = new JSONObject();
        if (i > 0) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "评价成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "评价失败");
            return jsonObject;
        }
    }

    //    计算歌单平均分
    @RequestMapping(value = "/ranks/average/{playListId}", method = RequestMethod.GET)
    public Object getAverage(@PathVariable("playListId") int playListId) {

        //   获得歌单总分
        QueryWrapper queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.select("IFNULL(sum(score),0) as totalScore")
                .eq("playlist_id", playListId);
        Map<String,Object> map1 = ranksService.getMap(queryWrapper1);
        Object object1 = map1.get("totalScore");
        int totalScore = Integer.parseInt(String.valueOf(object1));
        System.out.println(totalScore);

//    获取参与评价的总人数
        QueryWrapper queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.select("IFNULL(count(score),0) as totalCount")
                .eq("playlist_id", playListId);
        Map<String,Object> map2 = ranksService.getMap(queryWrapper2);
        Object object2 = map2.get("totalCount");
        int totalCount = Integer.parseInt(String.valueOf(object2));

        if (totalCount == 0){
            totalCount = 1;
        }

        Integer average = Math.toIntExact(totalScore / totalCount);
        System.out.println(average);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.AVERAGE,average);
        jsonObject.put(Consts.CODE, 1);
        return jsonObject;
    }
}
