package com.wr_education.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.wr_education.R;

import java.io.File;

public class Customer_Center_Detail extends Activity {

    private Button button;
   // private CircleImageView head_imageview;// 头像
    //  private PopWindowPostInfomation popMenue;
    private Button set_headimg_from_gallery;// 图库
    private Button set_headimg_from_camera;// 拍照
    private Button set_headimg_cancel;// 取消
    private Dialog dialog;// 选择头像头像对话框
    private File tempFile;
    private WindowManager.LayoutParams lp;// 背景明暗设置
    public static final int SET_HEADIMG_CAMERA = 1;// 拍照
    public static final int SET_HEADIMG_GALLERY = 2;// 从相册中选择
    public static final int SET_HEADIMG_CROP = 3;// 裁剪结果
    private BitmapUtils bitmapUtils;
    private ImageView imageView;
    /* 头像名称 */
    public static final String PHOTO_FILE_NAME = "headimg_temp.jpg";//FileUtils.getCacheImageDir() + File.separator + PHOTO_FILE_NAME
    private File head_file = new File(Environment.getExternalStorageDirectory()+ "/WR_Education/image/"+PHOTO_FILE_NAME); //自己设置存放位置
    /* 头像路径 */
    public static final String HEADIMGPATH =Environment.getExternalStorageDirectory() +"/WR_Education/image/" + "head_image.jpg";


    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.customer_center_back:
//                    finish();
//                    break;
//                //更改头像
//                case R.id.customer_center_Re_head:
//                    showDialog();
//                    break;
                case R.id.camera_btn:
                    showDialog();
                    break;
                // 选择头像 取消
                case R.id.set_headimg_cancel:
                    dialog.dismiss();
                    break;
                // 选择头像 拍照
                case R.id.set_headimg_from_camera:
                    camera();
                    dialog.dismiss();
                    break;
                // 选择头像 图库
                case R.id.set_headimg_from_gallery:
                    gallery();
                    dialog.dismiss();
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);

        button=(Button)findViewById(R.id.camera_btn);
        imageView=(ImageView)findViewById(R.id.camere_img);
        button.setOnClickListener(mListener);
    }


    @Override
    protected void onResume() {
        super.onResume();
    //    bitmapUtils.clearCache();
        //    bitmapUtils.display(head_imageview, VolleyApplication.user_info.getHeadImg());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 显示选择头像的对话框
    private void showDialog() {
        View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog,
                null);
        dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        set_headimg_cancel = (Button) view
                .findViewById(R.id.set_headimg_cancel);
        set_headimg_from_camera = (Button) view
                .findViewById(R.id.set_headimg_from_camera);
        set_headimg_from_gallery = (Button) view
                .findViewById(R.id.set_headimg_from_gallery);
        set_headimg_cancel.setOnClickListener(mListener);
        set_headimg_from_gallery.setOnClickListener(mListener);
        set_headimg_from_camera.setOnClickListener(mListener);
    }

    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            if (head_file.exists()) {
                head_file.delete();
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(head_file));
            startActivityForResult(intent, SET_HEADIMG_CAMERA);
        }
    }

    public boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 从相册获取
     */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SET_HEADIMG_GALLERY);
    }

    /**
     * 裁剪图片
     *
     * @param uri
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);// 黑边
        intent.putExtra("scaleUpIfNeeded", true);// 黑边
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
        File f = new File(HEADIMGPATH);
        if (f.exists()) {
            f.delete();
        }//"file://" + File.separator+ Environment.getExternalStorageDirectory()  + File.separator + "head_image.jpg"
        Uri uritempFile = Uri.fromFile(f);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        startActivityForResult(intent, SET_HEADIMG_CROP);
    }


    /**
     * 上传用户头像
     */
    private void uploadHeadImage(final String imagepath) {
        Bitmap bm = BitmapFactory.decodeFile(imagepath);
        imageView.setImageBitmap(bm);
//        RequestParams params = new RequestParams();
//        params.addBodyParameter("action", "");
//        params.addBodyParameter("KeyValue", "");
//        params.addBodyParameter("imgFile", new File(imagepath));
//        HttpUtils httpUtils = new HttpUtils();
//        httpUtils.send(HttpRequest.HttpMethod.POST, VolleyHttpPath.IMGURL, params,
//                new RequestCallBack<String>() {
//                    @Override
//                    public void onStart() {
//                        Log.e("db", "上传开始");
//                    }
//
//                    @Override
//                    public void onLoading(long total, long current,
//                                          boolean isUploading) {
//                        if (isUploading) {
//                            Log.e("db", "upload: " + current + "/" + total);
//                        } else {
//                            Log.e("db", "reply: " + current + "/" + total);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(HttpException error, String msg) {
//                        Log.e("db", "上传背景失敗" + error.getExceptionCode() + ":"
//                                + msg);
//                    }
//
//                    @Override
//                    public void onSuccess(ResponseInfo<String> responseInfo) {
//                        Log.e("db", "responseInfo=====" + responseInfo.result);
//                        try {
//                            JSONObject jo = new JSONObject(responseInfo.result);
//                            if (jo.getString("code").equals("0")) {
//                                // 同时更改sp中的头像地址
//                                if (jo.getString("data") != null) {
//
//                                }
//                                try {
//                                    bitmapUtils.clearCache();
//                                    bitmapUtils.display(head_imageview,
//                                            HEADIMGPATH);
//                                } catch (Exception e) {
//                                }
//
//
//                            } else {
//                                Toast.makeText(getApplicationContext(),
//                                        jo.getString("msg"),
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 图库
            case SET_HEADIMG_GALLERY:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Uri uri = data.getData();
                        crop(uri);
                    }
                }
                break;
            // 相机
            case SET_HEADIMG_CAMERA:
                if (resultCode == RESULT_OK) {
                    if (Environment.getExternalStorageState().equals(
                            Environment.MEDIA_MOUNTED)) {
                        try {
                            // tempFile = new File(FileUtils.getCacheImageDir()+
                            // File.separator + PHOTO_FILE_NAME);
                            crop(Uri.fromFile(head_file));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(Customer_Center_Detail.this, "sd卡不存在",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            // 裁剪
            case SET_HEADIMG_CROP:
                if (resultCode == RESULT_OK) {
                    uploadHeadImage(HEADIMGPATH);
                }
                break;
        }
    }


}
