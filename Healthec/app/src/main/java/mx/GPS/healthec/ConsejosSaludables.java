package mx.GPS.healthec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConsejosSaludables extends AppCompatActivity {

    TextView txtvConsejo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos_saludables);

        txtvConsejo = (TextView)findViewById( R.id.txtvConsejo );
    }

    public void btnSiguienteClick (View v){

        txtvConsejo.setText("Hola el consejo de hoy es comer frutas y verduras te hace una persona mas saludable");

    }

}