package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/14.
 */
public class MyMessageListBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * data : [{"id":"2","user_id":"22","type":"1","content":"消息2","time":"2016-04-22 13:41:55","status":""},{"id":"1","user_id":"22","type":"1","content":"这是一条消息","time":"2016-04-24 13:38:21","status":""}]
     */

    private int code;
    private String msg;
    /**
     * id : 2
     * user_id : 22
     * type : 1
     * content : 消息2
     * time : 2016-04-22 13:41:55
     * status :
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
        private String type;
        private String content;
        private String time;
        private String status;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
