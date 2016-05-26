package com.yfw.zlt.mynotify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import notification.OtherActivity;

/**
 * 新用法：：：
 * 这里只是把我们平时使用的Notification.Builder改成了NotificationCompat.Builder而已，
 * 其他用法都是一模一样的，这样我们的通知就具备各种Android版本的兼容性了。
 */
public class MyNotification extends AppCompatActivity {
    private NotificationManager notificationManager;
    private Button send,cancel;
    private static final int NOTIFICATION_ID = 0x123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnotice);
        notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        send= (Button) findViewById(R.id.send);
        cancel= (Button) findViewById(R.id.cancel);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNotifition();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del(v);
            }
        });

    }
    /**
     * notification
     */
    private void setNotifition(){
        // 创建一个启动其他Activity的Intent
        Intent intent = new Intent(MyNotification.this, OtherActivity.class);
        // 单击Notification 通知时将会启动Intent 对应的程序，实现页面的跳转
        PendingIntent pi=PendingIntent.getActivity(MyNotification.this,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyNotification.this);
        Notification notification = builder
                // 设置打开该通知，该通知自动消失
                .setAutoCancel(true)
                // 设置显示在状态栏的通知提示信息
                .setTicker("通知")
                // 设置通知内容的标题
                .setContentTitle("通知标题")
                // 设置通知内容
                .setContentText("通知内容")
                // 设置通知时间
                .setWhen(System.currentTimeMillis())
                // 设置通知大小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                // 设改通知将要启动程序的Intent
                .setContentIntent(pi)
                .build();
        //int flagAutoCancel = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(NOTIFICATION_ID,notification);

    }
    // 为删除通知的按钮的点击事件定义事件处理方法
    public void del(View v) {
        // 取消通知
        notificationManager.cancel(NOTIFICATION_ID);
    }
}
