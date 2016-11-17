package com.example.myapplication.bean;

/**
 * Created by 郭仲凯 on 2016/11/14.
 */

public class TtBean {



            /**
             * id : 18920
             * title : 嘘！关于“蛋蛋”的几个秘密
             * categoryid : 29
             * briefinfo : 蛋蛋是男人真正的命根子。
             * smallimg : http://club.xywy.com/upload/healthimg/20150724164937.jpg
             * imgurl : http://club.xywy.com/upload/healthimg/20150724164936.jpg
             * time : 2016-01-27 10:43:54
             * questionurl :
             * commentcount : 45
             * url : http://3g.club.xywy.com/article.php?type=detail&amp;id=18920
             * istop : 1
             */

            private String title;
            private String categoryid;
            private String briefinfo;
            private String smallimg;
            private String imgurl;
            private String time;
            private String url;
            private String istop;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(String categoryid) {
                this.categoryid = categoryid;
            }

            public String getBriefinfo() {
                return briefinfo;
            }

            public void setBriefinfo(String briefinfo) {
                this.briefinfo = briefinfo;
            }

            public String getSmallimg() {
                return smallimg;
            }

            public void setSmallimg(String smallimg) {
                this.smallimg = smallimg;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getIstop() {
                return istop;
            }

            public void setIstop(String istop) {
                this.istop = istop;
            }

    @Override
    public String toString() {
        return "TtBean{" +
                "briefinfo='" + briefinfo + '\'' +
                ", title='" + title + '\'' +
                ", categoryid='" + categoryid + '\'' +
                ", smallimg='" + smallimg + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                ", commentcount='" + istop + '\'' +
                '}';
    }
}
