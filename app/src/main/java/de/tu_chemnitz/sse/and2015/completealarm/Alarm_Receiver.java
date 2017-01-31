package de.tu_chemnitz.sse.and2015.completealarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


public class Alarm_Receiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the receiver", "OnRecieve");
        Toast.makeText(context,"We are in the receiver",Toast.LENGTH_LONG).show();

        Intent serviceIntent = new Intent(context,RingtonePlayingService.class);
        context.startService(serviceIntent);

    }

}