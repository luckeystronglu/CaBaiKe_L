package com.util;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public interface Constants {
    /**
     * 电台列表
     */
    String URL_DIANTAI="http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getCategoryList&format=json";
    /**
     * 获取某个电台下的歌曲列表
     */
    String URL_PART="http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getChannelSong&format=json&pn=0&rn=10&channelname=%s";
    /**
     * 19. 根据歌曲的id，查找详情数据，包含mp3文件
     */
    String URL_SING="http://ting.baidu.com/data/music/links?songIds=%d";
}
