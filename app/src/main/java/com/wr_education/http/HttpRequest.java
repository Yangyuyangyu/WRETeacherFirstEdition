package com.wr_education.http;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wr_education.bean.AboutUsBean;
import com.wr_education.bean.CallNameBean;
import com.wr_education.bean.ClassDetailsBean;
import com.wr_education.bean.HomePageTwoListBean;
import com.wr_education.bean.HomeStudentBean;
import com.wr_education.bean.LoginBean;
import com.wr_education.bean.MassBuidBean;
import com.wr_education.bean.MassDetailsBean;
import com.wr_education.bean.MassPlanBean;
import com.wr_education.bean.MassRegimetBean;
import com.wr_education.bean.MyMassListBean;
import com.wr_education.bean.MyMessageListBean;
import com.wr_education.bean.NewsDetailsBean;
import com.wr_education.bean.OrganizationHomeBean;
import com.wr_education.bean.OrganizationListBean;
import com.wr_education.bean.PublicBean;
import com.wr_education.bean.RecommendOrganizationBean;
import com.wr_education.bean.ScoreItemBean;
import com.wr_education.bean.StudentGradeBean;
import com.wr_education.bean.SyllabusBean;
import com.wr_education.bean.SyllabusUpDataBean;
import com.wr_education.bean.TeacherBean;
import com.wr_education.bean.UpHeadImgBean;
import com.wr_education.bean.VerficationBean;
import com.wr_education.bean.change.FindPassword;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求方法
 */
public class HttpRequest {

    private static HttpRequest httpRequest = null;
    private static Context ctx;

    public static HttpRequest getInstance(Context context) {
        if (httpRequest == null)
            httpRequest = new HttpRequest();
        ctx = context;
        return httpRequest;
    }


