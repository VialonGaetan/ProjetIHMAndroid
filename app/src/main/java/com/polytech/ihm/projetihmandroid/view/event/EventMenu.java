package com.polytech.ihm.projetihmandroid.view.event;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.widget.Toast;
import com.polytech.ihm.projetihmandroid.MainActivity;
import com.polytech.ihm.projetihmandroid.R;
import com.polytech.ihm.projetihmandroid.model.Event;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Vialon Gaetan
 * on 17/05/2017.
 */

public class EventMenu implements PopupMenu.OnMenuItemClickListener {

    private Context mContext;
    private Event event;
    private final AlarmManager am;
    private final NotificationManager mNotification;

    public EventMenu(Context mContext, Event event) {
        this.mContext = mContext;
        this.event = event;
        am = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
        mNotification = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cancel_event:
                Toast.makeText(mContext, "Cancel", Toast.LENGTH_SHORT).show();
                cancelNotif();
                return true;
            case R.id.action_participate:
                Toast.makeText(mContext, "Participate", Toast.LENGTH_SHORT).show();
                createNotif();
                return true;
            default:
        }
        return false;
    }

    private void cancelNotif() {
        Intent intent = new Intent(mContext, EventNotification.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(mContext, 0, intent, 0);
        am.cancel(pendingIntent2);
    }


    private void createNotif(){
        final Intent launchNotifiactionIntent = new Intent(mContext, MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(mContext,
                0, launchNotifiactionIntent,
                PendingIntent.FLAG_ONE_SHOT);
        Notification.Builder builder = new Notification.Builder(mContext)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(mContext.getString(R.string.good_news))
                .setContentText(mContext.getString(R.string.inscription) + event.getName())
                .setContentIntent(pendingIntent);
        mNotification.notify(0, builder.build());

        Intent intent = new Intent(mContext, EventNotification.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(mContext, 0, intent, 0);
        am.setExact(AlarmManager.RTC,event.getDate().getTime(),pendingIntent2);

    }

}
