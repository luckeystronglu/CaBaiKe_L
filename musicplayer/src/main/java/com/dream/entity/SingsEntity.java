package com.dream.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class SingsEntity {

    /**
     * errorCode : 22000
     * data : {"xcode":"4a72707e21dd04dfcafa86a7c9dabbc3","songList":[{"queryId":"39462038","songId":39462038,"songName":"Gentleman","artistId":"18104396","artistName":"PSY","albumId":39462035,"albumName":"Gentleman","songPicSmall":"http://musicdata.baidu.com/data2/pic/66b572bf2e2f05233085458cb5fcfc79/117450675/117450675.jpg","songPicBig":"http://musicdata.baidu.com/data2/pic/f6f23e00868a23aaa0dc06bc774662b2/117450626/117450626.jpg","songPicRadio":"http://musicdata.baidu.com/data2/pic/50d3a3691152c8ac4e915ee61493e9e0/117450595/117450595.jpg","lrcLink":"http://musicdata.baidu.com/data2/lrc/241668695/241668695.lrc","version":"","copyType":0,"time":194,"linkCode":22000,"songLink":"http://yinyueshiting.baidu.com/data2/music/9f8604b7425db5405b4f765009a98ca4/88329039/39462038223200128.mp3?xcode=4a72707e21dd04df794b86d808232fc4","showLink":"http://yinyueshiting.baidu.com/data2/music/9f8604b7425db5405b4f765009a98ca4/88329039/39462038223200128.mp3?xcode=4a72707e21dd04df794b86d808232fc4","format":"mp3","rate":128,"size":3115388,"relateStatus":"0","resourceType":"0","source":"web"}]}
     */

    private int errorCode;
    /**
     * xcode : 4a72707e21dd04dfcafa86a7c9dabbc3
     * songList : [{"queryId":"39462038","songId":39462038,"songName":"Gentleman","artistId":"18104396","artistName":"PSY","albumId":39462035,"albumName":"Gentleman","songPicSmall":"http://musicdata.baidu.com/data2/pic/66b572bf2e2f05233085458cb5fcfc79/117450675/117450675.jpg","songPicBig":"http://musicdata.baidu.com/data2/pic/f6f23e00868a23aaa0dc06bc774662b2/117450626/117450626.jpg","songPicRadio":"http://musicdata.baidu.com/data2/pic/50d3a3691152c8ac4e915ee61493e9e0/117450595/117450595.jpg","lrcLink":"http://musicdata.baidu.com/data2/lrc/241668695/241668695.lrc","version":"","copyType":0,"time":194,"linkCode":22000,"songLink":"http://yinyueshiting.baidu.com/data2/music/9f8604b7425db5405b4f765009a98ca4/88329039/39462038223200128.mp3?xcode=4a72707e21dd04df794b86d808232fc4","showLink":"http://yinyueshiting.baidu.com/data2/music/9f8604b7425db5405b4f765009a98ca4/88329039/39462038223200128.mp3?xcode=4a72707e21dd04df794b86d808232fc4","format":"mp3","rate":128,"size":3115388,"relateStatus":"0","resourceType":"0","source":"web"}]
     */

    private DataBean data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * queryId : 39462038
         * songId : 39462038
         * songName : Gentleman
         * artistId : 18104396
         * artistName : PSY
         * albumId : 39462035
         * albumName : Gentleman
         * songPicSmall : http://musicdata.baidu.com/data2/pic/66b572bf2e2f05233085458cb5fcfc79/117450675/117450675.jpg
         * songPicBig : http://musicdata.baidu.com/data2/pic/f6f23e00868a23aaa0dc06bc774662b2/117450626/117450626.jpg
         * songPicRadio : http://musicdata.baidu.com/data2/pic/50d3a3691152c8ac4e915ee61493e9e0/117450595/117450595.jpg
         * lrcLink : http://musicdata.baidu.com/data2/lrc/241668695/241668695.lrc
         * version :
         * copyType : 0
         * time : 194
         * linkCode : 22000
         * songLink : http://yinyueshiting.baidu.com/data2/music/9f8604b7425db5405b4f765009a98ca4/88329039/39462038223200128.mp3?xcode=4a72707e21dd04df794b86d808232fc4
         * showLink : http://yinyueshiting.baidu.com/data2/music/9f8604b7425db5405b4f765009a98ca4/88329039/39462038223200128.mp3?xcode=4a72707e21dd04df794b86d808232fc4
         * format : mp3
         * rate : 128
         * size : 3115388
         * relateStatus : 0
         * resourceType : 0
         * source : web
         */

        private List<SongListBean> songList;

        public List<SongListBean> getSongList() {
            return songList;
        }

        public void setSongList(List<SongListBean> songList) {
            this.songList = songList;
        }

        public static class SongListBean {
            private String queryId;
            private String songName;
            private String artistName;
            private String songPicSmall;
            private String songPicBig;
            private String songPicRadio;
            private String lrcLink;
            private String songLink;
            private String format;

            public String getQueryId() {
                return queryId;
            }

            public void setQueryId(String queryId) {
                this.queryId = queryId;
            }

            public String getSongName() {
                return songName;
            }

            public void setSongName(String songName) {
                this.songName = songName;
            }

            public String getArtistName() {
                return artistName;
            }

            public void setArtistName(String artistName) {
                this.artistName = artistName;
            }

            public String getSongPicSmall() {
                return songPicSmall;
            }

            public void setSongPicSmall(String songPicSmall) {
                this.songPicSmall = songPicSmall;
            }

            public String getSongPicBig() {
                return songPicBig;
            }

            public void setSongPicBig(String songPicBig) {
                this.songPicBig = songPicBig;
            }

            public String getSongPicRadio() {
                return songPicRadio;
            }

            public void setSongPicRadio(String songPicRadio) {
                this.songPicRadio = songPicRadio;
            }

            public String getLrcLink() {
                return lrcLink;
            }

            public void setLrcLink(String lrcLink) {
                this.lrcLink = lrcLink;
            }

            public String getSongLink() {
                return songLink;
            }

            public void setSongLink(String songLink) {
                this.songLink = songLink;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }
        }
    }
}
