package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/15.
 */
public class AbsentBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * data : [{"id":"31","name":"李三","phone":"18500000003"},{"id":"30","name":"李二","phone":"18500000002"},{"id":"29","name":"李一","phone":"18500000001"},{"id":"28","name":"李天三","phone":"13500000003"},{"id":"27","name":"shiguang","phone":"13330993147"}]
     */

    private int code;
    private String msg;
    /**
     * id : 31
     * name : 李三
     * phone : 18500000003
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
        private String phone;
        private Boolean ischeck=false;

        public Boolean getIscheck() {
            return ischeck;
        }

        public void setIscheck(Boolean ischeck) {
            this.ischeck = ischeck;
        }

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
