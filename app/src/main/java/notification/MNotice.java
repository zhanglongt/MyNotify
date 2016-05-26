package notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yfw.zlt.mynotify.R;

/**
 * notify旧用法
 */
public class MNotice extends Activity implements View.OnClickListener{
    private NotificationManager nm;
    private Notification baseNF;
    private Button send,cancel;
    private static final int NOTIFICATION_ID = 0x123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnotice);
        send= (Button) findViewById(R.id.send);
        cancel= (Button) findViewById(R.id.cancel);
        nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //点击事件
        send.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }
    private void sendNotification(){
        // 创建一个启动其他Activity的Intent
        Intent intent = new Intent(MNotice.this, OtherActivity.class);
        // 单击Notification 通知时将会启动Intent 对应的程序，实现页面的跳转
        PendingIntent pi=PendingIntent.getActivity(MNotice.this,0,intent,0);
        baseNF=new Notification.Builder(this)
                // 设置打开该通知，该通知自动消失
               .setAutoCancel(true)
                // 设置显示在状态栏的通知提示信息
               .setTicker("通知")
                // 设置通知的图标
               .setSmallIcon(R.mipmap.ic_launcher)
                // 设置通知内容的标题
               .setContentTitle("问好")
                // 设置通知内容
               .setContentText("你好啊")
                //设置使用系统默认的声音、默认LED灯
               .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS)
               .setWhen(System.currentTimeMillis())
                // 设改通知将要启动程序的Intent
               .setContentIntent(pi)
               .getNotification();
         // 发送通知
         nm.notify(NOTIFICATION_ID, baseNF);
    }
    // 为删除通知的按钮的点击事件定义事件处理方法
    public void del(View v) {
        // 取消通知
        nm.cancel(NOTIFICATION_ID);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:
                sendNotification();
                break;
            case R.id.cancel:
                del(v);
                break;
        }
    }
}
