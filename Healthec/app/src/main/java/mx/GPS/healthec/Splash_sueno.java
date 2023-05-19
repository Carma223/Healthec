package mx.GPS.healthec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Splash_sueno extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 14000;
    private TextView txt1;
    private TextView txt2;
    private static final int DELAY = 7000; // 6 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_sueno);

        int orientation=getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_PORTRAIT){
            getSupportActionBar().hide();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else
            getSupportActionBar().show();
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);

        // Mostrar texto 1 durante 5 segundos
        txt1.setVisibility(View.VISIBLE);
        txt2.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Ocultar texto 1 y mostrar texto 2
                txt1.setVisibility(View.GONE);
                txt2.setVisibility(View.VISIBLE);
            }
        }, DELAY);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash_sueno.this, ConciliacionSueno.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}