package com.wr_education.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/25.
 */
public class AboutUsBean implements Serializable{

    /**
     * code : 0
     * msg : 数据获取成功
     * data : {"telephone":"400-88-20","detail":"<p>正式成立于2011年9月，公司前身是深圳市一致电子科技有限公司，致力于电子产品的制造业领域长达十年。<\/p>"}
     */

    private int code;
    private String msg;
    /**
     * telephone : 400-88-20
     * detail : <p>正式成立于2011年9月，公司前身是深圳市一致电子科技有限公司，致力于电子产品的制造业领域长达十年。</p>
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

    public static class DataBean implements Serializable{
        private String telephone;
        private String detail;

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
