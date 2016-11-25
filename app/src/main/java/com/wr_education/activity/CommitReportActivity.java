package com.wr_education.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ta.common.AsyncTask;
import com.wr_education.R;
import com.wr_education.adapter.ReleaseDynamicsGriviewAdapter;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.Bimp;
import com.wr_education.bean.ImageItem;
import com.wr_education.bean.PublicBean;
import com.wr_education.bean.StudentGradeBean;
import com.wr_education.bean.UpHeadImgBean;
import com.wr_education.dialog.CommitPicDialog;
import com.wr_education.http.HttpPath;
import com.wr_education.http.HttpRequest;
import com.wr_education.util.CameraUtils;
import com.wr_education.util.FileSizeUtil;
import com.wr_education.util.ImageUtility;
import com.wr_education.util.ImageUtils;
import com.wr_education.util.UploadUtil;
import com.wr_education.util.Utility;
import com.wr_education.view.NoScrollGridView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * 课程提交报告页面
 * Created by Administrator on 2016/4/15.
 */
public class CommitReportActivity extends AbstractActivity implements UploadUtil.OnUploadProcessListener {
    //    @Bind(R.id.commitreport_btn_switch)
//    UISwitchButton commitreportBtnSwitch;
    @Bind(R.id.commitreport_imgbtn_back)
    ImageButton commitreportImgbtnBack;
    @Bind(R.id.commitreport_tv_title)
    TextView commitreportTvTitle;
    @Bind(R.id.commitreport_tv_commit)
    TextView commitreportTvCommit;
    @Bind(R.id.commitreport_tv_time)
    TextView commitreportTvTime;
    @Bind(R.id.commitreport_et_homework)
    EditText commitreportEtHomework;
    //    @Bind(R.id.commitreport_ll_homework)
//    LinearLayout commitreportLlHomework;
    @Bind(R.id.commitreport_gridview)
    NoScrollGridView commitreportGridview;
    @Bind(R.id.commitreport_et_classcontent)
    EditText commitreportEtClasscontent;
    @Bind(R.id.commitreport_et_issue)
    EditText commitreportEtIssue;
    @Bind(R.id.commitreport_tv_solution)
    EditText commitreportTvSolution;

    private CommitPicDialog dialog;
    private Button paizho;
    private Button select_photo;
    private Button close;
    private RelativeLayout parent;
    private int num = 1;
    private ReleaseDynamicsGriviewAdapter griviewAdapter;
    private String nameString;
    private Uri photoUri;
    private File f;
    public static final int TAKE_PICTURE = 10014;// 拍照标识
    private PublicBean bean;
    private String id;
    private int picnumber; //用来判断有几张图片
    private String[] images;//用来装图片的
    private int imagesnumber = 0; //用来装图片判断装第几个
    private UpHeadImgBean upHeadImgBean;
    private ArrayList<String> ReportDraft;
    private ArrayList<String> MapReportDraft;
    private StudentGradeBean studentGradeBean;
    private ArrayList<StudentGradeBean.DataBean.StudentBean> list;
    private Boolean isGrade = true; //判断评分评完了没有

    private String classcontent = ""; //课堂内容
    private String reson = "";//上课情况及问题
    private String solution = "";//解决办法

    @Override
    protected void initLayout() {
        setContentView(R.layout.commitreport_layout);
    }


