package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */
public class TeacherBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * data : {"info":{"id":"22","ask":null,"password":"e10adc3949ba59abbe56e057f20f883e","name":"王浩淼","phone":"15982407336","email":"lvshushan33@163.com","yue":"0.00","state":"2","summary":"简介","authed":"1","edu":"1","sex":"1","birthday":"1992-07-25","edu_school":"小学","edu_exp":"泼冷水救护会一直热群里了厉害了","edu_age":"5","res_share":"成果分享成果分享就是分享，没有说他的一起","class_video":null,"ctime":"2016-04-20 15:36:57","head":"http://192.168.10.142/operate/public/static/common/img/touxiang.jpg","label_admin":"沉默,领域","label":"0","average_price":"0.00","address":"四川省成都市武侯区倪家桥22号","qualification":"身份认证、"},"course":[{"id":"4","name":"UFC极限格斗","corporation":"3","subject":"3","address":"地方大幅度","fit_crowd":"地方大幅度","goal":"辅导费","quit_rule":"地方的","join_rule":"地方的","detail":"&lt;p&gt;的辅导辅导辅导&lt;/p&gt;","img":"http://192.168.10.134/weirao/public/upload/images/admin/1/201604/15/1460702896.png","type":"1","brief":"地方大幅度","teacher":"22","status":"1","cycle":null,"day":null,"start":null,"end":null,"course_num":"0"},{"id":"3","name":"民族舞蹈","corporation":"4","subject":"2","address":"天紫界","fit_crowd":"零基础","goal":"目标","quit_rule":"规则","join_rule":"规则","detail":"&lt;p&gt;详情&lt;/p&gt;","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201603/13/1457864015.jpeg","type":"1","brief":"简介","teacher":"22","status":"1","cycle":"1","day":null,"start":"19:00:00","end":"20:00:00","course_num":"0"},{"id":"2","name":"课程1","corporation":"4","subject":"2","address":"九眼桥","fit_crowd":"零基础","goal":"的神烦大叔","quit_rule":"是v收到","join_rule":"是v但是的的","detail":"&lt;p&gt;啥地方都是正常的事出的的&amp;nbsp;&lt;/p&gt;","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201603/13/1457858086.jpeg","type":"1","brief":"许多新中的的","teacher":"22","status":"1","cycle":null,"day":null,"start":null,"end":null,"course_num":"0"},{"id":"1","name":"订单","corporation":"2","subject":"1","address":"订单","fit_crowd":"分到","goal":"分到","quit_rule":"地方","join_rule":"地方","detail":"&lt;p&gt;分到&lt;/p&gt;","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201603/01/1456827915.jpg","type":"1","brief":"分到","teacher":"22","status":"1","cycle":"1","day":null,"start":"10:00:00","end":"12:00:00","course_num":"0"}],"courseNum":4,"comment":[{"id":"1","user_id":"10","course_id":"4","content":"dfdfdfd","time":"2016-04-21 18:24:46","user_img":"./public/static/home/images/touxiang.jpg","user_name":"山帅"}]}
     */

    private int code;
    private String msg;
    /**
     * info : {"id":"22","ask":null,"password":"e10adc3949ba59abbe56e057f20f883e","name":"王浩淼","phone":"15982407336","email":"lvshushan33@163.com","yue":"0.00","state":"2","summary":"简介","authed":"1","edu":"1","sex":"1","birthday":"1992-07-25","edu_school":"小学","edu_exp":"泼冷水救护会一直热群里了厉害了","edu_age":"5","res_share":"成果分享成果分享就是分享，没有说他的一起","class_video":null,"ctime":"2016-04-20 15:36:57","head":"http://192.168.10.142/operate/public/static/common/img/touxiang.jpg","label_admin":"沉默,领域","label":"0","average_price":"0.00","address":"四川省成都市武侯区倪家桥22号","qualification":"身份认证、"}
     * course : [{"id":"4","name":"UFC极限格斗","corporation":"3","subject":"3","address":"地方大幅度","fit_crowd":"地方大幅度","goal":"辅导费","quit_rule":"地方的","join_rule":"地方的","detail":"&lt;p&gt;的辅导辅导辅导&lt;/p&gt;","img":"http://192.168.10.134/weirao/public/upload/images/admin/1/201604/15/1460702896.png","type":"1","brief":"地方大幅度","teacher":"22","status":"1","cycle":null,"day":null,"start":null,"end":null,"course_num":"0"},{"id":"3","name":"民族舞蹈","corporation":"4","subject":"2","address":"天紫界","fit_crowd":"零基础","goal":"目标","quit_rule":"规则","join_rule":"规则","detail":"&lt;p&gt;详情&lt;/p&gt;","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201603/13/1457864015.jpeg","type":"1","brief":"简介","teacher":"22","status":"1","cycle":"1","day":null,"start":"19:00:00","end":"20:00:00","course_num":"0"},{"id":"2","name":"课程1","corporation":"4","subject":"2","address":"九眼桥","fit_crowd":"零基础","goal":"的神烦大叔","quit_rule":"是v收到","join_rule":"是v但是的的","detail":"&lt;p&gt;啥地方都是正常的事出的的&amp;nbsp;&lt;/p&gt;","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201603/13/1457858086.jpeg","type":"1","brief":"许多新中的的","teacher":"22","status":"1","cycle":null,"day":null,"start":null,"end":null,"course_num":"0"},{"id":"1","name":"订单","corporation":"2","subject":"1","address":"订单","fit_crowd":"分到","goal":"分到","quit_rule":"地方","join_rule":"地方","detail":"&lt;p&gt;分到&lt;/p&gt;","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201603/01/1456827915.jpg","type":"1","brief":"分到","teacher":"22","status":"1","cycle":"1","day":null,"start":"10:00:00","end":"12:00:00","course_num":"0"}]
     * courseNum : 4
     * comment : [{"id":"1","user_id":"10","course_id":"4","content":"dfdfdfd","time":"2016-04-21 18:24:46","user_img":"./public/static/home/images/touxiang.jpg","user_name":"山帅"}]
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
        /**
         * id : 22
         * ask : null
         * password : e10adc3949ba59abbe56e057f20f883e
         * name : 王浩淼
         * phone : 15982407336
         * email : lvshushan33@163.com
         * yue : 0.00
         * state : 2
         * summary : 简介
         * authed : 1
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
         * address : 四川省成都市武侯区倪家桥22号
         * qualification : 身份认证、
         */

        private InfoBean info;
        private int courseNum;
        /**
         * id : 4
         * name : UFC极限格斗
         * corporation : 3
         * subject : 3
         * address : 地方大幅度
         * fit_crowd : 地方大幅度
         * goal : 辅导费
         * quit_rule : 地方的
         * join_rule : 地方的
         * detail : &lt;p&gt;的辅导辅导辅导&lt;/p&gt;
         * img : http://192.168.10.134/weirao/public/upload/images/admin/1/201604/15/1460702896.png
         * type : 1
         * brief : 地方大幅度
         * teacher : 22
         * status : 1
         * cycle : null
         * day : null
         * start : null
         * end : null
         * course_num : 0
         */

        private List<CourseBean> course;
        /**
         * id : 1
         * user_id : 10
         * course_id : 4
         * content : dfdfdfd
         * time : 2016-04-21 18:24:46
         * user_img : ./public/static/home/images/touxiang.jpg
         * user_name : 山帅
         */

        private List<CommentBean> comment;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public int getCourseNum() {
            return courseNum;
        }

        public void setCourseNum(int courseNum) {
            this.courseNum = courseNum;
        }

        public List<CourseBean> getCourse() {
            return course;
        }

        public void setCourse(List<CourseBean> course) {
            this.course = course;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public static class InfoBean {
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
            private String sex;
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
            private String address;
            private String qualification;
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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getQualification() {
                return qualification;
            }

            public void setQualification(String qualification) {
                this.qualification = qualification;
            }
        }

        public static class CourseBean {
            private String id;
            private String name;
            private String corporation;
            private String subject;
            private String address;
            private String fit_crowd;
            private String goal;
            private String quit_rule;
            private String join_rule;
            private String detail;
            private String img;
            private String type;
            private String brief;
            private String teacher;
            private String status;
            private String cycle;
            private String day;
            private String start;
            private String end;
            private String course_num;

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

            public String getCorporation() {
                return corporation;
            }

            public void setCorporation(String corporation) {
                this.corporation = corporation;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getFit_crowd() {
                return fit_crowd;
            }

            public void setFit_crowd(String fit_crowd) {
                this.fit_crowd = fit_crowd;
            }

            public String getGoal() {
                return goal;
            }

            public void setGoal(String goal) {
                this.goal = goal;
            }

            public String getQuit_rule() {
                return quit_rule;
            }

            public void setQuit_rule(String quit_rule) {
                this.quit_rule = quit_rule;
            }

            public String getJoin_rule() {
                return join_rule;
            }

            public void setJoin_rule(String join_rule) {
                this.join_rule = join_rule;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
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

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getTeacher() {
                return teacher;
            }

            public void setTeacher(String teacher) {
                this.teacher = teacher;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCycle() {
                return cycle;
            }

            public void setCycle(String cycle) {
                this.cycle = cycle;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public String getCourse_num() {
                return course_num;
            }

            public void setCourse_num(String course_num) {
                this.course_num = course_num;
            }
        }

        public static class CommentBean {
            private String id;
            private String user_id;
            private String course_id;
            private String content;
            private String time;
            private String user_img;
            private String user_name;

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

            public String getCourse_id() {
                return course_id;
            }

            public void setCourse_id(String course_id) {
                this.course_id = course_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getUser_img() {
                return user_img;
            }

            public void setUser_img(String user_img) {
                this.user_img = user_img;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }
    }
}
