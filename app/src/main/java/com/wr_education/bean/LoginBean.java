package com.wr_education.bean;

/**
 * Created by Administrator on 2016/4/20.
 */
public class LoginBean {

    /**
     * code : 0
     * msg : 登录成功
     * data : {"id":"22","ask":null,"password":"e10adc3949ba59abbe56e057f20f883e","name":"王浩淼","phone":"15982407336","email":null,"yue":"0.00","state":"2","summary":"简介","authed":"0","edu":"1","sex":"1","birthday":"1992-07-25","edu_school":"小学","edu_exp":"泼冷水救护会一直热群里了厉害了","edu_age":"5","res_share":"成果分享成果分享就是分享，没有说他的一起","class_video":null,"ctime":"2016-04-20 15:36:57","head":"http://192.168.10.142/operate/public/static/common/img/touxiang.jpg","label_admin":"沉默,领域","label":"0","average_price":"0.00"}
     */

    private int code;
    private String msg;
    /**
     * id : 22
     * ask : null
     * password : e10adc3949ba59abbe56e057f20f883e
     * name : 王浩淼
     * phone : 15982407336
     * email : null
     * yue : 0.00
     * state : 2
     * summary : 简介
     * authed : 0
     * edu : 1
     * sex : 1
     * birthday : 1992-07-25
     * edu_school : 小学
     * edu_exp : 泼冷水救护会一直热群里了厉害了
     * edu_age : 5
     * res_share : 成果分享成果分享就是分享，没有说他的一起
     * class_video : null
     * ctime : 2016-04-20 15:36:57
     * head : http://192.168.10.142/operate/public/static/common/img/touxiang.jpg
     * label_admin : 沉默,领域
     * label : 0
     * average_price : 0.00
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

    public static class DataBean {
        private String id;
        private String ask;
        private String password;
        private String name;
        private String phone;
        private String email;
        private String yue;
        private String state;
        private String summary;
        private String authed;
        private String edu;
        private int sex;
        private String birthday;
        private String edu_school;
        private String edu_exp;
        private String edu_age;
        private String res_share;
        private String class_video;
        private String ctime;
        private String head;
        private String label_admin;
        private String label;
        private String average_price;
        private String has_message;

        public String getHas_message() {
            return has_message;
        }

        public void setHas_message(String has_message) {
            this.has_message = has_message;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAsk() {
            return ask;
        }

        public void setAsk(String ask) {
            this.ask = ask;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getYue() {
            return yue;
        }

        public void setYue(String yue) {
            this.yue = yue;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getAuthed() {
            return authed;
        }

        public void setAuthed(String authed) {
            this.authed = authed;
        }

        public String getEdu() {
            return edu;
        }

        public void setEdu(String edu) {
            this.edu = edu;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getEdu_school() {
            return edu_school;
        }

        public void setEdu_school(String edu_school) {
            this.edu_school = edu_school;
        }

        public String getEdu_exp() {
            return edu_exp;
        }

        public void setEdu_exp(String edu_exp) {
            this.edu_exp = edu_exp;
        }

        public String getEdu_age() {
            return edu_age;
        }

        public void setEdu_age(String edu_age) {
            this.edu_age = edu_age;
        }

        public String getRes_share() {
            return res_share;
        }

        public void setRes_share(String res_share) {
            this.res_share = res_share;
        }

        public String getClass_video() {
            return class_video;
        }

        public void setClass_video(String class_video) {
            this.class_video = class_video;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getLabel_admin() {
            return label_admin;
        }

        public void setLabel_admin(String label_admin) {
            this.label_admin = label_admin;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getAverage_price() {
            return average_price;
        }

        public void setAverage_price(String average_price) {
            this.average_price = average_price;
        }
    }
}
