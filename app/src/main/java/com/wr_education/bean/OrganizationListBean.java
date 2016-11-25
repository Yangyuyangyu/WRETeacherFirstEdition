package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/13.
 */
public class OrganizationListBean {

    /**
     * code : 0
     * msg : 获取数据成功
     * data : [{"id":"10","user_id":"22","group_id":"2","type":"2","status":"1","agencyInfo":{"id":"2","name":"围绕教育机构","type":"2","account":"test","pwd":"e10adc3949ba59abbe56e057f20f883e","img":null,"location":null,"brief":null,"feature":null,"qualification":null,"balance":null,"school_name":null,"auth":"admin_all","ctime":null,"mobile":null,"teacherNum":"1"}},{"id":"9","user_id":"22","group_id":"1","type":"2","status":"1","agencyInfo":{"id":"1","name":"维立方科技","type":"1","account":"waycube","pwd":"e10adc3949ba59abbe56e057f20f883e","img":"http://192.168.10.134/weirao/public/upload/images/admin/1/201604/15/1460706336.png","location":null,"brief":"地方大幅度发","feature":"新的，恩","qualification":null,"balance":null,"school_name":"成都市第十七中学","auth":"admin_all","ctime":null,"mobile":null,"teacherNum":"2"}}]
     */

    private int code;
    private String msg;
    /**
     * id : 10
     * user_id : 22
     * group_id : 2
     * type : 2
     * status : 1
     * agencyInfo : {"id":"2","name":"围绕教育机构","type":"2","account":"test","pwd":"e10adc3949ba59abbe56e057f20f883e","img":null,"location":null,"brief":null,"feature":null,"qualification":null,"balance":null,"school_name":null,"auth":"admin_all","ctime":null,"mobile":null,"teacherNum":"1"}
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
        private String user_id;
        private String group_id;
        private String type;
        private String status;
        /**
         * id : 2
         * name : 围绕教育机构
         * type : 2
         * account : test
         * pwd : e10adc3949ba59abbe56e057f20f883e
         * img : null
         * location : null
         * brief : null
         * feature : null
         * qualification : null
         * balance : null
         * school_name : null
         * auth : admin_all
         * ctime : null
         * mobile : null
         * teacherNum : 1
         */

        private AgencyInfoBean agencyInfo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public AgencyInfoBean getAgencyInfo() {
            return agencyInfo;
        }

        public void setAgencyInfo(AgencyInfoBean agencyInfo) {
            this.agencyInfo = agencyInfo;
        }

        public static class AgencyInfoBean {
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
            private String teacherNum;

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

            public String getTeacherNum() {
                return teacherNum;
            }

            public void setTeacherNum(String teacherNum) {
                this.teacherNum = teacherNum;
            }
        }
    }
}
