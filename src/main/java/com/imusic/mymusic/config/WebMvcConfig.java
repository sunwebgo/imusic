package com.imusic.mymusic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /*
     * 解决跨域问题
     * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
                .allowedOrigins("http://43.143.117.57")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    /* 配置本地资源映射路径 addResourceHandlers
     *  addResourceHandler()添加的是访问路径
     *  addResourceLocations()添加的是映射后的真实路径
     *  user.dir=D:\Idea_project\myMusic(获取到当前工程目录)
     *  file.separator=\ (文件分隔符)
     *  及静态资源映射路径为："file:D:\Idea_project\myMusic\img\singer\"
     * */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


//        对歌手默认图片定位
//        addResourceHandler()添加的是访问路径 addResourceLocations()添加的是映射后的真实路径
        registry.addResourceHandler("/img/singer/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "singer" + System.getProperty("file.separator")
        );
//        对歌曲默认图片定位
        registry.addResourceHandler("/img/song/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "song" + System.getProperty("file.separator")
        );

        //        对歌单默认图片定位
        registry.addResourceHandler("/img/songList/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "songList" + System.getProperty("file.separator")
        );

//        歌曲地址
        registry.addResourceHandler("/song/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "song"
                        + System.getProperty("file.separator")
        );
        //        用户头像地址地址
        registry.addResourceHandler("/img/user/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "user" + System.getProperty("file.separator")
        );
    }
}
