package mx.GPS.healthec;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class RecordatoriosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private Button miInfoBtn, citasBtn, medicamentosBtn;
    private ImageButton menuBtn;
    private TextView titleTxt;
    private ListView list;
    private TextView agregaractividad;
    private EditText actividad;
    private List<Modelo>mLista= new ArrayList<>();
    Modelo modelo;
    ListAdapter mAdapter;
    String nomarchivo;



    private static final String[] MEDICAMENTOS = {
            "Ibuprofeno - se usa para aliviar el dolor, la fiebre y la inflamación.\n",
            "Paracetamol - se usa para aliviar el dolor y la fiebre.\n",
            "Aspirina - se usa para aliviar el dolor, la fiebre y la inflamación. También se puede usar para prevenir ataques cardíacos y accidentes cerebrovasculares.\n",
            "Amoxicilina - se usa para tratar infecciones bacterianas, como la neumonía, la faringitis estreptocócica y las infecciones del oído, la nariz y la garganta.\n",
            "Omeprazol - se usa para tratar el reflujo gastroesofágico (ERGE), úlceras gástricas y duodenales, y otros trastornos gastrointestinales.\n",
            "Diazepam - se usa para tratar la ansiedad, el insomnio, los espasmos musculares y las convulsiones.\n",
            "Clonazepam - se usa para tratar la epilepsia y los trastornos de ansiedad, como el trastorno de pánico.\n",
            "Ketorolaco - se usa para aliviar el dolor moderado a intenso, como el dolor de cabeza, el dolor menstrual y el dolor después de una cirugía o lesión.\n",
            "Atorvastatina - se usa para reducir el colesterol en la sangre.\n",
            "Metformina - se usa para tratar la diabetes tipo 2.\n",
            "Metoprolol - se usa para tratar la presión arterial alta y la insuficiencia cardíaca.\n",
            "Sertralina - se usa para tratar la depresión y los trastornos de ansiedad.\n",
            "Alprazolam - se usa para tratar la ansiedad y los trastornos de pánico.\n",
            "Losartan - se usa para tratar la presión arterial alta y la insuficiencia cardíaca.\n",
            "Ondansetrón - se usa para prevenir las náuseas y los vómitos después de la quimioterapia o la cirugía.\n",
            "Furosemida - se usa para tratar la retención de líquidos en pacientes con insuficiencia cardíaca, cirrosis hepática y otros trastornos médicos.\n",
            "Lorazepam - se usa para tratar la ansiedad y los trastornos de pánico.\n",
            "Tramadol - se usa para aliviar el dolor moderado a intenso.\n"
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);
        agregaractividad= findViewById(R.id.agregaract);
        actividad=findViewById(R.id.act);
        list = findViewById(R.id.lista);
        list.setOnItemClickListener(this);
        list.setOnItemLongClickListener(this);

        recuperar();


        mAdapter = new  ListAdapter(RecordatoriosActivity.this, R.layout.item_row,mLista);
        list.setAdapter(mAdapter);


        agregaractividad.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                mLista.add(new Modelo(list.getCount()+1+"",actividad.getText().toString()));
                mAdapter = new ListAdapter(RecordatoriosActivity.this, R.layout.item_row,mLista);
                list.setAdapter(mAdapter);
                nomarchivo="Recordatorios.txt";

                grabar();
                actividad.setText("");
            }
        });

        // Enlazar vistas con variables
        medicamentosBtn = findViewById(R.id.btnMedicamentos);
        menuBtn = findViewById(R.id.ibtnAtras);
        titleTxt = findViewById(R.id.textView2);


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
                startActivity(new Intent( RecordatoriosActivity.this, MenuActivity.class));

            }
        });
    }

    private void recuperar(){
        boolean enco = false;
        String nomarchivo="Recordatorios.txt";

        String[] archivos = fileList();
        for(int f = 0; f<archivos.length;f++)
            if (nomarchivo.equals(archivos[f]))
                enco=true;
        if(enco==true){
            try{
                InputStreamReader archivo = new InputStreamReader(openFileInput(nomarchivo));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                while (linea != null){
                    mAdapter=new ListAdapter(RecordatoriosActivity.this,R.layout.item_row,mLista);
                    list.setAdapter(mAdapter);

                    mLista.add(new Modelo(list.getCount()+1+"",linea));
                    mAdapter=new ListAdapter(RecordatoriosActivity.this,R.layout.item_row,mLista);
                    list.setAdapter(mAdapter);

                    linea=br.readLine();
                }
                br.close();
                archivo.close();
            }catch (IOException e){
            }
        }else
        {
            Toast.makeText(this,"No hay recordatorios", Toast.LENGTH_SHORT).show();
        }
    }



    /*private void grabar() {

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nomarchivo, Activity.MODE_PRIVATE));
            archivo.write(actividad.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e){

        }
        Toast mensaje = Toast.makeText(this, "Los Datos Fueron Grabados",Toast.LENGTH_SHORT);
        mensaje.show();
    }*/

    private void grabar() {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nomarchivo, Activity.MODE_APPEND));  // Utiliza MODE_APPEND para agregar contenido al final del archivo
            archivo.write(actividad.getText().toString());
            archivo.write("\n");  // Agrega una nueva línea después de cada elemento para separarlos
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast mensaje = Toast.makeText(this, "Los Datos Fueron Grabados", Toast.LENGTH_SHORT);
        mensaje.show();
    }


    private void grabarEliminar() {
        if (nomarchivo == null) {
            nomarchivo = "Recordatorios.txt"; // Valor predeterminado
        }

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nomarchivo, Activity.MODE_PRIVATE));
            for (Modelo modelo : mLista) {
                archivo.write(modelo.getNombreactividad());
                archivo.write("\n");  // Agrega una nueva línea después de cada elemento para separarlos
            }
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Actividad seleccionada:"+position,Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this,SegundaActivity.class);
        //intent.putExtra("nombre", mAdapter.getItem(position).getNombreActividad());

        intent.putExtra("nombre", mAdapter.getItem(position).getNombreactividad());
        //startActivity(intent);
        startActivityForResult(intent,1);
    }*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Actividad seleccionada: " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SegundaActivity.class);
        intent.putExtra("nombre", mAdapter.getItem(position).getNombreactividad());
        startActivityForResult(intent, 1);
    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        eliminarElementoPorLongClick(position);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==1 && resultCode==RESULT_OK)
        {

        }
    }
    private void eliminarElementoPorLongClick(int posicion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar elemento")
                .setMessage("¿Deseas eliminar este elemento?")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mLista.remove(posicion);
                        mAdapter.notifyDataSetChanged();
                        Toast.makeText(RecordatoriosActivity.this, "Elemento eliminado", Toast.LENGTH_SHORT).show();
                        grabarEliminar();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }



}



