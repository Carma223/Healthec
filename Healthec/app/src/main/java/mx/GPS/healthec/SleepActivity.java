package mx.GPS.healthec;

import androidx.annotation.NonNull;
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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SleepActivity extends AppCompatActivity {
    ImageButton btn_time;
    ToggleButton tgbtn_actividad;
    TextView txv_meta;
    int hourSleep, minuteSleep;

    String savedKey, savedPassword, savedEmail;

    FirebaseDatabase database;
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

        //Inicializar tabla
        BarChart barChart = findViewById(R.id.barChart);

        //Inicializar db firebase
        database = FirebaseDatabase.getInstance();

        DatabaseReference timeRef = database.getReference().child("usuarios").child(savedKey).child("registroSueño");

        // Agrega un listener para detectar cambios en los datos
        timeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Borra los datos existentes en el gráfico de barras
                barChart.clear();

                // Recorre los datos en Firebase y agrega las entradas al gráfico de barras
                ArrayList<BarEntry> entries = new ArrayList<>();
                int index = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    int horas = snapshot.child("horas").getValue(Integer.class);
                    int minutos = snapshot.child("minutos").getValue(Integer.class);
                    entries.add(new BarEntry(index, horas));
                    entries.add(new BarEntry(index + 1, minutos));
                    index += 2;
                }

                // Crea el conjunto de datos y configura el gráfico de barras
                BarDataSet dataSet = new BarDataSet(entries, "Tiempo");
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                BarData barData = new BarData(dataSet);
                barChart.setData(barData);
                barChart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar el error de Firebase, si es necesario
            }
        });


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