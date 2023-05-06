package mx.GPS.healthec;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecordatoriosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Button miInfoBtn, citasBtn, medicamentosBtn;
    private ImageButton menuBtn;
    private TextView titleTxt;
    private TableLayout citasTable;

    private static final String[] MEDICAMENTOS = {"Ibuprofeno", "Paracetamol", "Aspirina", "Amoxicilina", "Omeprazol", "Diazepam","Clonasempam",
            "Ketorolako"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);
        agregaractividad=findViewById(R.id.btnAgregar);
        actividad=findViewById(R.id.txtRecordatorio);
        list = findViewById(R.id.lista);
        list.setOnItemClickListener(this);

        mAdapter = new  ListAdapter(RecordatoriosActivity.this, R.layout.item_row,mLista);
        list.setAdapter(mAdapter);
        agregaractividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLista.add(new Modelo(list.getCount()+1+"",actividad.getText().toString()));
                mAdapter= new ListAdapter(RecordatoriosActivity.this, R.layout.item_row,mLista);
                list.setAdapter(mAdapter);
                actividad.setText("");
            }
        });

        // Enlazar vistas con variables
        miInfoBtn = findViewById(R.id.btnInformacion);
        citasBtn = findViewById(R.id.btnCitas);
        medicamentosBtn = findViewById(R.id.btnMedicamentos);
        menuBtn = findViewById(R.id.ibtnAtras);
        titleTxt = findViewById(R.id.textView2);


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

    private ListView list;
    private TextView agregaractividad;
    private EditText actividad;
    private List<Modelo>mLista= new ArrayList<>();
    Modelo modelo;
    ListAdapter mAdapter;


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Actividad seleccionada: "+i,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SegundaActivity.class);
        intent.putExtra("nombre", mAdapter.getItem(i).getNombreactividad());
        startActivityForResult(intent,1);

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1 && resultCode==RESULT_OK)
        {

        }
    }
}



class Modelo {

    private String prioridadactividad;
    private String nombreactividad;

    public Modelo() {
    }

    public Modelo(String prioridadactividad, String nombreactividad) {
        this.prioridadactividad = prioridadactividad;
        this.nombreactividad = nombreactividad;
    }

    public String getPrioridadactividad() {
        return prioridadactividad;
    }

    public void setPrioridadactividad(String prioridadactividad) {
        this.prioridadactividad = prioridadactividad;
    }

    public String getNombreactividad() {
        return nombreactividad;
    }

    public void setNombreactividad(String nombreactividad) {
        this.nombreactividad = nombreactividad;
    }
}

class ListAdapter extends ArrayAdapter <Modelo> {
    private List<Modelo> miLista;
    private Context mContext;
    private int resourceLayout;
    public ListAdapter(@NonNull Context context, int resource, List<Modelo> objects) {
        super(context, resource);
        this.miLista=objects;
        this.mContext=context;
        this.resourceLayout=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null)
            view= LayoutInflater.from(mContext).inflate(R.layout.item_row,null);

        Modelo modelo=miLista.get(position);
        TextView textoact= view.findViewById(R.id.txtactividad);
        textoact.setText(modelo.getNombreactividad());
        TextView textoprioridad=view.findViewById(R.id.txtprioridad);
        textoprioridad.setText(modelo.getPrioridadactividad());
        return view;
    }
}
