package com.jq.learn.yn.review.rxdemo;

import java.util.List;

public class NewsEntity {


    /**
     * error : false
     * results : [{"_id":"5d8d5a1d9d2122688d07a75d","createdAt":"2019-09-27T00:38:53.969Z","desc":"学会这3个Android Studio操作，保你月薪double","publishedAt":"2019-09-27T01:07:19.58Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/flvC4b2kW3731eP9jsI8zA","used":true,"who":"潇湘剑雨"},{"_id":"5d885cea9d21226808a3e98e","createdAt":"2019-09-23T05:49:30.103Z","desc":"基于腾讯x5封源库，提高webView开发效率，大概要节约你百分之六十的时间成本。","publishedAt":"2019-09-23T05:49:44.533Z","source":"web","type":"Android","url":"https://github.com/yangchong211/YCWebView","used":true,"who":"潇湘剑雨"},{"_id":"5d817c759d2122031f053291","createdAt":"2019-09-18T00:38:13.545Z","desc":"加载那么多小姐姐的脉脉，怎么没OOM？","publishedAt":"2019-09-18T01:12:37.561Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/iQvqU5KcNsFWBBMXUqL6Vw","used":true,"who":"潇湘剑雨"},{"_id":"5d731dfb9d2122279ba69b62","createdAt":"2019-09-07T03:03:23.186Z","desc":"一位练习时长两年半的安卓练习生根据鸿神提供的WanAndroid开放Api来制作的产品级App","publishedAt":"2019-09-08T13:43:26.439Z","source":"web","type":"Android","url":"https://github.com/hegaojian/WanAndroid","used":true,"who":"潇湘剑雨"},{"_id":"5d723c469d212227862f8a6c","createdAt":"2019-09-06T11:00:22.700Z","desc":"竖直方向，一次滚动一个页面的封装库。目前支持ViewPager做法，也支持RecyclerView做法\u2026\u2026","publishedAt":"2019-09-06T11:00:38.256Z","source":"web","type":"Android","url":"https://github.com/yangchong211/YCScrollPager","used":true,"who":"潇湘剑雨"},{"_id":"5d6c617c9d21222784aff65b","createdAt":"2019-09-02T00:25:32.782Z","desc":"趣头条大佬带你飞：实现阿里无抖动换肤","publishedAt":"2019-09-03T01:42:23.876Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/mGv_SO5ivqEmHJX9bojT0A","used":true,"who":"潇湘剑雨"},{"_id":"5d6ccadf9d212227862f8a65","createdAt":"2019-09-02T07:55:11.696Z","desc":"Flutter完整开发实战详解 实用技巧与填坑，带来 Flutter 开发过程中的实用技巧，让你继续弯道超车，全篇均为个人的日常干货总结，以实用填坑为主，让你少走弯路狂飙车。","publishedAt":"2019-09-03T01:41:57.750Z","source":"web","type":"Android","url":"https://juejin.im/post/5d6cb579f265da03da24aeb9","used":true,"who":"潇湘剑雨"},{"_id":"5d64e3969d2122031b79811a","createdAt":"2019-08-27T08:02:30.158Z","desc":"一个轻量级、可插拔的Android消息推送框架。一键集成推送（极光推送、友盟推送、华为、小米推送等），提供有效的保活机制，支持推送的拓展，充分解耦推送和业务逻辑","publishedAt":"2019-08-29T01:18:56.54Z","source":"web","type":"Android","url":"https://github.com/xuexiangjys/XPush","used":true,"who":"潇湘剑雨"},{"_id":"5d60d6fd9d2122031b798118","createdAt":"2019-08-24T06:19:41.437Z","desc":"Android博客大汇总，全面系统解析各个知识点，所有博客开源到GitHub！","publishedAt":"2019-08-24T06:20:15.192Z","source":"web","type":"Android","url":"https://juejin.im/post/5d60ad5df265da03c42899f1","used":true,"who":"潇湘剑雨"},{"_id":"5d5f37419d2122774f0cd91c","createdAt":"2019-08-23T00:45:53.276Z","desc":"腾讯Android插件库，技压群雄实现零反射全动态","publishedAt":"2019-08-24T06:20:12.285Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/w_tHM1P-mYIoKs5BAacu3w","used":true,"who":"潇湘剑雨"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5d8d5a1d9d2122688d07a75d
         * createdAt : 2019-09-27T00:38:53.969Z
         * desc : 学会这3个Android Studio操作，保你月薪double
         * publishedAt : 2019-09-27T01:07:19.58Z
         * source : web
         * type : Android
         * url : https://mp.weixin.qq.com/s/flvC4b2kW3731eP9jsI8zA
         * used : true
         * who : 潇湘剑雨
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
