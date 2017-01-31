package de.tu_chemnitz.sse.and2015.completealarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import static android.content.Context.ALARM_SERVICE;

public class MainActivity extends AppCompatActivity {

    // Making the alarm Manager
    AlarmManager alarmManager;
    TimePicker timePicker;
    TextView textView;
    Context context;
    PendingIntent pendingIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        //Initializing
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        timePicker = (TimePicker)findViewById(R.id.timePicker);

        textView = (TextView)findViewById(R.id.textView);

        Switch switch1 = (Switch)findViewById(R.id.switch1);

        final Calendar calendar = Calendar.getInstance();

        //Creating Intent for Alarm Receiver class
        final Intent alarm_intent = new Intent(this.context, Alarm_Receiver.class);


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    calendar.set(Calendar.MINUTE,timePicker.getMinute());

                    final int hour = timePicker.getHour();
                    final int minute = timePicker.getMinute();

                    String minute_string = String.valueOf(minute);
                    String hour_string = String.valueOf(hour);

                    if(minute < 10){
                        minute_string = "0" + String.valueOf(minute);
                    }
                    if (hour > 12){
                        hour_string = String.valueOf(hour-12);
                    }

                    textView.setText("Alarm is set to " + hour_string + ":" + minute_string);

                    Toast.makeText(MainActivity.this,"Now Setting",Toast.LENGTH_SHORT).show();

                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,alarm_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
                }
                else{
                    textView.setText("Alarm is OFF");
                    alarmManager.cancel(pendingIntent );
                }
            }
        });



    }

    public void setEquation(View view)
    {
        Intent intent = new Intent(this,EquationDisplay.class);
        startActivity(intent);
    }


}
