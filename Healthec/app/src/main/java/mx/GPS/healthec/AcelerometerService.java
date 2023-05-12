package mx.GPS.healthec;

import static android.os.SystemClock.uptimeMillis;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

public class AcelerometerService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long endTimeMillis, duration;
    private double awakeTime;
    private String userKey;

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        duration = intent.getLongExtra("duration", 0);
        userKey = intent.getStringExtra("key");
        endTimeMillis =  System.currentTimeMillis() + (5 * 1000);
        awakeTime = 0;
        sensorManager.registerListener(this, accelerometer, 1000000, 1000000);
        database = FirebaseDatabase.getInstance();//Se crea la instancia de la base de datos.

        return START_NOT_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Lógica para procesar los datos del acelerómetro+
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float z = event.values[2];

            if( z < 0){
                awakeTime++;
            }

            if (System.currentTimeMillis() >= endTimeMillis) {
                // Stop the service
                sensorManager.unregisterListener(this);

                awakeTime = awakeTime / 5.5;

                Log.d("Healthec", Double.toString(awakeTime));

                double realTimeSleep = Math.round(Math.abs(duration - awakeTime));

                String sleepTime = convertSecondsToHourMinute(realTimeSleep);
                try{
                    ref = database.getReference().child("usuarios").child(userKey).child("registroSueño");
                } catch ( Exception exception){
                    Log.d("Healthec", "No se pudo encontrar el camino de referencia");
                    ref = database.getReference().child("sueñoPerdido");
                }
                ref.runTransaction(new Transaction.Handler() {
                    @NonNull
                    @Override
                    public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                        DatabaseReference sleepRef = ref.push();

                        sleepRef.child("TiempoDeSueño").setValue(sleepTime);
                        Transaction.success(currentData);
                        return null;
                    }

                    @Override
                    public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                        if (committed) {
                            Log.d("Firebase", "Transaction completed");

                        } else {
                            Log.d("Firebase", "Transaction aborted");
                        }
                    }
                });

                stopSelf();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No es necesario implementar este método en este ejemplo
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static String convertSecondsToHourMinute(double totalSeconds) {
        int hours = (int) (totalSeconds / 3600); // Calculate whole number of hours
        double remainingSeconds = totalSeconds % 3600; // Calculate remaining seconds

        int minutes = (int) (remainingSeconds / 60); // Calculate whole number of minutes
        double seconds = remainingSeconds % 60; // Calculate remaining fractional seconds

        String time = (hours + " hours, " + minutes + " minutes, " + seconds + " seconds");
        return time;
    }


}


