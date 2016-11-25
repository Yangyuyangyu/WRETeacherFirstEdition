package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class HomeStudentBean {

    /**
     * code  : 0
     * msg : 获取数据成功
     * data : ["宫城良田","流川枫","山帅"]
     */

    private int code;
    private String msg;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
