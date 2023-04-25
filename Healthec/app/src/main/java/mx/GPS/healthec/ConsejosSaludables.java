package mx.GPS.healthec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ConsejosSaludables extends AppCompatActivity {

    TextView txtvConsejo;

    ImageView imgvConsejo;

    ProgressBar prgsbImagenes;

    static int idSwitch=1;
    static int progess = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos_saludables);

        txtvConsejo = (TextView)findViewById( R.id.txtvConsejo );
        imgvConsejo = (ImageView) findViewById(R.id.imgvConsejo);
        prgsbImagenes = findViewById(R.id.prgsbImagenes);
    }

    public void btnSiguienteClick (View v){

        progess+=10;
        idSwitch++;


        txtvConsejo.setText("Hola el consejo de hoy es comer frutas y verduras te hace una persona mas saludable");


        prgsbImagenes.setProgress(progess,true);

        if(progess==110){
            progess=0;
            idSwitch = 1;
            prgsbImagenes.setProgress(progess,true);
        }
        switch (idSwitch){

            case 1:  imgvConsejo.setImageResource(R.drawable.saludconsejo);
                break;
            case 2:  imgvConsejo.setImageResource(R.drawable.saludconsejo2);
                break;
            case 3:  imgvConsejo.setImageResource(R.drawable.saludconsejo3);
                break;
            case 4:  imgvConsejo.setImageResource(R.drawable.saludconsejo4);
                break;
            case 5:  imgvConsejo.setImageResource(R.drawable.saludconsejo5);
                break;
            case 6:  imgvConsejo.setImageResource(R.drawable.saludconsejo6);
                break;
            case 7:  imgvConsejo.setImageResource(R.drawable.saludconsejo7);
                break;
            case 8:  imgvConsejo.setImageResource(R.drawable.saludconsejo8);
                break;
            case 9:  imgvConsejo.setImageResource(R.drawable.saludconsejo9);
                break;
            case 10:  imgvConsejo.setImageResource(R.drawable.saludconsejo10);
                break;
            case 11:  imgvConsejo.setImageResource(R.drawable.saludconsejo11);
                break;


        }


    }

}