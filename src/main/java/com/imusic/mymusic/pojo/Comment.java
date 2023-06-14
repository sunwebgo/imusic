package com.imusic.mymusic.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName comment
 */
@Data
@TableName(value ="comment")
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
//    歌单id
    private Integer playlistId;

    /**
     * 
     */
//    评论内容
    private String content;

    /**
     * 
     */
//    点赞数
    private Integer number;

//    评论事件
    private String time;

}