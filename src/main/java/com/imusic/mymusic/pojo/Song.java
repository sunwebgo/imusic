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
 * @TableName song
 */
@Data
@TableName(value ="song")
public class Song implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer singerId;

    private String singerName;

    private String style;
    /**
     * 
     */
    private String album;

    /**
     * 
     */
    private String picture;

    /**
     * 
     */
    private String lyric;

    /**
     * 
     */
    private String url;


}