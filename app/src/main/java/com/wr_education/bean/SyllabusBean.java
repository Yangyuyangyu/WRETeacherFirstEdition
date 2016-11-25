package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */
public class SyllabusBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * data : [{"week":"3","date":"6-22","course":[{"id":"1","cid":"1","tid":"48","sid":"405,404,403,402,401,400,399,398,397,396","status":"2","date":"2016-06-22","start":"14:30:00","end":null,"place":"成都市一环路南三段46号","present":null,"absent":null,"longitude":"104.099246","latitude":"30.648638","location":"中国四川省成都市锦江区一环路东5段-46号","start_time":"2016-06-24 15:32:04","code":"356205055565233","sign_time":"2016-06-24 15:31:48","call_time":null,"late":null,"sign_late":"1","late_time":"2942","leave":-1,"replace":-1,"report":-1,"name":"小提琴教学001","img":"http://www.weiraoedu.com/public/upload/images/admin/1/201606/23/1466652081.jpg","type":"1","class_time":"2016-06-22 14:30~15:30","second_call":0}]},{"week":"4","date":"6-23","course":[{"id":"2","cid":"1","tid":"48","sid":"405,404,403,402,401,400,399,398,397,396","status":"0","date":"2016-06-23","start":"14:30:00","end":null,"place":"成都市一环路南三段46号","present":null,"absent":null,"longitude":null,"latitude":null,"location":null,"start_time":null,"code":null,"sign_time":null,"call_time":null,"late":null,"sign_late":null,"late_time":"0","leave":-1,"replace":-1,"report":-1,"name":"小提琴教学001","img":"http://www.weiraoedu.com/public/upload/images/admin/1/201606/23/1466652081.jpg","type":"1","class_time":"2016-06-23 14:30~15:30","second_call":0}]},{"week":"5","date":"6-24","course":[{"id":"3","cid":"1","tid":"48","sid":"405,404,403,402,401,400,399,398,397,396","status":"0","date":"2016-06-24","start":"14:30:00","end":null,"place":"成都市一环路南三段46号","present":null,"absent":null,"longitude":null,"latitude":null,"location":null,"start_time":null,"code":null,"sign_time":null,"call_time":null,"late":null,"sign_late":null,"late_time":"0","leave":-1,"replace":-1,"report":-1,"name":"小提琴教学001","img":"http://www.weiraoedu.com/public/upload/images/admin/1/201606/23/1466652081.jpg","type":"1","class_time":"2016-06-24 14:30~15:30","second_call":0}]},{"week":"6","date":"6-25","course":[{"id":"4","cid":"1","tid":"48","sid":"405,404,403,402,401,400,399,398,397,396","status":"0","date":"2016-06-25","start":"14:30:00","end":null,"place":"成都市一环路南三段46号","present":null,"absent":null,"longitude":null,"latitude":null,"location":null,"start_time":null,"code":null,"sign_time":null,"call_time":null,"late":null,"sign_late":null,"late_time":"0","leave":-1,"replace":-1,"report":-1,"name":"小提琴教学001","img":"http://www.weiraoedu.com/public/upload/images/admin/1/201606/23/1466652081.jpg","type":"1","class_time":"2016-06-25 14:30~15:30","second_call":0}]},{"week":"7","date":"6-26","course":[{"id":"5","cid":"1","tid":"48","sid":"405,404,403,402,401,400,399,398,397,396","status":"0","date":"2016-06-26","start":"14:30:00","end":null,"place":"成都市一环路南三段46号","present":null,"absent":null,"longitude":null,"latitude":null,"location":null,"start_time":null,"code":null,"sign_time":null,"call_time":null,"late":null,"sign_late":null,"late_time":"0","leave":-1,"replace":-1,"report":-1,"name":"小提琴教学001","img":"http://www.weiraoedu.com/public/upload/images/admin/1/201606/23/1466652081.jpg","type":"1","class_time":"2016-06-26 14:30~15:30","second_call":0}]}]
     */

    private int code;
    private String msg;
    /**
     * week : 3
     * date : 6-22
     * course : [{"id":"1","cid":"1","tid":"48","sid":"405,404,403,402,401,400,399,398,397,396","status":"2","date":"2016-06-22","start":"14:30:00","end":null,"place":"成都市一环路南三段46号","present":null,"absent":null,"longitude":"104.099246","latitude":"30.648638","location":"中国四川省成都市锦江区一环路东5段-46号","start_time":"2016-06-24 15:32:04","code":"356205055565233","sign_time":"2016-06-24 15:31:48","call_time":null,"late":null,"sign_late":"1","late_time":"2942","leave":-1,"replace":-1,"report":-1,"name":"小提琴教学001","img":"http://www.weiraoedu.com/public/upload/images/admin/1/201606/23/1466652081.jpg","type":"1","class_time":"2016-06-22 14:30~15:30","second_call":0}]
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
        private String week;
        private String date;
        /**
         * id : 1
         * cid : 1
         * tid : 48
         * sid : 405,404,403,402,401,400,399,398,397,396
         * status : 2
         * date : 2016-06-22
         * start : 14:30:00
         * end : null
         * place : 成都市一环路南三段46号
         * present : null
         * absent : null
         * longitude : 104.099246
         * latitude : 30.648638
         * location : 中国四川省成都市锦江区一环路东5段-46号
         * start_time : 2016-06-24 15:32:04
         * code : 356205055565233
         * sign_time : 2016-06-24 15:31:48
         * call_time : null
         * late : null
         * sign_late : 1
         * late_time : 2942
         * leave : -1
         * replace : -1
         * report : -1
         * name : 小提琴教学001
         * img : http://www.weiraoedu.com/public/upload/images/admin/1/201606/23/1466652081.jpg
         * type : 1
         * class_time : 2016-06-22 14:30~15:30
         * second_call : 0
         */

        private List<CourseBean> course;

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<CourseBean> getCourse() {
            return course;
        }

        public void setCourse(List<CourseBean> course) {
            this.course = course;
        }

        public static class CourseBean {
            private String id;
            private String cid;
            private String tid;
            private String sid;
            private String status;
            private String date;
            private String start;
            private String end;
            private String place;
            private String present;
            private String absent;
            private String longitude;
            private String latitude;
            private String location;
            private String start_time;
            private String code;
            private String sign_time;
            private String call_time;
            private String late;
            private String sign_late;
            private String late_time;
            private String leave;
            private String replace;
            private String report;
            private String name;
            private String img;
            private String type;
            private String class_time;
            private String second_call;

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

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
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

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getSign_time() {
                return sign_time;
            }

            public void setSign_time(String sign_time) {
                this.sign_time = sign_time;
            }

            public String getCall_time() {
                return call_time;
            }

            public void setCall_time(String call_time) {
                this.call_time = call_time;
            }

            public String getLate() {
                return late;
            }

            public void setLate(String late) {
                this.late = late;
            }

            public String getSign_late() {
                return sign_late;
            }

            public void setSign_late(String sign_late) {
                this.sign_late = sign_late;
            }

            public String getLate_time() {
                return late_time;
            }

            public void setLate_time(String late_time) {
                this.late_time = late_time;
            }

            public String getLeave() {
                return leave;
            }

            public void setLeave(String leave) {
                this.leave = leave;
            }

            public String getReplace() {
                return replace;
            }

            public void setReplace(String replace) {
                this.replace = replace;
            }

            public String getReport() {
                return report;
            }

            public void setReport(String report) {
                this.report = report;
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

            public String getSecond_call() {
                return second_call;
            }

            public void setSecond_call(String second_call) {
                this.second_call = second_call;
            }
        }
    }
}
