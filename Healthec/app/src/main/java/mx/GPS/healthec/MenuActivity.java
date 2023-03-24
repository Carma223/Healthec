package mx.GPS.healthec;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnModuloSueñoClick (){
        startActivity(new Intent( this, SleepActivity.class));
    }

    public void btnModuloRecetasClick (){
        startActivity(new Intent( this, RecetasActivity.class));
    }

    public void btnModuloRecordatorioClick(){
        startActivity(new Intent( this, RecetasActivity.class));
    }

    public void btnModuloMeditacionClick(){
        startActivity(new Intent( this, MeditacionActivity.class));
    }
}