package com.imusic.mymusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imusic.mymusic.mapper.AdminMapper;
import com.imusic.mymusic.pojo.Admin;
import com.imusic.mymusic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tony贾维斯
 * @description 针对表【admin】的数据库操作Service实现
 * @createDate 2022-07-14 15:21:01
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
        implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    //    验证密码
    public boolean verifyPassword(String username, String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "password");
        Admin admins = adminMapper.selectOne(queryWrapper);
        if (admins.getName().equals(username) && admins.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}




