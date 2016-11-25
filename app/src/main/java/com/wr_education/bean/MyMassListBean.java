package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/14.
 */
public class MyMassListBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * data : [{"id":"6","name":"包子社","brief":"一起来吃包子吧","admin":"2,1","create_time":"2016-04-15 14:43:57","course_plan":null,"score_items":null,"tutor":null,"img":"http://192.168.10.134/weirao/public/upload/images/admin/1/201604/15/1460702634.png","agency":"1","agency_name":"维立方科技","admins":""}]
     */

    private int code;
    private String msg;
    /**
     * id : 6
     * name : 包子社
     * brief : 一起来吃包子吧
     * admin : 2,1
     * create_time : 2016-04-15 14:43:57
     * course_plan : null
     * score_items : null
     * tutor : null
     * img : http://192.168.10.134/weirao/public/upload/images/admin/1/201604/15/1460702634.png
     * agency : 1
     * agency_name : 维立方科技
     * admins :
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
        private String name;
        private String brief;
        private String admin;
        private String create_time;
        private String course_plan;
        private String score_items;
        private String tutor;
        private String img;
        private String agency;
        private String agency_name;
        private String admins;

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

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCourse_plan() {
            return course_plan;
        }

        public void setCourse_plan(String course_plan) {
            this.course_plan = course_plan;
        }

        public String getScore_items() {
            return score_items;
        }

        public void setScore_items(String score_items) {
            this.score_items = score_items;
        }

        public String getTutor() {
            return tutor;
        }

        public void setTutor(String tutor) {
            this.tutor = tutor;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getAgency() {
            return agency;
        }

        public void setAgency(String agency) {
            this.agency = agency;
        }

        public String getAgency_name() {
            return agency_name;
        }

        public void setAgency_name(String agency_name) {
            this.agency_name = agency_name;
        }

        public String getAdmins() {
            return admins;
        }

        public void setAdmins(String admins) {
            this.admins = admins;
        }
    }
}
