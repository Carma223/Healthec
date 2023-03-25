package mx.GPS.healthec;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btnModuloSueñoClick ( View v ){
        startActivity(new Intent( MenuActivity.this, SleepActivity.class));
    }

    public void btnModuloRecetasClick ( View v ){
        startActivity(new Intent( MenuActivity.this, RecetasActivity.class));
    }

    public void btnModuloRecordatorioClick( View v ){
        startActivity(new Intent( MenuActivity.this, RecordatoriosActivity.class));
    }

    public void btnModuloMeditacionClick( View v ){
        startActivity(new Intent( MenuActivity.this, MeditacionActivity.class));
    }
}