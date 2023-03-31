package mx.GPS.healthec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    Button btn_registroAceptar;
    EditText edt_emailRegistro, edt_passwordRegistro, edt_nombreRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btn_registroAceptar = findViewById(R.id.btn_registroAceptar);
        edt_emailRegistro = findViewById(R.id.edt_correoRegistro);
        edt_passwordRegistro = findViewById(R.id.edt_correoRegistro);
        edt_nombreRegistro = findViewById(R.id.edt_correoRegistro);

        btn_registroAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel usuario;
                try{
                    usuario = new UserModel(-1, edt_nombreRegistro.getText().toString(),
                            edt_emailRegistro.getText().toString(),
                            edt_nombreRegistro.getText().toString());
                    Toast.makeText(RegistroActivity.this, usuario.toString(), Toast.LENGTH_SHORT).show();
                } catch( Exception e){
                    Toast.makeText(RegistroActivity.this, "Es necesario rellenar todos los campos",
                            Toast.LENGTH_SHORT).show();
                    usuario = new UserModel(-1, "error", "error", "error");
                }
                DataBaseHealthec dataBaseHealthec = new DataBaseHealthec(RegistroActivity.this);

                boolean success = dataBaseHealthec.addOne(usuario);

                try{
                    if(success){
                        startActivity( new Intent(RegistroActivity.this, MenuActivity.class));
                    }
                }catch (Exception e){
                    Toast.makeText(RegistroActivity.this, "No se pudo registrar correctamente",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}