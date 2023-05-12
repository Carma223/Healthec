package mx.GPS.healthec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.GPS.healthec.Adapter.IngredientesAdapter;
import mx.GPS.healthec.modelos.Ingredientes;

public class IngredientesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    //Constantes
    public final int [] imagenes = {0, R.drawable.tomate, R.drawable.palta,
                                    R.drawable.fresa, R.drawable.lechuga,
                                    R.drawable.carne, R.drawable.pollo,
                                    R.drawable.jalapeno, R.drawable.pez};
    SearchView srcRecetas;
    RecyclerView recvRecetas;
    DatabaseReference mDatabase;
    IngredientesAdapter iAdapter;
    FirebaseFirestore mFirestore;
    private ArrayList<Ingredientes> mIngredientesList = new ArrayList<>();
    DataBaseHealthec db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientes);
        srcRecetas = findViewById(R.id.srcvIngredientes);


        recvRecetas = findViewById(R.id.recvIngredientes);
        recvRecetas.setLayoutManager(new LinearLayoutManager(this));
        actualizaImagenes();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        getIngredientesFromFirebase();
        srcRecetas.setOnQueryTextListener(this);
        iAdapter = new IngredientesAdapter(mIngredientesList);

    }

    private void getIngredientesFromFirebase(){
        mDatabase.child("Ingredientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()){


                        String Titulo = ds.child("Titulo").getValue().toString();
                        int Imagen = (int)(long) ds.child("Imagen").getValue();
                        int clave = (int)(long) ds.child("clave").getValue();
                        mIngredientesList.add(new Ingredientes(Titulo,Imagen,clave));
                    }

                    iAdapter = new IngredientesAdapter(mIngredientesList);
                    recvRecetas.setAdapter(iAdapter);
                    iAdapter.setOnItemClickListener(new IngredientesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Ingredientes ingredientes) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(IngredientesActivity.this);
                            builder.setTitle(ingredientes.getTitulo()).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        iAdapter = new IngredientesAdapter(mIngredientesList);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        iAdapter.filtrado(s);
        return false;
    }
    public void actualizaImagenes(){
        mDatabase = FirebaseDatabase.getInstance().getReference("Ingredientes");
        for(int i = 1; i < imagenes.length; i++) {
            Map<String, Object> imagenMap = new HashMap<>();
            imagenMap.put("Imagen", imagenes[i]);
            mDatabase.child(i+"").updateChildren(imagenMap);
        }
    }
}