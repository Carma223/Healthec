package mx.GPS.healthec;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btnModuloSueñoClick (){
        Intent intent = new Intent(MenuActivity.this, SleepActivity.class);
        startActivity(intent);
    }

    public void btnModuloRecetasClick (){
        startActivity(new Intent( MenuActivity.this, RecetasActivity.class));
    }

    public void btnModuloRecordatorioClick(){
        startActivity(new Intent( MenuActivity.this, RecordatoriosActivity.class));
    }

    public void btnModuloMeditacionClick(){
        startActivity(new Intent( MenuActivity.this, MeditacionActivity.class));
    }
}