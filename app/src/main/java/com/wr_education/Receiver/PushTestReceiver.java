package com.wr_education.Receiver;

import android.content.Context;
import android.content.Intent;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.wr_education.activity.LoadingActivity;
import com.wr_education.activity.MyMessageActivity;
import com.wr_education.app.MyApplication;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class PushTestReceiver extends PushMessageReceiver {
    /**
     * 调用PushManager.startWork后，sdk将对push
     * server发起绑定请求，这个过程是异步的。绑定请求的结果通过onBind返回。 如果您需要用单播推送，需要把这里获取的channel
     * id和user id上传到应用server中，再调用server接口用channel id和user id给单个手机或者用户推送。
     *
     * @param context   BroadcastReceiver的执行Context
     * @param errorCode 绑定接口返回值，0 - 成功
     * @param appid     应用id。errorCode非0时为null
     * @param userId    应用user id。errorCode非0时为null
     * @param channelId 应用channel id。errorCode非0时为null
     * @param requestId 向服务端发起的请求id。在追查问题时有用；
     * @return none
     */
    private String ChannelId;
   // private GetChannelId getChannelId;

    @Override
    public void onBind(final Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
        String responseString = "onBind errorCode=" + errorCode + " appid="
                + appid + " userId=" + userId + " channelId=" + channelId
                + " requestId=" + requestId;
        System.out.println("百度推送返回的数据:" + responseString);
     //   getChannelId.getChannelid(channelId);
        MyApplication.getInstance().setChannelId(channelId);
    }

//    public  interface  GetChannelId{
//        public void getChannelid(String ChannelId);
//    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    /**
     * setTags() 的回调函数。
     *
     * @param context
     *            上下文
     * @param errorCode
     *            错误码。0表示某些tag已经设置成功；非0表示所有tag的设置均失败。
     * @param successTags
     *            设置成功的tag
     * @param failTags
     *            设置失败的tag
     * @param requestId
     *            分配给对云推送的请求的id
     */
    @Override
    public void onSetTags(Context context, int errorCode, List<String> successTags, List<String> failTags, String requestId) {

    }

    /**
     * delTags() 的回调函数。
     *
     * @param context
     *            上下文
     * @param errorCode
     *            错误码。0表示某些tag已经删除成功；非0表示所有tag均删除失败。
     * @param successTags
     *            成功删除的tag
     * @param failTags
     *            删除失败的tag
     * @param requestId
     *            分配给对云推送的请求的id
     */
    @Override
    public void onDelTags(Context context, int errorCode, List<String> successTags, List<String> failTags, String requestId) {

    }


    /**
     * listTags() 的回调函数。
     *
     * @param context
     *            上下文
     * @param errorCode
     *            错误码。0表示列举tag成功；非0表示失败。
     * @param tags
     *            当前应用设置的所有tag。
     * @param requestId
     *            分配给对云推送的请求的id
     */
    @Override
    public void onListTags(Context context, int errorCode, List<String> tags, String requestId) {

    }
    /**
     * 接收透传消息的函数。
     *
     * @param context
     *            上下文
     * @param message
     *            推送的消息
     * @param customContentString
     *            自定义内容,为空或者json字符串
     */
    @Override
    public void onMessage(Context context, String message, String customContentString) {

    }

    /**
     * 接收通知点击的函数。
     *
     * @param context
     *            上下文
     * @param title
     *            推送的通知的标题
     * @param description
     *            推送的通知的描述
     * @param customContentString
     *            自定义内容，为空或者json字符串
     */
    @Override
    public void onNotificationClicked(Context context, String title, String description, String customContentString) {
        Intent intent = new Intent();
        if(MyApplication.isLogin){
            intent.setClass(context.getApplicationContext(), MyMessageActivity.class);
            //百度云推送默认点击后跳转到指定页面 需加上下面代码才能跳转到指定位置
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if(MyApplication.getInstance().getChannelId()!=null){
//            intent.putExtra("id",MyApplication.getInstance().getChannelId());
//        }
            MyApplication.setIsMyMessage(true);
            context.getApplicationContext().startActivity(intent);
        }else{
            intent.setClass(context.getApplicationContext(), LoadingActivity.class);
            //百度云推送默认点击后跳转到指定页面 需加上下面代码才能跳转到指定位置
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if(MyApplication.getInstance().getChannelId()!=null){
//            intent.putExtra("id",MyApplication.getInstance().getChannelId());
//        }
            MyApplication.setIsMyMessage(true);
            context.getApplicationContext().startActivity(intent);
        }
    }

    /**
     * 接收通知到达的函数。
     *
     * @param context
     *            上下文
     * @param title
     *            推送的通知的标题
     * @param description
     *            推送的通知的描述
     * @param customContentString
     *            自定义内容，为空或者json字符串
     */
    @Override
    public void onNotificationArrived(Context context, String title, String description, String customContentString) {

    }

}
