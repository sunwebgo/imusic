package com.imusic.mymusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imusic.mymusic.mapper.CollectMapper;
import com.imusic.mymusic.mapper.SongMapper;
import com.imusic.mymusic.pojo.*;
import com.imusic.mymusic.service.UserService;
import com.imusic.mymusic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Tony贾维斯
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-07-14 15:21:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    SongMapper songMapper;

    //    查询所有用户
    public List<User> selectAllUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        List<User> users = userMapper.selectList(queryWrapper);
        return users;
    }

    //    根据用户名查询用户
    public Boolean selectOne(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null){
            return false;
        }else{
            return true;
        }
    }

    //    根据用户id查询用户
    public User selectOneById(Integer userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",userId);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null){
            return user;
        }else{
            return null;
        }
    }

    //    根据id和密码查询用户
    public User selectByIdAndPwd(int id, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",id);
        queryWrapper.in("password",password);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    //    添加用户
    public boolean addUser(User user) {
        int number = userMapper.insert(user);
        if (number != 0){
            return true;
        }else{
            return false;
        }
    }

    //    用户登录
    public User verifyPassword(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("username",username);
        queryWrapper.in("password",password);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null){
            return user;
        }else{
            return null;
        }
    }

    //    将歌单评论表中的用户信息进行去重操作
    public List<User> distinctUser(Comment comment) {

        return null;
    }

    //    根据当前用户id查询收藏表里面的音乐
    public List<Song> selectCollectByUserId(int id) {
//        首先根据用户id查询到歌曲id
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        List<Song> songs = new ArrayList<>();
        queryWrapper.in("user_id",id);
        List<Collect> collects = collectMapper.selectList(queryWrapper);
        for (Collect item:collects){
            Song song = songMapper.selectById(item.getSongId());
            songs.add(song);
        }
        return songs;
    }

    //    更改用户信息
    public boolean updateUser(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id",user.getId());
        if (user.getUsername() != null){
            updateWrapper.set("username",user.getUsername());
        }
        if (user.getPassword() != null){
            updateWrapper.set("password",user.getPassword());
        }
        if (user.getGender() != null){
            updateWrapper.set("gender",user.getGender());
        }
        if (user.getBirth() != null){
            updateWrapper.set("birth",user.getBirth());
        }
        if (user.getPicture() != null){
            updateWrapper.set("picture",user.getPicture());
        }
        if (user.getPhone() != null){
            updateWrapper.set("phone",user.getPhone());
        }
        if (user.getEmail() != null){
            updateWrapper.set("email",user.getEmail());
        }
        int number = userMapper.update(null, updateWrapper);
        if (number != 0){
            return true;
        }else{
            return false;
        }

    }

    //    根据id删除用户
    public boolean deleteUser(int id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",id);
        int delete = userMapper.delete(queryWrapper);
        if (delete > 0){
            return true;
        }else{
            return false;
        }
    }
}




