package com.dream.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class PartEntity {

    /**
     * error_code : 22000
     * result : {"channel":"热歌","channelid":null,"ch_name":"public_tuijian_rege","artistid":null,"avatar":null,"count":null,"songlist":[{"songid":"14960323","title":"偶阵雨","artist":"梁静茹","thumb":"http://qukufile2.qianqian.com/data2/pic/115646550/115646550.jpg","method":0,"flow":0,"artist_id":"120","all_artist_id":"120","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"13919906","title":"Payphone","artist":"Maroon 5,Wiz Khalifa","thumb":"http://qukufile2.qianqian.com/data2/pic/115614712/115614712.jpg","method":0,"flow":0,"artist_id":"807","all_artist_id":"807,49605","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"309877","title":"忘记你我做不到","artist":"张学友","thumb":"http://qukufile2.qianqian.com/data2/pic/117396191/117396191.jpg","method":0,"flow":0,"artist_id":"23","all_artist_id":"23","resource_type":"0","havehigh":2,"charge":0,"all_rate":"32,64,128,192,256,320,flac"},{"songid":"12337876","title":"Glad You Came","artist":"The Wanted","thumb":"http://qukufile2.qianqian.com/data2/pic/117402233/117402233.jpg","method":0,"flow":0,"artist_id":"48230","all_artist_id":"48230","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"1787453","title":"High歌","artist":"黄龄","thumb":"http://qukufile2.qianqian.com/data2/pic/88349938/88349938.jpg","method":0,"flow":0,"artist_id":"1709","all_artist_id":"1709","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"30827868","title":"变成陌生人","artist":"王心凌","thumb":"http://qukufile2.qianqian.com/data2/pic/123950709/123950709.jpg","method":0,"flow":0,"artist_id":"69","all_artist_id":"69","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320"},{"songid":"23473715","title":"江南Style","artist":"PSY","thumb":"http://qukufile2.qianqian.com/data2/pic/115677649/115677649.jpg","method":0,"flow":0,"artist_id":"43815","all_artist_id":"43815","resource_type":"0","havehigh":2,"charge":0,"all_rate":"64,128,192,256,320"},{"songid":"11779364","title":"孤独患者","artist":"陈奕迅","thumb":"http://qukufile2.qianqian.com/data2/pic/115548107/115548107.jpg","method":0,"flow":0,"artist_id":"87","all_artist_id":"87","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"620023","title":"一路上有你","artist":"张学友","thumb":"http://qukufile2.qianqian.com/data2/pic/115360828/115360828.jpg","method":0,"flow":0,"artist_id":"23","all_artist_id":"23","resource_type":"0","havehigh":2,"charge":0,"all_rate":"64,128,192,256,320,flac"},{"songid":"35366703","title":"其实都没有","artist":"杨宗纬","thumb":"http://qukufile2.qianqian.com/data2/pic/117393074/117393074.jpg","method":0,"flow":0,"artist_id":"1009","all_artist_id":"1009","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":null,"title":null,"artist":null,"thumb":"","method":0,"flow":0,"artist_id":null,"all_artist_id":null,"resource_type":null,"havehigh":0,"charge":0,"all_rate":""}]}
     */

    private int error_code;
    /**
     * channel : 热歌
     * channelid : null
     * ch_name : public_tuijian_rege
     * artistid : null
     * avatar : null
     * count : null
     * songlist : [{"songid":"14960323","title":"偶阵雨","artist":"梁静茹","thumb":"http://qukufile2.qianqian.com/data2/pic/115646550/115646550.jpg","method":0,"flow":0,"artist_id":"120","all_artist_id":"120","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"13919906","title":"Payphone","artist":"Maroon 5,Wiz Khalifa","thumb":"http://qukufile2.qianqian.com/data2/pic/115614712/115614712.jpg","method":0,"flow":0,"artist_id":"807","all_artist_id":"807,49605","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"309877","title":"忘记你我做不到","artist":"张学友","thumb":"http://qukufile2.qianqian.com/data2/pic/117396191/117396191.jpg","method":0,"flow":0,"artist_id":"23","all_artist_id":"23","resource_type":"0","havehigh":2,"charge":0,"all_rate":"32,64,128,192,256,320,flac"},{"songid":"12337876","title":"Glad You Came","artist":"The Wanted","thumb":"http://qukufile2.qianqian.com/data2/pic/117402233/117402233.jpg","method":0,"flow":0,"artist_id":"48230","all_artist_id":"48230","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"1787453","title":"High歌","artist":"黄龄","thumb":"http://qukufile2.qianqian.com/data2/pic/88349938/88349938.jpg","method":0,"flow":0,"artist_id":"1709","all_artist_id":"1709","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"30827868","title":"变成陌生人","artist":"王心凌","thumb":"http://qukufile2.qianqian.com/data2/pic/123950709/123950709.jpg","method":0,"flow":0,"artist_id":"69","all_artist_id":"69","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320"},{"songid":"23473715","title":"江南Style","artist":"PSY","thumb":"http://qukufile2.qianqian.com/data2/pic/115677649/115677649.jpg","method":0,"flow":0,"artist_id":"43815","all_artist_id":"43815","resource_type":"0","havehigh":2,"charge":0,"all_rate":"64,128,192,256,320"},{"songid":"11779364","title":"孤独患者","artist":"陈奕迅","thumb":"http://qukufile2.qianqian.com/data2/pic/115548107/115548107.jpg","method":0,"flow":0,"artist_id":"87","all_artist_id":"87","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":"620023","title":"一路上有你","artist":"张学友","thumb":"http://qukufile2.qianqian.com/data2/pic/115360828/115360828.jpg","method":0,"flow":0,"artist_id":"23","all_artist_id":"23","resource_type":"0","havehigh":2,"charge":0,"all_rate":"64,128,192,256,320,flac"},{"songid":"35366703","title":"其实都没有","artist":"杨宗纬","thumb":"http://qukufile2.qianqian.com/data2/pic/117393074/117393074.jpg","method":0,"flow":0,"artist_id":"1009","all_artist_id":"1009","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320,flac"},{"songid":null,"title":null,"artist":null,"thumb":"","method":0,"flow":0,"artist_id":null,"all_artist_id":null,"resource_type":null,"havehigh":0,"charge":0,"all_rate":""}]
     */

    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String ch_name;
        /**
         * songid : 14960323
         * title : 偶阵雨
         * artist : 梁静茹
         * thumb : http://qukufile2.qianqian.com/data2/pic/115646550/115646550.jpg
         * method : 0
         * flow : 0
         * artist_id : 120
         * all_artist_id : 120
         * resource_type : 0
         * havehigh : 2
         * charge : 0
         * all_rate : 24,64,128,192,256,320,flac
         */

        private List<SonglistBean> songlist;

        public String getCh_name() {
            return ch_name;
        }

        public void setCh_name(String ch_name) {
            this.ch_name = ch_name;
        }

        public List<SonglistBean> getSonglist() {
            return songlist;
        }

        public void setSonglist(List<SonglistBean> songlist) {
            this.songlist = songlist;
        }

        public static class SonglistBean {
            private String songid;
            private String title;
            private String artist;
            private String thumb;

            public String getSongid() {
                return songid;
            }

            public void setSongid(String songid) {
                this.songid = songid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getArtist() {
                return artist;
            }

            public void setArtist(String artist) {
                this.artist = artist;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }
    }
}
