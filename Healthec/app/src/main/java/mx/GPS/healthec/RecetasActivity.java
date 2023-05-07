package mx.GPS.healthec;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.checkerframework.checker.units.qual.A;

import java.util.List;

public class RecetasActivity extends AppCompatActivity {
    //Constantes
    ListView lv_recetasList;
    ArrayAdapter recetasArrayAdapter;
    private int [] idRecetas;
    private String [] recetasTitulo;
    private String [] recetasPasos;
    private String [] recetasIngredientes;
    private int [] recetasImagen;


    DataBaseHealthec db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);

        //ListView
        lv_recetasList = findViewById(R.id.lv_recetas);

        //Llenado del ViewList
        db = new DataBaseHealthec(RecetasActivity.this);
        actualizarList();
        lv_recetasList = findViewById(R.id.lv_recetas);
        MiAdaptador adaptador = new MiAdaptador( this, recetasTitulo, recetasImagen );
        lv_recetasList.setAdapter(adaptador);
    }

    public void actualizarList (){
        List<RecetasModel> modeloRecetasList = db.getRecetas();
        idRecetas = new int [modeloRecetasList.size()];
        recetasTitulo = new String [modeloRecetasList.size()];
        recetasPasos = new String [modeloRecetasList.size()];
        recetasIngredientes = new String [modeloRecetasList.size()];
        recetasImagen = new int[modeloRecetasList.size()];
        for (int i = 0; i < modeloRecetasList.size(); i++){
            RecetasModel modeloRecetas = modeloRecetasList.get(i);
            idRecetas[i] = modeloRecetas.getIdReceta();
            recetasTitulo[i] = modeloRecetas.getNombreReceta();
            recetasPasos[i] = modeloRecetas.getPasosReceta();
            recetasIngredientes[i] = modeloRecetas.getIngredientesReceta();
            recetasImagen[i] = modeloRecetas.getImagenReceta();
        }
    }

    class MiAdaptador extends ArrayAdapter{
        private Context context;
        private String [] recetaTitulo;
        private String [] recetaPasos;
        private int [] recetasImagen;
        public MiAdaptador(Context c, String [] recetaTitulo, int [] recetasImagen ) {

            super(c, R.layout.list_recetas, R.id.txtvTituloReceta, recetaTitulo );
            context = c;
            this.recetaTitulo = recetaTitulo;
            this.recetasImagen = recetasImagen;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //LayoutInflater layoutInflater = LayoutInflater (context)
                convertView = layoutInflater.inflate(R.layout.list_recetas, parent, false);
            }
            ImageView imagen = convertView.findViewById(R.id.imgvRecetas);
            TextView titulo = convertView.findViewById(R.id.txtvTituloReceta);

            imagen.setImageResource( recetasImagen[position] );
            titulo.setText( recetasTitulo[position] );
            return super.getView(position, convertView, parent);

        }
    }




}