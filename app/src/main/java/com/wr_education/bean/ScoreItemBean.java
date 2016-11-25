package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public class ScoreItemBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * data : [{"id":"5","name":"团队协作与协调能力","score":"0"}]
     * student : 233
     */

    private int code;
    private String msg;
    private String student;
    /**
     * id : 5
     * name : 团队协作与协调能力
     * score : 0
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

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
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
        private String score;

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

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}
