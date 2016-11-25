package com.wr_education.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.SortAdapter;
import com.wr_education.app.BaseFragment;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.HomeStudentBean;
import com.wr_education.bean.SortModel;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.CharacterParser;
import com.wr_education.util.PinyinComparator;
import com.wr_education.util.Utility;
import com.wr_education.view.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/12.
 */
public class HomePageThreeFragment extends BaseFragment {
    @Bind(R.id.homepagethree_list)
    ListView homepagethreeList;
    @Bind(R.id.homepagethree_tv_dialog)
    TextView homepagethreeTvDialog;
    @Bind(R.id.homepagethree_sidrbar)
    SideBar homepagethreeSidrbar;
    @Bind(R.id.organization_ll_circle)
    LinearLayout organizationLlCircle;
    private HomeStudentBean bean;
    private SortAdapter adapter;
    private View v;
    private String[] list;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    /**
     * s 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.viewpager_homepager_three, null);
    }

    @Override
    protected void initVariables() {
        init();
    }

    @Override
    protected void addListener() {

    }

    private void init() {
        homepagethreeSidrbar.setTextView(homepagethreeTvDialog);
        // 设置右侧触摸监听
        homepagethreeSidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //        该字母首次出现的位置
                if(adapter!=null){
                    int position = adapter.getPositionForSection(s.charAt(0));
                    if (position != -1) {
                        homepagethreeList.setSelection(position);
                    }
                }
            }
        });

        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();

        updata();//请求数据
    }

    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    private class GetHomeStudentTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(getActivity()).GetHomeStudent(strings[0]);
            // 请求成功返回数据
            if (bean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (bean.getCode() == 0) { //有数据
                        organizationLlCircle.setVisibility(View.GONE);
                        list = new String[bean.getData().size()];
                        for (int i = 0; i < bean.getData().size(); i++) {
                            list[i] = bean.getData().get(i);
                        }
                        SourceDateList = filledData(list);
                        Collections.sort(SourceDateList, pinyinComparator);
                        adapter = new SortAdapter(getActivity(), SourceDateList);
                        homepagethreeList.setAdapter(adapter);
                    } else {//没有数据
                        organizationLlCircle.setVisibility(View.GONE);
                 //       Utility.showToast(bean.getMsg(), getActivity());
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", getActivity());
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.getStackTrace();
            }
        }
    }

    private void updata() {
        if (Utility.NetworkAvailable(getActivity())) {
            if (MyApplication.getInstance().getLoginBean().getData().getId() != null) {
                new GetHomeStudentTask().execute(MyApplication.getInstance().getLoginBean().getData().getId());
            } else {
                Utility.showToast("没有获取到您的id", getActivity());
            }
        } else {
            Utility.showToastNoNetWork(getActivity());
        }
    }
}
