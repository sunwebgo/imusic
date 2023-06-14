package com.imusic.mymusic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imusic.mymusic.pojo.Comment;
import com.imusic.mymusic.pojo.Singer;
import com.imusic.mymusic.pojo.Song;
import com.imusic.mymusic.pojo.User;

import java.util.List;

/**
 * @author Tony贾维斯
 * @description 针对表【user】的数据库操作Service
 * @createDate 2022-07-14 15:21:01
 */
public interface UserService extends IService<User> {
    //    查询所有用户
    public List<User> selectAllUser();

    //    根据用户名查询用户
    public Boolean selectOne(String username);

    //    根据用户id查询用户
    public User selectOneById(Integer userId);

//    根据id和密码查询用户
    public User selectByIdAndPwd(int id,String password);

    //    添加用户
    public boolean addUser(User user);

    //    用户登录
    public User verifyPassword(String username, String password);


    //    根据当前用户id查询收藏表里面的音乐
    public List<Song> selectCollectByUserId(int id);

    //    更改用户信息
    public boolean updateUser(User user);

//    根据id删除用户
    public boolean deleteUser(int id);

}
