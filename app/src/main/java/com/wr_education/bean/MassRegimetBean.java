package com.wr_education.bean;

/**
 * Created by Administrator on 2016/4/25.
 */
public class MassRegimetBean {

    /**
     * code : 0
     * msg : 获取数据成功
     * data : {"id":"2","title":"社团管理制度","detail":"<p><span style=\"color: rgb(255, 0, 0);\">社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度<\/span><\/p>","corporation":"6","img":null,"time":"2016-04-21 11:51:34"}
     */

    private int code;
    private String msg;
    /**
     * id : 2
     * title : 社团管理制度
     * detail : <p><span style="color: rgb(255, 0, 0);">社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度社团管理制度</span></p>
     * corporation : 6
     * img : null
     * time : 2016-04-21 11:51:34
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
        private String title;
        private String detail;
        private String corporation;
        private String img;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getCorporation() {
            return corporation;
        }

        public void setCorporation(String corporation) {
            this.corporation = corporation;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
