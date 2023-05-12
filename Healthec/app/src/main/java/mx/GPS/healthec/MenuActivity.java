package mx.GPS.healthec;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MenuActivity extends AppCompatActivity {

    private ImageView perfil;

    private TextView txtvFrase;

    private DatabaseReference mDataBase;

    private Random azar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        perfil = findViewById(R.id.imgvPerfil);
        perfil.setImageResource(R.drawable.saludconsejo);
        txtvFrase = findViewById(R.id.txtvFrase);

        mDataBase = FirebaseDatabase.getInstance().getReference();

        azar = new Random();

       int fraseAzar = azar.nextInt(10) +1;



        mDataBase.child("frases").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    // String consejo = snapshot.getValue().toString();
                    String frase = snapshot.child(fraseAzar+"").getValue().toString();
                    txtvFrase.setText(frase);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public void btnModuloSueñoClick (View v){
        Intent intent = new Intent(MenuActivity.this, SleepActivity.class);
        startActivity(intent);
    }

    public void btnModuloRecetasClick (View v){
        startActivity(new Intent( MenuActivity.this, IngredientesActivity.class));
    }

    public void btnModuloRecordatorioClick(View v){
        startActivity(new Intent( MenuActivity.this, RecordatoriosActivity.class));
    }

    public void btnModuloMeditacionClick(View v){
        startActivity(new Intent( MenuActivity.this, MeditacionActivity.class));
    }
}