    /**
     * 注册里获取手机验证码
     *
     * @param mobile 手机号码
     * @return
     */
    public VerficationBean GetVerificationCode(String mobile) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("mobile", mobile);
            String result = HttpUtility.getInstance().doGet(HttpPath.VerificationCodeUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                VerficationBean bean = new VerficationBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 注册
     *
     * @param mobile 手机号码
     * @param pass   密码
     * @param code   验证码
     * @param log_id 获取验证码时返回的参数
     * @return
     */
    public PublicBean SetRegisterCode(String mobile, String pass, String code, String log_id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("mobile", mobile);
            map.put("pass", pass);
            map.put("code", code);
            map.put("log_id", log_id);
            String result = HttpUtility.getInstance().doPost(HttpPath.RegisterUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 登录
     *
     * @param mobile 手机号码
     * @param pass   密码
     * @return
     */
    public LoginBean setLogin(String mobile, String pass) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("mobile", mobile);
            map.put("pass", pass);
            String result = HttpUtility.getInstance().doLoadingPost(HttpPath.LoginUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                LoginBean bean = new LoginBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 忘记密码
     *
     * @param mobile 手机号
     * @param pass   密码
     * @param code   验证码
     * @param log_id 获取验证码时返回的参数
     */
    public PublicBean FindPassWorld(String mobile, String pass, String code, String log_id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("mobile", mobile);
            map.put("pass", pass);
            map.put("code", code);
            map.put("log_id", log_id);
            String result = HttpUtility.getInstance().doPost(HttpPath.FindPassWorldUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 修改密码
     */
    public FindPassword FindPassword(String id, String oldPwd, String newPwd) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            map.put("oldPass", oldPwd);
            map.put("newPass", newPwd);
            String result = HttpUtility.getInstance().doPost(HttpPath.FindPassWord, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                FindPassword bean = new FindPassword();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 老师基本信息
     *
     * @param id 老师的id
     * @return
     */
    public TeacherBean GetTeacherInfo(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doGet(HttpPath.TeacherInfoUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                TeacherBean bean = new TeacherBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 课程表
     *
     * @param id   老师id
     * @param week 查看哪一周的数据，0表示当前周，查看上一周将数字减一，下一周加一
     * @return
     */
    public SyllabusBean GetSyllabus(String id, String week) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            map.put("week", week);
            String result = HttpUtility.getInstance().doGet(HttpPath.SyllabusUrl, map);
            if (null != result && !result.equals("")) {
                Log.e("课程表数据",result);
                Gson gson = new Gson();
                SyllabusBean bean = new SyllabusBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 教育经历
     *
     * @param id      老师id
     * @param content 内容
     * @return
     */
    public PublicBean SetEducationExperience(String id, String content) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            map.put("content", content);
            String result = HttpUtility.getInstance().doPost(HttpPath.EducationExperienceUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改个人资料
     *
     * @param head_img 头像
     * @param name     名字
     * @param sex      性别
     * @param birthday 出生日期
     * @param id       老师id
     * @return
     */
    public PublicBean SetPersonData(String head_img, String name, String sex, String birthday, String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("head_img", head_img);
            map.put("name", name);
            map.put("sex", sex);
            map.put("birthday", birthday);
            map.put("id", id);
            String result = HttpUtility.getInstance().doPost(HttpPath.PersonDataUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改成果分享
     *
     * @param id      老师id
     * @param content 内容
     * @return
     */
    public PublicBean SetShareFruitData(String id, String content) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("content", content);
            map.put("id", id);
            String result = HttpUtility.getInstance().doPost(HttpPath.ShareFruitUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 意见反馈
     *
     * @param id      老师id
     * @param content 内容
     * @return
     */
    public PublicBean SetFeedBackData(String id, String content) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("content", content);
            map.put("id", id);
            String result = HttpUtility.getInstance().doPost(HttpPath.FeedBackUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 客服电话
     *
     * @return
     */
    public AboutUsBean SetAboutUsData() {
        try {
            Map<String, String> map = new HashMap<String, String>();
            String result = HttpUtility.getInstance().doGet(HttpPath.aboutUsUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                AboutUsBean bean = new AboutUsBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 请假
     *
     * @param id       老师id
     * @param reason   请假原因
     * @param remark   备注
     * @param class_id 课程id
     * @return
     */
    public PublicBean SetLeave(String id, String class_id, String reason, String remark) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("reason", reason);
            map.put("remark", remark);
            map.put("id", id);
            map.put("class_id", class_id);
            String result = HttpUtility.getInstance().doPost(HttpPath.LeaveUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 代课
     *
     * @param id     老师id
     * @param cid    课程id
     * @param tid    所选择的代课老师id
     * @param reason 原因
     * @param remark 备注
     * @return
     */
    public PublicBean SetAbsentTeacher(String id, String cid, String tid, String reason, String remark) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("reason", reason);
            map.put("remark", remark);
            map.put("id", id);
            map.put("cid", cid);
            map.put("tid", tid);
//            map.put("account", account);
//            map.put("pass", pass);
            String result = HttpUtility.getInstance().doPost(HttpPath.AbsentTeacherUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 老师加入的机构
     *
     * @param id 老师id
     * @return
     */
    public OrganizationListBean SetOrganization(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doGet(HttpPath.OrganizationUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                OrganizationListBean bean = new OrganizationListBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 机构列表--获取搜索的信息
     *
     * @param type 类型，搜索时传入
     * @param name 名称，搜索时传入
     * @return
     */
    public RecommendOrganizationBean GetFindDataOrganization(String type, String name, String page) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("type", type);
            map.put("name", name);
            map.put("page", page);
            String result = HttpUtility.getInstance().doGet(HttpPath.FindOrganizationUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                RecommendOrganizationBean bean = new RecommendOrganizationBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 机构详情页面
     *
     * @param agencyId 机构id
     * @param tid      老师id
     * @return
     */
    public OrganizationHomeBean GetOrganizationDetail(String agencyId, String tid) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("agencyId", agencyId);
            map.put("tid", tid);
            String result = HttpUtility.getInstance().doGet(HttpPath.OrganizationDetailUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                OrganizationHomeBean bean = new OrganizationHomeBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 社团动态   暂时还不知道哪里有用
     *
     * @param groupId 社团id
     * @return
     */
    public PublicBean GetMassDynamic(String groupId) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("groupId", groupId);
            String result = HttpUtility.getInstance().doGet(HttpPath.MassdynamicUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 申请加入机构
     *
     * @param id       老师id
     * @param agencyId 机构id
     * @return
     */
    public PublicBean SetAddOrganization(String id, String agencyId) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            map.put("agencyId", agencyId);
            String result = HttpUtility.getInstance().doPost(HttpPath.AddOrganizationUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 社团建设详情
     *
     * @param groupId
     * @return
     */
    public MassBuidBean GetMassBuildDetail(String groupId) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("groupId", groupId);
            String result = HttpUtility.getInstance().doGet(HttpPath.MassBuildDetailUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                MassBuidBean bean = new MassBuidBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 社团详情
     *
     * @param groupId
     * @return
     */
    public MassDetailsBean GetMassDetail(String groupId) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("groupId", groupId);
            String result = HttpUtility.getInstance().doGet(HttpPath.MassDetailUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                MassDetailsBean bean = new MassDetailsBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 社团管理制度
     *
     * @param groupId 社团id
     * @return
     */
    public MassRegimetBean GetMasssRegimet(String groupId) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("groupId", groupId);
            String result = HttpUtility.getInstance().doGet(HttpPath.MassRegimetUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                MassRegimetBean bean = new MassRegimetBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 课程规划
     *
     * @param groupId 社团id
     * @return
     */
    public MassPlanBean GetCoursePlan(String groupId) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("groupId", groupId);
            String result = HttpUtility.getInstance().doGet(HttpPath.CoursePlanUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                MassPlanBean bean = new MassPlanBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 机构下的社团
     *
     * @param id 机构id
     * @return
     */
    public MyMassListBean GetMyMass(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doGet(HttpPath.MyMassUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                MyMassListBean bean = new MyMassListBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 主页课程列表
     *
     * @param id 老师id
     * @return
     */
    public HomePageTwoListBean GetHomeCourse(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doGet(HttpPath.HomeCourseUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                HomePageTwoListBean bean = new HomePageTwoListBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 主页学生列表
     *
     * @param id 老师id
     * @return
     */
    public HomeStudentBean GetHomeStudent(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doGet(HttpPath.HomeStudentUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                HomeStudentBean bean = new HomeStudentBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 社团活动详情
     *
     * @param id 动态id
     * @return
     */
    public NewsDetailsBean GetnewsDetails(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doGet(HttpPath.newsDetailUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                NewsDetailsBean bean = new NewsDetailsBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 我的消息
     *
     * @param id 老师id
     * @return
     */
    public MyMessageListBean GetMyMessage(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doPost(HttpPath.myMessageUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                MyMessageListBean bean = new MyMessageListBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 修改课程状态
     *
     * @param status
     * @param id
     * @return
     */
    public SyllabusUpDataBean GetCourseState(String status, String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", status);
            map.put("cid", id);
            String result = HttpUtility.getInstance().doPost(HttpPath.courseStateUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                SyllabusUpDataBean bean = new SyllabusUpDataBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 点名
     *
     * @param id 课程id
     * @return
     */
    public CallNameBean GetCallNameState(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doGet(HttpPath.CallNameUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                CallNameBean bean = new CallNameBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 补点名
     *
     * @param id 课程id
     * @return
     */
    public CallNameBean GetSecondCallNameState(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doGet(HttpPath.SecondCallNameUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                CallNameBean bean = new CallNameBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存点名信息
     *
     * @param id      课程id
     * @param present 点名到场的学生id，格式为数组，如[1,2,3]
     * @param absent  点名未到的学生id，格式为数组，如[4,5,6]，没有则传空数组
     * @return
     */
    public PublicBean SetCallNameState(String id, String present, String absent) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            map.put("present", present);
            map.put("absent", absent);
            String result = HttpUtility.getInstance().doPost(HttpPath.SaveCallUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 提交课程报告
     *
     * @param courseId 上课记录id
     * @param content  上课内容
     * @param problem  上课情况及问题
     * @param solution 解决方案
     * @param homework 是否有作业，0无，1有
     * @param work     作业内容，无作业时传空值
     * @param img      作业图片，无作业时传空值
     * @return
     */
    public PublicBean SetCommitReport(String courseId, String content, String problem, String solution, String homework, String work, String img) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("courseId", courseId);
            map.put("content", content);
            map.put("problem", problem);
            map.put("solution", solution);
            map.put("homework", homework);
            map.put("work", work);
            map.put("img", img);
            String result = HttpUtility.getInstance().doPost(HttpPath.ReportUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 课程详情页面
     *
     * @param id 课程id
     * @return
     */
    public ClassDetailsBean GetCourseInfo(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doGet(HttpPath.CourseInfoUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                ClassDetailsBean bean = new ClassDetailsBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 评分时查询上课学生
     *
     * @param id 课程id
     * @return
     */
    public StudentGradeBean GetStudentOfCourse(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            String result = HttpUtility.getInstance().doGet(HttpPath.StudentOfCourseUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                StudentGradeBean bean = new StudentGradeBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 查询打分项
     *
     * @param class_id  上课记录id（studentOfCourse接口返回数据中courseInfo下的class_id的值）
     * @param studentId 所选择的学生id
     * @return
     */
    public ScoreItemBean GetScore(String class_id, String studentId) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("class_id", class_id);
            map.put("studentId", studentId);
            String result = HttpUtility.getInstance().doGet(HttpPath.ScoreUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                ScoreItemBean bean = new ScoreItemBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 保存评分信息
     *
     * @param studentId 学生id
     * @param classId   上课记录id（studentOfCourse接口返回数据中courseInfo下的class_id的值）
     * @param item      评分项id，以逗号连接的字符串
     * @param score     分数，以逗号连接的字符串
     * @return
     */
    public PublicBean GetSaveScore(String studentId, String classId, String item, String score) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("studentId", studentId);
            map.put("classId", classId);
            map.put("item", item);
            map.put("score", score);
            String result = HttpUtility.getInstance().doGet(HttpPath.SaveScoreUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 所有学生都评分后修改课程状态为结束
     *
     * @param classId 上课记录id
     * @return
     */
    public PublicBean GetScoreFinish(String classId) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("classId", classId);
            String result = HttpUtility.getInstance().doGet(HttpPath.ScoreFinishUrl, map);
            if (null != result && !result.equals("")) {
                Gson gson = new Gson();
                PublicBean bean = new PublicBean();
                bean = gson.fromJson(result, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public UpHeadImgBean UpdateUser(String base64) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", base64);
            String st = HttpUtility.getInstance().doPost(HttpPath.UpHeadImageUrl, map);
            if (null != st && !st.equals("")) {
                Gson gson = new Gson();
                UpHeadImgBean bean = new UpHeadImgBean();
                bean = gson.fromJson(st, bean.getClass());
                return bean;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}