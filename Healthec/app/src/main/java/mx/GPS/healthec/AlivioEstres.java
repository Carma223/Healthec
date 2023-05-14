package mx.GPS.healthec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class AlivioEstres extends AppCompatActivity {
    long START_TIME_IN_MILLIS = 320000;
    Button btnIniciar;
    TextView contador,instrucciones;
    CountDownTimer tiempo;
    Button btnReset,btnPausar;
    boolean mTimerRunning;
    long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    MediaPlayer bell;
    MediaPlayer respiracion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int orientation=getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_PORTRAIT){
            getSupportActionBar().hide();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else
            getSupportActionBar().show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alivio_estres);
        instrucciones=findViewById(R.id.txtInstrucciones);
        contador=findViewById(R.id.txtContador);
        btnIniciar=findViewById(R.id.btnIniciar);
        btnReset=findViewById(R.id.btnReset);
        btnPausar=findViewById(R.id.btnPausar);
        bell=MediaPlayer.create(this,R.raw.bell);
        bell.setVolume(0.1f,0.1f);
        respiracion=MediaPlayer.create(this,R.raw.respiracion);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startTimer();
                    bell.start();
                    respiracion.start();
                    instrucciones.setText("COMIENZA");

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
                bell.seekTo(0);
                respiracion.seekTo(0);
            }
        });
        btnPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
                bell.pause();
                respiracion.pause();

            }
        });

        updateCountDownText();
    }

    private void startTimer() {
        tiempo = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                btnIniciar.setText("INICIAR EJERCICIO");
                btnIniciar.setVisibility(View.INVISIBLE);
                btnReset.setVisibility(View.VISIBLE);
                btnPausar.setVisibility(View.INVISIBLE);
                instrucciones.setText("TEN UN BUEN DIA");
            }
        }.start();
        Toast.makeText(this, "Recuerda usar audifonos para una mejor experiencia", Toast.LENGTH_LONG).show();

        mTimerRunning = true;
        btnIniciar.setText("INICIAR EJERCICIO");
        btnPausar.setVisibility(View.VISIBLE);
        btnReset.setVisibility(View.INVISIBLE);
        btnIniciar.setVisibility(View.INVISIBLE);

    }

    private void pauseTimer() {
        tiempo.cancel();
        mTimerRunning = false;
        btnIniciar.setText("REANUDAR EJERCICIO");
        btnIniciar.setVisibility(View.VISIBLE);
        btnReset.setVisibility(View.VISIBLE);
        btnPausar.setVisibility(View.INVISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        btnIniciar.setVisibility(View.VISIBLE);
        btnIniciar.setText("INICIAR EJERCICIO");
        btnPausar.setVisibility(View.VISIBLE);
        instrucciones.setText("LISTO?");
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        contador.setText(timeLeftFormatted);
    }

    @Override
    public void onBackPressed() {
        // Detener la actividad
        if (respiracion != null) {
            respiracion.stop();
            respiracion.release();
            respiracion = null;
        }
        finish();
    }

}
