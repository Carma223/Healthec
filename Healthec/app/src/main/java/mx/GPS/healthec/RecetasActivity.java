package mx.GPS.healthec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.GPS.healthec.Adapter.RecetasAdapter;
import mx.GPS.healthec.modelos.Ingredientes;
import mx.GPS.healthec.modelos.Recetas;

public class RecetasActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public final int [][] imagenes = {{0, R.drawable.spagueti, R.drawable.chilaquiles,
                                    R.drawable.sopafid, R.drawable.enchiladaroja,
                                    R.drawable.salsatomate},
                                    {0, R.drawable.pastaqueso, R.drawable.lasana,
                                     R.drawable.espaguetichipotle, R.drawable.espaguetiblanco,
                                     R.drawable.espaguetiespinaca},
                                    {0, R.drawable.gaspachofresa, R.drawable.manzanamicro,
                                     R.drawable.chia, R.drawable.tartapina,
                                     R.drawable.tartamanzana},
                                    {0, R.drawable.ensaladaatun, R.drawable.ensaladapollo,
                                     R.drawable.ensaladamandarina, R.drawable.ensaladaaguacate,
                                     R.drawable.ensaladatomate},
                                    {0, R.drawable.costillaadobo, R.drawable.costillachile,
                                     R.drawable.ribeye, R.drawable.calabazaarra,
                                     R.drawable.medallones},
                                    {0, R.drawable.hampollo, R.drawable.tacospollo,
                                     R.drawable.pollocazuela, R.drawable.pastapollo,
                                     R.drawable.palopollo},
                                    {0, R.drawable.chilaverdes, R.drawable.molleterojo,
                                    R.drawable.huevonido, R.drawable.huevoranchero,
                                    R.drawable.panfrances},
                                    {0, R.drawable.pescadoajillo, R.drawable.pescadotalla,
                                     R.drawable.pescadoculi, R.drawable.pescadoempa,
                                     R.drawable.pescadozar}};
    private int ingredienteClave;
    private RecyclerView recvRecetas;
    private SearchView srcvRecetas;
    DatabaseReference mDatabase;
    private RecetasAdapter rAdapter;
    private ArrayList<Recetas> mRecetasList = new ArrayList<Recetas>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);
        srcvRecetas = findViewById(R.id.srcvRecetas);
        recvRecetas = findViewById(R.id.recvRecetas);
        recvRecetas.setLayoutManager(new LinearLayoutManager(this));
        ingredienteClave = getIntent().getIntExtra("clave",0);
        actualizaImagenes();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getRecetasFromFirebase();
        srcvRecetas.setOnQueryTextListener(this);
    }

    private void getRecetasFromFirebase(){
        mDatabase.child("Ingredientes/"+ingredienteClave+"/Recetas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String Titulo = ds.child("Titulo").getValue().toString();
                        String Descripcion = ds.child("Descripcion").getValue().toString();
                        int Imagen = (int)(long) ds.child("Imagen").getValue();
                        int Clave = (int)(long) ds.child("Clave").getValue();
                        mRecetasList.add(new Recetas(Titulo,Descripcion,Imagen,Clave));
                    }
                    rAdapter = new RecetasAdapter(mRecetasList);
                    recvRecetas.setAdapter(rAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void actualizaImagenes(){
        mDatabase = FirebaseDatabase.getInstance().getReference("Ingredientes/"+ingredienteClave+"/Recetas");
        for(int i = 1; i < imagenes[ingredienteClave-1].length; i++){
            Map<String,Object> imagenesMap = new HashMap<>();
            imagenesMap.put("Imagen",imagenes[ingredienteClave-1][i]);
            mDatabase.child(i+"").updateChildren(imagenesMap);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        rAdapter.filtrado(s);
        return false;
    }
}