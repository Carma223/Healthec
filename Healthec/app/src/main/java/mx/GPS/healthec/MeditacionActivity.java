package mx.GPS.healthec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MeditacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditacion);
        ImageView imageView = findViewById(R.id.imageView4);
        float alphaValue = 0.5f; // establece el valor de transparencia entre 0 (totalmente transparente) y 1 (totalmente opaco)
        imageView.setAlpha(alphaValue);

    }

    //boton para entrar a la seccion conciliacion del sueño
    public void btnSueño( View v ) { setContentView(R.layout.activity_sueño);

    }
    //boton para entrar a la seccion alvio de ansiendad
    public void btnAnsiedad( View v ) { setContentView(R.layout.activity_ansiedad);

    }
    //boton para entrar a la seccion relajacion vespertina
    public void btnRelajacion( View v ) { setContentView(R.layout.activity_relajacion);

    }
    //boton para entrar a la seccion de alivio de sobrepensamiento
    public void btnAlivio( View v ) {setContentView(R.layout.activity_alivio);

    }


    //boton para regresar al menu principal
    public void btnRegresar( View v ) {
        setContentView(R.layout.activity_menu);
    }

}