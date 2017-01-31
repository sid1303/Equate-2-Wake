package de.tu_chemnitz.sse.and2015.completealarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


public class RingtonePlayingService extends Service {
    private MediaPlayer media_song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        media_song.stop();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("in the service", "startCommand");
        Toast.makeText(this,"Service started...",Toast.LENGTH_LONG).show();

        media_song = MediaPlayer.create(getApplicationContext(),R.raw.istanbul);
        media_song.start();
        media_song.setLooping(true);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder.setSmallIcon(R.drawable.logo);
        notificationBuilder.setContentTitle("WAKE UP WAKE UP!!!");

        Intent notificationIntent = new Intent(getApplicationContext(), EquationDisplay.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setAutoCancel(true);

        // Add as notification
        notificationManager.notify(0, notificationBuilder.build());


        return START_STICKY;
    }
}
