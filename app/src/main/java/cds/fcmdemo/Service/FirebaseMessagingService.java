package cds.fcmdemo.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import cds.fcmdemo.MainActivity;

/**
 * Created by Fazal on 9/9/2016.
 */
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        ShoeNotification(remoteMessage.getData().get("message"));
    }

    private void ShoeNotification(String message) {
        try{

            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setAutoCancel(true)
                    .setContentTitle("FCM Test Notification")
                    .setContentText(message)
                    .setSmallIcon(android.support.v7.appcompat.R.drawable.notification_template_icon_bg)
                    .setContentIntent(pendingIntent);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.notify(0,builder.build());


            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
