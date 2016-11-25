package com.wr_education.bean.change;

/**
 * Created by waycubeoxa on 16/9/26.修改密码
 */
public class FindPassword {
    private int code;
    private String msg;
//    id：老师id
//    oldPass：旧密码
//    newPass：新密码
    private String id;
    private String oldPass;
    private String newPass;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
