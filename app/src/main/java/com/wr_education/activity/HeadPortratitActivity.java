package com.wr_education.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.wr_education.R;
import com.wr_education.app.AbstractActivity;
import com.wr_education.app.MyApplication;
import com.wr_education.bean.UpHeadImgBean;
import com.wr_education.http.HttpPath;
import com.wr_education.util.CameraUtils;
import com.wr_education.util.FileSizeUtil;
import com.wr_education.util.ImageUtility;
import com.wr_education.util.ImageUtils;
import com.wr_education.util.UploadUtil;
import com.wr_education.util.Utility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class HeadPortratitActivity extends AbstractActivity implements
        OnClickListener, UploadUtil.OnUploadProcessListener {
    public static final int PZ_FLAG = 1;
    // 使用照相机拍照获取图片
    public static final int SELECT_PIC_BY_TACK_PHOTO = 20001;
    // 使用相册中的图片
    public static final int SELECT_PIC_BY_PICK_PHOTO = 20002;
    public static final int PHTOT_JQ = 20020;// 图片剪切
    private RelativeLayout min_layout;
    private LinearLayout linearLayout;
    private Button pz_but;
    private Button ck_but;
    private Button qx_but;
    private Button select_photo;
    private String imag_url;
    private Uri photoUri;
    private String base64;
    private String suid;
    private String cruid;
    private File f;
    private CameraUtils cameraUtils;
    private String path = "";

    private UpHeadImgBean bean;

    /**
     * 上传文件响应
     */
    protected static final int UPLOAD_FILE_DONE = 2;

    @Override
    protected void aadListenter() {
        linearLayout.setOnClickListener(this);
        pz_but.setOnClickListener(this);
        qx_but.setOnClickListener(this);
        select_photo.setOnClickListener(this);
        min_layout.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        MyApplication.getInstance().addActivity(this);
        cameraUtils = new CameraUtils();
        initview();
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.held_img_layout);
    }

    private void initview() {
        // TODO Auto-generated method stub
        linearLayout = (LinearLayout) findViewById(R.id.dialog_layout);
        min_layout = (RelativeLayout) findViewById(R.id.held_img_layout_mian);
        pz_but = (Button) findViewById(R.id.paizhao);
        qx_but = (Button) findViewById(R.id.quxiao);
        select_photo = (Button) findViewById(R.id.selectphoto);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.paizhao: //拍照
                openCarm();
                break;
            case R.id.quxiao: //取消
                finish();
                break;
            case R.id.selectphoto: //相册选择
                pickPhoto();
                break;
            default:
                finish();
                break;
        }

    }

    /***
     * 从相册中取图片
     */
    private void pickPhoto() {
        Intent intent = cameraUtils.get_Pick_Photo();
        startActivityForResult(intent, CameraUtils.CAMERA_UTILS_PICK_PHOTO);
    }

    private void openCarm() {
        Intent intent = cameraUtils.get_OpenCarmIntent();
        if (intent != null) {
            startActivityForResult(intent, CameraUtils.CAMERA_UTILS_TACK_PHOTO);
        } else {
            Utility.showToast("内存卡不存在", this);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            try {
                switch (requestCode) {

                    case CameraUtils.CAMERA_UTILS_TACK_PHOTO:
                        if (cameraUtils != null && cameraUtils.getImage() != null) {
                            path = cameraUtils.getImage().getPath();
                            //这里有2个不同的Uri 防止部分机型剪切图片保存失败
                            Uri old_uri = Uri.fromFile(new File(path));
                            path = CameraUtils.Camera_Path + CameraUtils.IMG_NAME;
                            Uri new_uri = Uri.fromFile(new File(path));
                            //构造剪切图片intent
                            Intent intent = CameraUtils.cropImageUri(
                                    old_uri, 800, 800,
                                    new_uri);
                            startActivityForResult(intent,
                                    CameraUtils.CAMERA_UTILS_INTERCEPT);
                        } else {
                            //对象被销毁后无法获取之前拍的图片的处理方式
                            path = CameraUtils.Camera_Path + CameraUtils.IMG_NAME_1;
                            f = new File(path);
                            if (!f.exists()) {
                                Utility.showToast("没有获取到文件路径", HeadPortratitActivity.this);
                                finish();
                                break;
                            }
                            Uri old_uri = Uri.fromFile(f);
                            path = CameraUtils.Camera_Path + CameraUtils.IMG_NAME;
                            f = new File(path);
                            Uri new_uri = Uri.fromFile(f);
                            Intent intent = CameraUtils.cropImageUri(
                                    old_uri, 800, 800,
                                    new_uri);
                            startActivityForResult(intent,
                                    CameraUtils.CAMERA_UTILS_INTERCEPT);

                        }
                        break;

                    case CameraUtils.CAMERA_UTILS_PICK_PHOTO:
                        if (data != null && data.getData() != null) {
                            photoUri = data.getData();
                            path = photoUri.getPath();
                            Uri new_uri = null;
                            if (photoUri.getScheme().toString().compareTo("content") == 0) {
                                //以content为开头得uri
                                path = ImageUtils.getImageAbsolutePath(HeadPortratitActivity.this, photoUri);
                                photoUri = Uri.fromFile(new File(path));
                            }
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
                            //给一个新的地址用与保存剪切后的图片 如何与前一个地址相同 部分机型剪切会失败所以这里 我构造了 2个uri
                            path = CameraUtils.Camera_Path + CameraUtils.IMG_NAME;
                            if (!Utility.StringIsNull(path)) {
                                File file_1 = new File(path);
                                new_uri = Uri.fromFile(file_1);
                                //启动剪切
                                Intent intent2 = CameraUtils.cropImageUri(old_uri,
                                        800, 800, new_uri);
                                startActivityForResult(intent2,
                                        CameraUtils.CAMERA_UTILS_INTERCEPT);
                            } else {
                                Utility.showToast("图片路径错误", HeadPortratitActivity.this);
                                finish();
                            }

                        } else {
                            Utility.showToast("没有获取到文件路径", HeadPortratitActivity.this);
                            finish();
                        }
                        break;

                    case CameraUtils.CAMERA_UTILS_INTERCEPT:
                        if (!Utility.StringIsNull(path)) {
                            linearLayout.setVisibility(View.GONE);
                            f = new File(path);
                            new Thread(run1).start();

                        } else {
                            path = CameraUtils.Camera_Path + CameraUtils.IMG_NAME;
                            linearLayout.setVisibility(View.GONE);
                            f = new File(path);
                            if (f.exists()) {
                                new Thread(run1).start();
                            } else {
                                Utility.showToast("没有获取到文件路径", HeadPortratitActivity.this);
                                finish();
                            }
                        }
                        break;
                }
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("e", e.toString());
            }

        }
    }

    private Runnable run1 = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            mHandler.sendEmptyMessage(100);
            toUploadFile();

        }

    };

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case 100:
                    if (Utility.NetworkAvailable(HeadPortratitActivity.this)) {
                        // 开始上传头像打开等待dialog
                        HeadPortratitActivity.this.showWaitLoad(
                                HeadPortratitActivity.this, "正在上传头像");

                    } else {
                        Utility.showToast("没有网络，请检查网络设置",
                                HeadPortratitActivity.this);
                    }

                    break;

                case 200:
                    MyApplication.getInstance().getLoginBean().getData().setHead(bean.getData());
                    HeadPortratitActivity.this.DismissDialog();
                    Utility.showToast("修改成功", HeadPortratitActivity.this);
                    finish();
                    break;

                case -200:
                    HeadPortratitActivity.this.DismissDialog();
                    Utility.showToast("头像识别失败", HeadPortratitActivity.this);
                    finish();
                    break;

                case -300:
                    HeadPortratitActivity.this.DismissDialog();
                    Utility.showToast("图片保存失败", HeadPortratitActivity.this);
                    finish();
                    break;
                case UPLOAD_FILE_DONE:
                    String result = (String) msg.obj;
                    Gson gson = new Gson();
                    UpHeadImgBean bean = new UpHeadImgBean();
                    bean = gson.fromJson(result, bean.getClass());
                    if (bean.getCode() == 0) {
                        Utility.showToast("修改成功", HeadPortratitActivity.this);
//                        MyApplication.getInstance().getLoginBean().getData().setHead(bean.getData());
                        Intent intent=new Intent(HeadPortratitActivity.this,PersonDataActivity.class);
                        intent.putExtra("img",bean.getData());
                        setResult(PZ_FLAG,intent);
                        finish();
                    } else {
                        Utility.showToast("头像修改失败", HeadPortratitActivity.this);
                        finish();
                    }
                    break;
            }

            base64 = null;

        }

        ;
    };

    @Override
    public void onUploadDone(int responseCode, String message) {
        HeadPortratitActivity.this.DismissDialog();
        Message msg = Message.obtain();
        msg.what = UPLOAD_FILE_DONE;
        msg.arg1 = responseCode;
        msg.obj = message;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onUploadProcess(int uploadSize) {

    }

    @Override
    public void initUpload(int fileSize) {

    }

    private void toUploadFile() {
        String fileKey = "pic";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态

        Map<String, String> params = new HashMap<String, String>();
        params.put("orderId", "11111");
        Log.i("Whm", "Path:" + f.getPath());
        Log.i("Whm", "fileKey:" + fileKey);
        Log.i("Whm", "Url:" + HttpPath.UpHeadImageUrl);
        Log.i("Whm", "params:" + params.toString());
        uploadUtil.uploadFile(f.getPath(), fileKey, HttpPath.UpHeadImageUrl, params);
    }
}
