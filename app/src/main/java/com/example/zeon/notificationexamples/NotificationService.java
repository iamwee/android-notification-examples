package com.example.zeon.notificationexamples;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RemoteViews;

/**
 * Created by Zeon on 1/9/2559.
 */
public class NotificationService extends Service {

    Handler handler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                showNotification();
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.sendEmptyMessageDelayed(1, 5000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }
    private void showNotification() {


        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                );


        int color = ContextCompat.getColor(this, R.color.colorPrimary);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.ic_launcher);

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(bitmap)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText("Hello World, test notification from this app")
                        .setAutoCancel(true)
                        .setColor(color)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(resultPendingIntent)
                        .setVibrate(new long[] { 500, 1000, 500 })
                        .setLights(color, 3000, 3000)
                        .build();


        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(0, notification);
    }
}
