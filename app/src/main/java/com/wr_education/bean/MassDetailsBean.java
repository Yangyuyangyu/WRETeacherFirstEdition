package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class MassDetailsBean {
    /**
     * code : 0
     * msg : 获取数据成功
     * data : {"id":"6","name":"包子社","brief":"一起来吃包子吧","admin":"2,1","create_time":"2016-04-15 14:43:57","course_plan":null,"score_items":null,"tutor":null,"img":"http://192.168.10.134/weirao/public/upload/images/admin/1/201604/15/1460702634.png","agency":"1","agency_name":"维立方科技","news":[{"id":"8","name":"社团动态1","send_to":"0","brief":"动态1","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201603/11/1457676574.jpeg","detail":"&lt;p&gt;在v续写着1&lt;/p&gt;","time":"2016-03-11 14:15:55","group":"6","type":null},{"id":"7","name":"新动态1","send_to":"0","brief":"哈哈1","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201602/26/1456453263.jpg","detail":"&lt;p&gt;恩恩额的订单的辅导辅导辅导&lt;/p&gt;","time":"2016-02-26 10:21:05","group":"6","type":null},{"id":"5","name":"这里的黎明静悄悄","send_to":"0","brief":"到处","img":"http://127.0.0.1/agency/Public/upload/images/admin/1/201602/24/1456302958.png","detail":"&lt;p&gt;除非对方的&lt;/p&gt;","time":"2016-02-24 16:36:05","group":"6","type":null}],"admins":"无情,铁手"}
     */

    private int code;
    private String msg;
    /**
     * id : 6
     * name : 包子社
     * brief : 一起来吃包子吧
     * admin : 2,1
     * create_time : 2016-04-15 14:43:57
     * course_plan : null
     * score_items : null
     * tutor : null
     * img : http://192.168.10.134/weirao/public/upload/images/admin/1/201604/15/1460702634.png
     * agency : 1
     * agency_name : 维立方科技
     * news : [{"id":"8","name":"社团动态1","send_to":"0","brief":"动态1","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201603/11/1457676574.jpeg","detail":"&lt;p&gt;在v续写着1&lt;/p&gt;","time":"2016-03-11 14:15:55","group":"6","type":null},{"id":"7","name":"新动态1","send_to":"0","brief":"哈哈1","img":"http://192.168.10.122/weirao/public/upload/images/admin/1/201602/26/1456453263.jpg","detail":"&lt;p&gt;恩恩额的订单的辅导辅导辅导&lt;/p&gt;","time":"2016-02-26 10:21:05","group":"6","type":null},{"id":"5","name":"这里的黎明静悄悄","send_to":"0","brief":"到处","img":"http://127.0.0.1/agency/Public/upload/images/admin/1/201602/24/1456302958.png","detail":"&lt;p&gt;除非对方的&lt;/p&gt;","time":"2016-02-24 16:36:05","group":"6","type":null}]
     * admins : 无情,铁手
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
        private String brief;
        private String admin;
        private String create_time;
        private String course_plan;
        private String score_items;
        private String tutor;
        private String img;
        private String agency;
        private String agency_name;
        private String admins;
        /**
         * id : 8
         * name : 社团动态1
         * send_to : 0
         * brief : 动态1
         * img : http://192.168.10.122/weirao/public/upload/images/admin/1/201603/11/1457676574.jpeg
         * detail : &lt;p&gt;在v续写着1&lt;/p&gt;
         * time : 2016-03-11 14:15:55
         * group : 6
         * type : null
         */

        private List<NewsBean> news;

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

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCourse_plan() {
            return course_plan;
        }

        public void setCourse_plan(String course_plan) {
            this.course_plan = course_plan;
        }

        public String getScore_items() {
            return score_items;
        }

        public void setScore_items(String score_items) {
            this.score_items = score_items;
        }

        public String getTutor() {
            return tutor;
        }

        public void setTutor(String tutor) {
            this.tutor = tutor;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getAgency() {
            return agency;
        }

        public void setAgency(String agency) {
            this.agency = agency;
        }

        public String getAgency_name() {
            return agency_name;
        }

        public void setAgency_name(String agency_name) {
            this.agency_name = agency_name;
        }

        public String getAdmins() {
            return admins;
        }

        public void setAdmins(String admins) {
            this.admins = admins;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public static class NewsBean {
            private String id;
            private String name;
            private String send_to;
            private String brief;
            private String img;
            private String detail;
            private String time;
            private String group;
            private Object type;

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

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }
        }
    }
}
