package com.imusic.mymusic;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.imusic.mymusic.mapper.*;
import com.imusic.mymusic.pojo.*;
import com.imusic.mymusic.service.RanksService;
import com.imusic.mymusic.service.SingerService;
import com.imusic.mymusic.utils.Consts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootTest
class MyMusicApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    SingerMapper singerMapper;

    @Autowired
    SingerService singerService;

    @Autowired
    RanksMapper ranksMapper;

    @Autowired
    RanksService ranksService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    SongMapper songMapper;

    @Test
    public void verifyPassword() {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "password");
        Admin admins = adminMapper.selectOne(queryWrapper);
        if (admins.getName().equals("admin") && admins.getPassword().equals("123")) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    @Test
    public void updateSinger() {
        UpdateWrapper<Singer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", 1);
        updateWrapper.set("introduction", "叶天帝啦啦啦");
        int update = singerMapper.update(null, updateWrapper);
        System.out.println(update);

    }


    @Test
    public void selectOne() {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper();
        queryWrapper.in("id", 1);
        Singer singer = singerMapper.selectOne(queryWrapper);
        System.out.println(singer);
    }

    @Test
    public void selectAllSinger() {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        List<Singer> singers = singerMapper.selectList(queryWrapper);
        System.out.println(singers);
    }

    @Test
    public void selectAllByName() {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper();
        queryWrapper.like("name", "叶");
        Singer singer = singerMapper.selectOne(queryWrapper);
        System.out.println(singer);
    }

    @Test
    public void selectSingerByGender() {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("gender", "男");
        List<Singer> singers = singerMapper.selectList(queryWrapper);
        System.out.println(singers);
    }

    @Test
    public void selectOne2() {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper();
        queryWrapper.in("id", 3);
        Singer singer = singerMapper.selectOne(queryWrapper);
        System.out.println(singer);
    }

    @Test
    public void selectAllSinger2() {
        JSONObject jsonObject = new JSONObject();
        List<Singer> singers = singerService.selectAllSinger();
        jsonObject.put(Consts.SINGERLIST, singers);
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "查询成功");
        System.out.println(jsonObject);
    }

    @Test
    public void test() {
        Properties properties = System.getProperties();
        for (String i : properties.stringPropertyNames()) {
            System.out.println(i + "=" + properties.getProperty(i));
        }
    }

    @Test
    public void testSum() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.select("IFNULL(sum(score),0) as totalScore")
                .eq("playlist_id", 1);
        Map<String,Object> map = ranksService.getMap(queryWrapper);
        System.out.println(map.get("totalScore"));
    }
    @Test
    public void testCount() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.select("IFNULL(count(score),0) as totalCount")
                .eq("playlist_id", 1);
        Map<String,Object> map = ranksService.getMap(queryWrapper);
        System.out.println(map.get("totalCount"));
    }

    @Test
    public void selectCollectByUserId() {
//        首先根据用户id查询到歌曲id
        QueryWrapper<Collect> queryWrapper1 = new QueryWrapper();
        QueryWrapper<Collect> queryWrapper2 = new QueryWrapper();
        List<Song> songs = new ArrayList<>();
        queryWrapper1.in("user_id",5);
        List<Collect> collects = collectMapper.selectList(queryWrapper1);
        for (Collect item:collects){
            Song song = songMapper.selectById(item.getSongId());
            songs.add(song);
        }
        System.out.println(songs);

    }
}
