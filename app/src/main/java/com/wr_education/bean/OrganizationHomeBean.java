package com.wr_education.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/22.
 */
public class OrganizationHomeBean implements Serializable{

    /**
     * code : 0
     * msg : 数据获取成功
     * data : {"id":"1","name":"维立方科技","type":"2","account":"waycube","pwd":"e10adc3949ba59abbe56e057f20f883e","img":"http://www.weiraoedu.com/public/upload/images/admin/1/201605/26/1464260458.jpg","location":"四川省成都市锦江区一环路东5段-26号-附4号","brief":"有一个梦想","feature":"qqqqqq,cccccc","qualification":"","balance":"600.00","school_name":"成都市第十七中学一","auth":"admin_all","ctime":"0000-00-00 00:00:00","mobile":"13540810167","longitude":"104.074818","latitude":"30.646156","state":"1","groupNum":"2","joined":"1","refuse":""}
     */

    private int code;
    private String msg;
    /**
     * id : 1
     * name : 维立方科技
     * type : 2
     * account : waycube
     * pwd : e10adc3949ba59abbe56e057f20f883e
     * img : http://www.weiraoedu.com/public/upload/images/admin/1/201605/26/1464260458.jpg
     * location : 四川省成都市锦江区一环路东5段-26号-附4号
     * brief : 有一个梦想
     * feature : qqqqqq,cccccc
     * qualification :
     * balance : 600.00
     * school_name : 成都市第十七中学一
     * auth : admin_all
     * ctime : 0000-00-00 00:00:00
     * mobile : 13540810167
     * longitude : 104.074818
     * latitude : 30.646156
     * state : 1
     * groupNum : 2
     * joined : 1
     * refuse :
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String id;
        private String name;
        private String type;
        private String account;
        private String pwd;
        private String img;
        private String location;
        private String brief;
        private String feature;
        private String qualification;
        private String balance;
        private String school_name;
        private String auth;
        private String ctime;
        private String mobile;
        private String longitude;
        private String latitude;
        private String state;
        private String groupNum;
        private String joined;
        private String refuse;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getFeature() {
            return feature;
        }

        public void setFeature(String feature) {
            this.feature = feature;
        }

        public String getQualification() {
            return qualification;
        }

        public void setQualification(String qualification) {
            this.qualification = qualification;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(String groupNum) {
            this.groupNum = groupNum;
        }

        public String getJoined() {
            return joined;
        }

        public void setJoined(String joined) {
            this.joined = joined;
        }

        public String getRefuse() {
            return refuse;
        }

        public void setRefuse(String refuse) {
            this.refuse = refuse;
        }
    }
}
