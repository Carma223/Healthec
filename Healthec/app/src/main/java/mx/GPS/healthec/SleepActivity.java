package mx.GPS.healthec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Time;
import java.util.Calendar;
import java.util.Locale;

public class SleepActivity extends AppCompatActivity {
    ImageButton btn_time;
    ToggleButton tgbtn_actividad;
    TextView txv_meta;
    int hourSleep, minuteSleep;

    String savedKey, savedPassword, savedEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        btn_time = findViewById(R.id.btnSleepHorario);
        tgbtn_actividad = findViewById(R.id.tgbtnSleep);
        txv_meta = findViewById(R.id.txvMetaSueno);

        SharedPreferences prefs = getSharedPreferences("Account", MODE_PRIVATE);
        savedEmail = prefs.getString("email", null);
        savedPassword = prefs.getString("password", null);
        savedKey = prefs.getString("key", null);

    }

    public void popTimePicker (View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hourSleep = selectedHour;
                minuteSleep = selectedMinute;


                txv_meta.setText(String.format(Locale.getDefault(), "%02d:%02d",hourSleep, minuteSleep));

            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialogOne = new TimePickerDialog(this, style, onTimeSetListener, hourSleep, minuteSleep, true);


        timePickerDialogOne.setTitle("Selecciona el numero de horas que deseas dormir");

        timePickerDialogOne.show();

    }

    public void startService( View v){
        long durationSeconds = ((long)hourSleep * 3600) + (long)(minuteSleep * 60);

        Intent serviceIntent = new Intent(this, AcelerometerService.class);
        serviceIntent.putExtra("duration", durationSeconds);
        serviceIntent.putExtra("userKey", savedKey);
        startService(serviceIntent);

    }

}