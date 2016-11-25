package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public class StudentGradeBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * data : {"student":[{"id":"226","name":"学生一","score":"5.0","score_id":"86"}],"courseInfo":{"id":"7","name":"语文（小课）001","class_id":"95"}}
     */

    private int code;
    private String msg;
    /**
     * student : [{"id":"226","name":"学生一","score":"5.0","score_id":"86"}]
     * courseInfo : {"id":"7","name":"语文（小课）001","class_id":"95"}
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
        /**
         * id : 7
         * name : 语文（小课）001
         * class_id : 95
         */

        private CourseInfoBean courseInfo;
        /**
         * id : 226
         * name : 学生一
         * score : 5.0
         * score_id : 86
         */

        private List<StudentBean> student;

        public CourseInfoBean getCourseInfo() {
            return courseInfo;
        }

        public void setCourseInfo(CourseInfoBean courseInfo) {
            this.courseInfo = courseInfo;
        }

        public List<StudentBean> getStudent() {
            return student;
        }

        public void setStudent(List<StudentBean> student) {
            this.student = student;
        }

        public static class CourseInfoBean {
            private String id;
            private String name;
            private String class_id;

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

            public String getClass_id() {
                return class_id;
            }

            public void setClass_id(String class_id) {
                this.class_id = class_id;
            }
        }

        public static class StudentBean {
            private String id;
            private String name;
            private String score;
            private String score_id;

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

            public String getScore_id() {
                return score_id;
            }

            public void setScore_id(String score_id) {
                this.score_id = score_id;
            }
        }
    }
}
