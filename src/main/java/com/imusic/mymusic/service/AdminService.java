package com.imusic.mymusic.service;

import com.imusic.mymusic.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Repository;

/**
* @author Tony贾维斯
* @description 针对表【admin】的数据库操作Service
* @createDate 2022-07-14 15:21:01
*/
@Repository
public interface AdminService extends IService<Admin> {
    //    验证密码
    public boolean verifyPassword(String username,String password);
}
