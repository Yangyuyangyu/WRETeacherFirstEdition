package com.wr_education.http;

/**
 * 请求地址类
 */
public class HttpPath {

    /* 请求服务器地址 */  //192.168.10.143
    public static String HTTPHOST = "http://www.weiraoedu.com/";
    //图片请求
    public static String ImgHOST = "http://www.weiraoedu.com/";
    //获取验证码
    public static String VerificationCodeUrl = HTTPHOST + "Api/TeacherApi/sendCode";
    //找回密码验证码
    public static String SendFindCodeUrl = HTTPHOST + "Api/TeacherApi/sendFindCode";
    //注册
    public static String RegisterUrl = HTTPHOST + "Api/TeacherApi/register";
    //登录
    public static String LoginUrl = HTTPHOST + "Api/TeacherApi/login";
    //老师基本信息
    public static String TeacherInfoUrl = HTTPHOST + "Api/TeacherApi/info";
    //找回密码
    public static String FindPassWorldUrl = HTTPHOST + "Api/TeacherApi/editPwd";

    //修改密码
    public static String FindPassWord = HTTPHOST + "Api/TeacherApi/editPass";

    //课程表
    public static String SyllabusUrl = HTTPHOST + "Api/TeacherApi/courseList";
    //修改教育经历
    public static String EducationExperienceUrl = HTTPHOST + "Api/TeacherApi/editEdu";
    //修改个人资料
    public static String PersonDataUrl = HTTPHOST + "Api/TeacherApi/editInfo";
    //请假
    public static String LeaveUrl = HTTPHOST + "Api/TeacherApi/leave";
    //修改成果分享
    public static String ShareFruitUrl = HTTPHOST + "Api/TeacherApi/editShare";
    //意见反馈
    public static String FeedBackUrl = HTTPHOST + "Api/TeacherApi/feedback";
    //申请代课
    public static String AbsentTeacherUrl = HTTPHOST + "Api/TeacherApi/replace";
    //老师加入的机构
    public static String OrganizationUrl = HTTPHOST + "Api/TeacherApi/joinedGroup";
    //机构列表
    public static String FindOrganizationUrl = HTTPHOST + "Api/TeacherApi/agency";
    //机构详情
    public static String OrganizationDetailUrl = HTTPHOST + "Api/TeacherApi/agencyDetail";
    //社团动态
    public static String MassdynamicUrl = HTTPHOST + "Api/TeacherApi/news";
    //申请加入机构
    public static String AddOrganizationUrl = HTTPHOST + "Api/TeacherApi/join";
    //社团建设详情
    public static String MassBuildDetailUrl = HTTPHOST + "Api/TeacherApi/groupBuild";
    //社团详情
    public static String MassDetailUrl = HTTPHOST + "Api/TeacherApi/groupDetail";
    //社团管理制度
    public static String MassRegimetUrl = HTTPHOST + "Api/TeacherApi/groupRule";
    //课程规划
    public static String CoursePlanUrl = HTTPHOST + "Api/TeacherApi/coursePlan";
    //机构下的社团
    public static String MyMassUrl = HTTPHOST + "Api/TeacherApi/groupOfAgency";
    //主页课程列表
    public static String HomeCourseUrl = HTTPHOST + "Api/TeacherApi/course";
    //主页学生列表
    public static String HomeStudentUrl = HTTPHOST + "Api/TeacherApi/myStudent";
    //社团动态详情
    public static String newsDetailUrl = HTTPHOST + "Api/TeacherApi/newsDetail";
    //我的消息
    public static String myMessageUrl = HTTPHOST + "Api/TeacherApi/message";
    //修改课程状态
    public static String courseStateUrl = HTTPHOST + "Api/TeacherApi/courseState";
    //点名
    public static String CallNameUrl = HTTPHOST + "Api/TeacherApi/rollCall";
    //补点名
    public static String SecondCallNameUrl = HTTPHOST + "Api/TeacherApi/recall";
    //保存点名信息
    public static String SaveCallUrl = HTTPHOST + "Api/TeacherApi/saveCall";
    //提交课程报告
    public static String ReportUrl = HTTPHOST + "Api/TeacherApi/report";
    //课程详情
    public static String CourseInfoUrl = HTTPHOST + "Api/TeacherApi/courseInfo";
    //评分时查询上课学生
    public static String StudentOfCourseUrl = HTTPHOST + "Api/TeacherApi/studentOfCourse";
    //查询打分项
    public static String ScoreUrl = HTTPHOST + "Api/TeacherApi/scoreItem";
    //保存评分信息
    public static String SaveScoreUrl = HTTPHOST + "Api/TeacherApi/saveScore";
    //所有学生都评分后修改课程状态为结束
    public static String ScoreFinishUrl = HTTPHOST + "Api/TeacherApi/scoreFinished";
    //头像上传
    public static String UpHeadImageUrl = HTTPHOST + "Api/CommonApi/imgUpload";
    //推送消息
    public static String pushMessageUrl = HTTPHOST + "Api/TeacherApi/pushMessage";
    //签到
    public static String signUpUrl = HTTPHOST + "Api/TeacherApi/signUp";
    //历史课程
    public static String finishedClassUrl = HTTPHOST + "Api/TeacherApi/finishedClass";
    //客服电话
    public static String aboutUsUrl = HTTPHOST + "Api/TeacherApi/aboutUs";
    //代课老师查询
    public static String absentUrl = HTTPHOST + "Api/TeacherApi/getTeacher";
}
