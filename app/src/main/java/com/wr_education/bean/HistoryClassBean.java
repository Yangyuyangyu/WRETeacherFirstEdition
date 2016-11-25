package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/10.
 */
public class HistoryClassBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * data : [{"id":"39","cid":"14","tid":"22","sid":"12","status":"5","date":"2016-05-10","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"12","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-10 15:00~16:00"}]
     */

    private int code;
    private String msg;
    /**
     * id : 39
     * cid : 14
     * tid : 22
     * sid : 12
     * status : 5
     * date : 2016-05-10
     * start : 15:00:00
     * place : 成都市青羊区上南大街2号
     * present : 12
     * absent :
     * confirm : 0
     * longitude :
     * latitude :
     * name : 数学
     * img : http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg
     * type : 1
     * class_time : 2016-05-10 15:00~16:00
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String cid;
        private String tid;
        private String sid;
        private String status;
        private String date;
        private String start;
        private String place;
        private String present;
        private String absent;
        private String confirm;
        private String longitude;
        private String latitude;
        private String name;
        private String img;
        private String type;
        private String class_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getPresent() {
            return present;
        }

        public void setPresent(String present) {
            this.present = present;
        }

        public String getAbsent() {
            return absent;
        }

        public void setAbsent(String absent) {
            this.absent = absent;
        }

        public String getConfirm() {
            return confirm;
        }

        public void setConfirm(String confirm) {
            this.confirm = confirm;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClass_time() {
            return class_time;
        }

        public void setClass_time(String class_time) {
            this.class_time = class_time;
        }
    }
}
