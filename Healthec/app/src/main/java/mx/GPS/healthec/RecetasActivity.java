package mx.GPS.healthec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.checkerframework.checker.units.qual.A;

import java.util.List;

public class RecetasActivity extends AppCompatActivity {
    //Constantes
    ListView lv_recetasList;
    ArrayAdapter recetasArrayAdapter;
    DataBaseHealthec db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);

        //ListView
        lv_recetasList = findViewById(R.id.lv_recetas);

        //Llenado del ViewList
        db = new DataBaseHealthec(RecetasActivity.this);
        recetasArrayAdapter = new ArrayAdapter<RecetasModel>(RecetasActivity.this, android.R.layout.simple_list_item_1, db.getRecipes());
        lv_recetasList.setAdapter(recetasArrayAdapter);

    }
}