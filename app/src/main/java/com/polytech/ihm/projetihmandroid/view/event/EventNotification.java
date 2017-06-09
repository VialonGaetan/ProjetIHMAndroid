package com.polytech.ihm.projetihmandroid.view.event;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.polytech.ihm.projetihmandroid.MainActivity;
import com.polytech.ihm.projetihmandroid.R;
import com.polytech.ihm.projetihmandroid.model.Event;

/**
 * @author Gaetan Vialon
 *         Created the 09/06/2017.
 */

public class EventNotification extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Intent intent = new Intent(this, Event.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(getString(R.string.rappel))
                .setContentText(getString(R.string.eventDay))
                .setContentIntent(pi);

        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, builder.build());
    }
}
