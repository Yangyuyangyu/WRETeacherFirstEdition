package com.wr_education.bean;

/**
 * Created by Administrator on 2016/4/25.
 */
public class NewsDetailsBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * data : {"id":"8","name":"社团动态1","send_to":"0","brief":"动态1","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201603/11/1457676574.jpeg","detail":"<p>在v续写着1<\/p>","time":"2016-03-11 14:15:55","group":"6","type":null,"group_name":"包子社"}
     */

    private int code;
    private String msg;
    /**
     * id : 8
     * name : 社团动态1
     * send_to : 0
     * brief : 动态1
     * img : http://192.168.10.122/weirao/public/upload/images/admin/1/201603/11/1457676574.jpeg
     * detail : <p>在v续写着1</p>
     * time : 2016-03-11 14:15:55
     * group : 6
     * type : null
     * group_name : 包子社
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
        private String name;
        private String send_to;
        private String brief;
        private String img;
        private String detail;
        private String time;
        private String group;
        private String type;
        private String group_name;

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

        public String getSend_to() {
            return send_to;
        }

        public void setSend_to(String send_to) {
            this.send_to = send_to;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }
    }
}
