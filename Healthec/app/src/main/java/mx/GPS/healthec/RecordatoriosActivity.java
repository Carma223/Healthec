package mx.GPS.healthec;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RecordatoriosActivity extends AppCompatActivity {
    private Button miInfoBtn, citasBtn, medicamentosBtn;
    private ImageButton menuBtn;
    private TextView titleTxt;
    private TableLayout citasTable;

    private static final String[] MEDICAMENTOS = {"Ibuprofeno", "Paracetamol", "Aspirina", "Amoxicilina", "Omeprazol", "Diazepam"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);

        // Enlazar vistas con variables
        miInfoBtn = findViewById(R.id.button3);
        citasBtn = findViewById(R.id.button4);
        medicamentosBtn = findViewById(R.id.btnMedicamentos);
        menuBtn = findViewById(R.id.imageButton5);
        titleTxt = findViewById(R.id.textView2);
        citasTable = findViewById(R.id.tableLayout);

        // Definir listener para el botón "Mi Información"
        miInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar el código que se ejecutará al hacer clic en el botón
                Toast.makeText(RecordatoriosActivity.this, "Presionaste el botón Mi Información", Toast.LENGTH_SHORT).show();
            }
        });

        // Definir listener para el botón "Citas"
        citasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar el código que se ejecutará al hacer clic en el botón
                Toast.makeText(RecordatoriosActivity.this, "Presionaste el botón Citas", Toast.LENGTH_SHORT).show();
            }
        });

        // Definir listener para el botón "Medicamentos"
        Button btnMedicamentos = findViewById(R.id.btnMedicamentos);

        btnMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RecordatoriosActivity.this);
                builder.setTitle("Medicamentos")
                        .setItems(MEDICAMENTOS, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Aquí puedes agregar la lógica para manejar la selección del usuario
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        // Definir listener para el botón del menú
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se agregara el código que se ejecutará al hacer clic en el botón del menú
                Toast.makeText(RecordatoriosActivity.this, "Presionaste el botón del menú", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
