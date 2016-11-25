package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/15.
 */
public class CallNameBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * courseId : 40
     * data : [{"id":"12","ask":"","password":"e10adc3949ba59abbe56e057f20f883e","head":"http://192.168.10.143/operate/public/upload/images/app/201605/09/1462783367.jpg","name":"西城","phone":"15756242976","email":"","id_type":"0","birthday":"1993-01-24","summary":"","yue":"0.00","integral":"0","sex":"1","state":"1","ctime":"","pay_pass":"","province_id":"0","city_id":"0","area_id":"0","addr":"放假放假","notice_unread_num":"0","leave":"0","called":"0"}]
     */

    private int code;
    private String msg;
    private int courseId;
    /**
     * id : 12
     * ask :
     * password : e10adc3949ba59abbe56e057f20f883e
     * head : http://192.168.10.143/operate/public/upload/images/app/201605/09/1462783367.jpg
     * name : 西城
     * phone : 15756242976
     * email :
     * id_type : 0
     * birthday : 1993-01-24
     * summary :
     * yue : 0.00
     * integral : 0
     * sex : 1
     * state : 1
     * ctime :
     * pay_pass :
     * province_id : 0
     * city_id : 0
     * area_id : 0
     * addr : 放假放假
     * notice_unread_num : 0
     * leave : 0
     * called : 0
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String ask;
        private String password;
        private String head;
        private String name;
        private String phone;
        private String email;
        private String id_type;
        private String birthday;
        private String summary;
        private String yue;
        private String integral;
        private String sex;
        private String state;
        private String ctime;
        private String pay_pass;
        private String province_id;
        private String city_id;
        private String area_id;
        private String addr;
        private String notice_unread_num;
        private String leave;
        private String called;

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

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
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

        public String getId_type() {
            return id_type;
        }

        public void setId_type(String id_type) {
            this.id_type = id_type;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getYue() {
            return yue;
        }

        public void setYue(String yue) {
            this.yue = yue;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getPay_pass() {
            return pay_pass;
        }

        public void setPay_pass(String pay_pass) {
            this.pay_pass = pay_pass;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getNotice_unread_num() {
            return notice_unread_num;
        }

        public void setNotice_unread_num(String notice_unread_num) {
            this.notice_unread_num = notice_unread_num;
        }

        public String getLeave() {
            return leave;
        }

        public void setLeave(String leave) {
            this.leave = leave;
        }

        public String getCalled() {
            return called;
        }

        public void setCalled(String called) {
            this.called = called;
        }
    }
}
