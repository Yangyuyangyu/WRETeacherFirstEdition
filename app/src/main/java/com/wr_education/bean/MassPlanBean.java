package com.wr_education.bean;

/**
 * Created by Administrator on 2016/4/25.
 */
public class MassPlanBean {
    /**
     * code : 0
     * msg : 获取数据成功
     * data : {"plan":""}
     */

    private int code;
    private String msg;
    /**
     * plan :
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
        private String plan;

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }
    }
}
