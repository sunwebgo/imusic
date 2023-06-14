package com.imusic.mymusic.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName playlist_song
 */
@Data
@TableName(value ="songlist_song")
public class SongListSong implements Serializable {
    /**
     * 歌单歌曲id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 歌曲id
     */

    private String singerName;

    private Integer songId;
    /**
     * 歌单id
     */
    private Integer songlistId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private String name;

    private String picture;

    private String style;

    private String album;

    private String lyric;

    private String url;


}