    @Override
    protected void aadListenter() {
        //    commitreportBtnSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
        commitreportImgbtnBack.setOnClickListener(this);
        commitreportTvCommit.setOnClickListener(this);
        paizho.setOnClickListener(this);
        select_photo.setOnClickListener(this);
        close.setOnClickListener(this);
        parent.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        //  commitreportBtnSwitch.setChecked(false);
        list = new ArrayList<StudentGradeBean.DataBean.StudentBean>();
        if (getIntent() != null) {
            commitreportTvTitle.setText(getIntent().getStringExtra("name")); //设置标题  数据从上个页面传过来
            commitreportTvTime.setText(getIntent().getStringExtra("time")); //设置时间
            id = getIntent().getStringExtra("id");
            updata(); //查看学生的评分评完没有
            System.out.println("map=" + MyApplication.getInstance().getReportDraft().toString());
            if (MyApplication.getInstance().getReportDraft().containsKey(id)) { //如果有数据
                MapReportDraft = MyApplication.getInstance().getReportDraft().get(id);
                if (!Utility.StringIsNull(MapReportDraft.get(0))) {
                    commitreportEtClasscontent.setText(MapReportDraft.get(0));
                }
                if (!Utility.StringIsNull(MapReportDraft.get(1))) {
                    commitreportEtIssue.setText(MapReportDraft.get(1));
                }
                if (!Utility.StringIsNull(MapReportDraft.get(2))) {
                    commitreportTvSolution.setText(MapReportDraft.get(2));
                }
                if (!Utility.StringIsNull(MapReportDraft.get(3))) {
                    commitreportEtHomework.setText(MapReportDraft.get(3));
                }
            }
        }

        Bimp.count = 9;
        Bimp.tempSelectBitmap = new ArrayList<ImageItem>();// 初始化Bimp
        View view = getLayoutInflater().inflate(R.layout.item_popupwindows,
                null);
        dialog = new CommitPicDialog(CommitReportActivity.this, view);
        parent = (RelativeLayout) view.findViewById(R.id.parent);
        paizho = (Button) view.findViewById(R.id.paizhao);
        select_photo = (Button) view.findViewById(R.id.select_photo);
        close = (Button) view.findViewById(R.id.quxiao);
        commitreportGridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        griviewAdapter = new ReleaseDynamicsGriviewAdapter(this);
        commitreportGridview.setAdapter(griviewAdapter);
        commitreportGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {
                    dialog.showDialog();
                } else {
                    Intent intent = new Intent(CommitReportActivity.this,
                            GalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commitreport_imgbtn_back:
                ReportDraft = new ArrayList<String>();
                if (!Utility.StringIsNull(commitreportEtClasscontent.getText().toString())) {
                    //第一个是课堂内容
                    ReportDraft.add(commitreportEtClasscontent.getText().toString());
                } else {
                    ReportDraft.add("null");
                }
                if (!Utility.StringIsNull(commitreportEtIssue.getText().toString())) {
                    //第二个是上课情况及问题
                    ReportDraft.add(commitreportEtIssue.getText().toString());
                } else {
                    ReportDraft.add("null");
                }
                if (!Utility.StringIsNull(commitreportTvSolution.getText().toString())) {
                    //第三个是解决办法
                    ReportDraft.add(commitreportTvSolution.getText().toString());
                } else {
                    ReportDraft.add("null");
                }
                if (!Utility.StringIsNull(commitreportEtHomework.getText().toString())) {
                    //第四个是作业内容
                    ReportDraft.add(commitreportEtHomework.getText().toString());
                } else {
                    ReportDraft.add("null");
                }
                //将数据保存下来
                MyApplication.getInstance().addReportDraft(id, ReportDraft);
                finish();
                saveEditTextAndCloseIMM();
                Bimp.tempSelectBitmap.clear();
                Bimp.tempSelectBitmap = null;
                break;
            case R.id.commitreport_tv_commit:
                if (!Utility.StringIsNull(commitreportEtClasscontent.getText().toString())) {
                    classcontent = commitreportEtClasscontent.getText().toString();
                }
                if (!Utility.StringIsNull(commitreportEtIssue.getText().toString())) {
                    reson = commitreportEtIssue.getText().toString();
                }
                if (!Utility.StringIsNull(commitreportTvSolution.getText().toString())) {
                    solution = commitreportTvSolution.getText().toString();
                }
                if(!isGrade){
                    Utility.showToast("您还有未评分的学生",CommitReportActivity.this);
                }else if (!Utility.StringIsNull(commitreportEtHomework.getText().toString())) { //如果有文字作业
                    if (Utility.NetworkAvailable(CommitReportActivity.this)) {
                        if (Bimp.tempSelectBitmap.size() > 0) {
                            showWaitLoad(CommitReportActivity.this, "上传中...");
                            toUploadFile();
                        } else {
                            new SetCommitReportTask().execute(id, classcontent, reson, solution, "1", commitreportEtHomework.getText().toString(), "");
                        }
                    } else {
                        Utility.showToastNoNetWork(CommitReportActivity.this);
                    }
                } else if (Bimp.tempSelectBitmap.size() > 0) {//如果没有文字作业，有图片
                    if (Utility.NetworkAvailable(CommitReportActivity.this)) {
                        showWaitLoad(CommitReportActivity.this, "上传中...");
                        toUploadFile();
                    } else {
                        Utility.showToastNoNetWork(CommitReportActivity.this);
                    }
                } else {
                    Utility.showToast("作业或者图片必须有一个才能提交", CommitReportActivity.this);
                }
//                if (commitreportBtnSwitch.isChecked()) { //有作业
//                    if (Utility.StringIsNull(commitreportEtClasscontent.getText().toString())) {
//                        Utility.showToast("课堂内容不能为空", CommitReportActivity.this);
//                    } else if (Utility.StringIsNull(commitreportEtIssue.getText().toString())) {
//                        Utility.showToast("上课情况及问题不能为空", CommitReportActivity.this);
//                    } else if (Utility.StringIsNull(commitreportTvSolution.getText().toString())) {
//                        Utility.showToast("解决办法不能为空", CommitReportActivity.this);
//                    } else if (Utility.StringIsNull(commitreportEtHomework.getText().toString())) {
//                        Utility.showToast("作业内容不能为空", CommitReportActivity.this);
//                    } else if (Utility.NetworkAvailable(CommitReportActivity.this)) {
//                        if (Bimp.tempSelectBitmap.size() > 0) {
//                            showWaitLoad(CommitReportActivity.this, "上传中...");
//                            toUploadFile();
//                        } else {
//                            new SetCommitReportTask().execute(id, commitreportEtClasscontent.getText().toString(), commitreportEtIssue.getText().toString(), commitreportTvSolution.getText().toString(), "1", commitreportEtHomework.getText().toString(), "");
//                        }
//                    } else {
//                        Utility.showToastNoNetWork(CommitReportActivity.this);
//                    }
//                } else {//没有作业
//                    if(!isGrade){
//                        Utility.showToast("还有没有评分的同学",CommitReportActivity.this);
//                    }else if (Utility.StringIsNull(commitreportEtClasscontent.getText().toString())) {
//                        Utility.showToast("课堂内容不能为空", CommitReportActivity.this);
//                    } else if (Utility.StringIsNull(commitreportEtIssue.getText().toString())) {
//                        Utility.showToast("上课情况及问题不能为空", CommitReportActivity.this);
//                    } else if (Utility.StringIsNull(commitreportTvSolution.getText().toString())) {
//                        Utility.showToast("解决办法不能为空", CommitReportActivity.this);
//                    } else if (Utility.NetworkAvailable(CommitReportActivity.this)) {
//                        new SetCommitReportTask().execute(id, commitreportEtClasscontent.getText().toString(), commitreportEtIssue.getText().toString(), commitreportTvSolution.getText().toString(), "0", "", "");
//                    } else {
//                        Utility.showToastNoNetWork(CommitReportActivity.this);
//                    }
//                }
                break;
            case R.id.paizhao:
                photo();
                dialog.cancel();
                break;
            case R.id.select_photo:
                Intent select_photo_intent = new Intent(
                        CommitReportActivity.this, AlbumActivity.class);
                startActivity(select_photo_intent);
                dialog.cancel();
                break;
            case R.id.quxiao:
                dialog.cancel();
                break;
            case R.id.parent:
                dialog.dismiss();
                break;
        }
    }

//    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            if (isChecked) {
//                commitreportLlHomework.setVisibility(View.VISIBLE);
//            } else {
//                commitreportLlHomework.setVisibility(View.GONE);
//            }
//        }
//    };

    public void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File dir = new File(Environment.getExternalStorageDirectory()
                + "/WR_Education/image");
        if (!dir.exists())
            dir.mkdirs();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        nameString = dateFormat.format(date);
        nameString = nameString + ".png";
        f = new File(dir, "/" + nameString);

        photoUri = Uri.fromFile(f);

        openCameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                photoUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.tempSelectBitmap != null) {
                    if (Bimp.tempSelectBitmap.size() < Bimp.count
                            && resultCode == RESULT_OK) {
                        if (f != null) {
                            ImageItem takePhoto = new ImageItem();
                            takePhoto.setImagePath(f.getPath());
                            Bimp.tempSelectBitmap.add(takePhoto);
                            griviewAdapter.notifyDataSetChanged();
                        }
                    }
                }
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        griviewAdapter.notifyDataSetChanged();
        images = new String[Bimp.tempSelectBitmap.size()];
    }


    private class SetCommitReportTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            bean = HttpRequest.getInstance(CommitReportActivity.this).SetCommitReport(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5], strings[6]);
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
                    if (bean.getCode() == 0) {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), CommitReportActivity.this);
                        finish();
                        saveEditTextAndCloseIMM();
                    } else {
                        DismissDialog();
                        Utility.showToast(bean.getMsg(), CommitReportActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", CommitReportActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }

    @Override
    public void onUploadDone(int responseCode, String message) {
        Gson gson = new Gson();
        upHeadImgBean = new UpHeadImgBean();
        upHeadImgBean = gson.fromJson(message, upHeadImgBean.getClass());
        if (upHeadImgBean.getCode() == 0) {
            if (imagesnumber < Bimp.tempSelectBitmap.size()) {
                System.out.println("数组大小是：" + images.length);
                images[imagesnumber] = upHeadImgBean.getData();
                imagesnumber++;
                toUploadFile();
            }
        }
    }

    @Override
    public void onUploadProcess(int uploadSize) {

    }

    @Override
    public void initUpload(int fileSize) {

    }

    private void toUploadFile() {
        if (imagesnumber < Bimp.tempSelectBitmap.size()) {
            String fileKey = "pic";
            UploadUtil uploadUtil = UploadUtil.getInstance();
            ;
            uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态

            Map<String, String> params = new HashMap<String, String>();
            params.put("orderId", "11111");
            System.out.println("图片路径是：" + Bimp.tempSelectBitmap.get(imagesnumber).getImagePath());
            Log.i("Whm", "Path:" + Bimp.tempSelectBitmap.get(imagesnumber).getImagePath());
            Log.i("Whm", "fileKey:" + fileKey);
            Log.i("Whm", "Url:" + HttpPath.UpHeadImageUrl);
            Log.i("Whm", "params:" + params.toString());
            String path = Bimp.tempSelectBitmap.get(imagesnumber).getImagePath();
            //先得到本地的这张图片进行一下压缩 防止部分机型无法加载与保存的问题
            if (FileSizeUtil.getFileOrFilesSize(path, FileSizeUtil.SIZETYPE_MB) > 1) {
                //判断图片文件是否大于1M （部分机型选择的图片大于1M 剪切后无法保存）
                Bitmap b1 = ImageUtils.getBitmapFile(path);//进行一下比例压缩
                path = CameraUtils.Camera_Path + CameraUtils.IMG_NAME_1;
                String newpath = CameraUtils.Camera_Path + CameraUtils.IMG_NAME_1;
                ImageUtility.compressImage(b1, newpath, 1024);
                path = newpath;
            }
            //压缩完成后将压缩后图片rui得到
            Uri old_uri = Uri.fromFile(new File(path));
            uploadUtil.uploadFile(old_uri.getPath(), fileKey, HttpPath.UpHeadImageUrl, params);
        } else {
            Log.i("Whm", "images:" + images);
            if(Utility.StringIsNull(commitreportEtHomework.getText().toString())){ //如果没有文字作业
                new SetCommitReportTask().execute(id, classcontent, reson, solution, "1", "", Utility.converToString(images));
            }else{
                new SetCommitReportTask().execute(id, classcontent, reson, solution, "1", commitreportEtHomework.getText().toString(), Utility.converToString(images));
            }
        }
    }


    //查询上课学生
    private class GetGradeTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitLoad(CommitReportActivity.this, "获取数据中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            studentGradeBean = HttpRequest.getInstance(CommitReportActivity.this).GetStudentOfCourse(strings[0]);
            // 请求成功返回数据
            if (studentGradeBean != null) {
                return "success";
            }
            return "fail";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                if (result.equals("success")) {
                    if (studentGradeBean.getCode() == 0) {
                        DismissDialog();
                        list.clear();
                        list.addAll(studentGradeBean.getData().getStudent());
                        for (int i = 0; i < list.size(); i++) {
                            if (Utility.StringIsNull(list.get(i).getScore())) {
                                isGrade = false; //如果有评分没有评完的学生
                            }
                        }
                    } else {
                        DismissDialog();
                        Utility.showToast(studentGradeBean.getMsg(), CommitReportActivity.this);
                    }
                } else {
                    Utility.showToast("请求失败，请重试！", CommitReportActivity.this);
                    DismissDialog();
                }
            } catch (Exception e) {
                // TODO: handle exception
                DismissDialog();
                e.getStackTrace();
            }
        }
    }

    private void updata() {
        if (Utility.NetworkAvailable(CommitReportActivity.this)) {
            new GetGradeTask().execute(id);
        } else {
            Utility.showToastNoNetWork(CommitReportActivity.this);
        }
    }
}
