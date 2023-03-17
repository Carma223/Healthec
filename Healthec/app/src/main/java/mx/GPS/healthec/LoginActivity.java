package mx.GPS.healthec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //Referencia a los botones y otros controles en el layout
    Button btn_registrar, btn_ingresar, btn_recuperar;
    EditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Se le asigna a las variables el elemento creado por la computadora en el layout---------//
        btn_ingresar = findViewById(R.id.btn_ingresar);
        btn_registrar = findViewById(R.id.btn_registrar);
        btn_recuperar = findViewById(R.id.btn_recuperar);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        //Listeners de los botones----------------------------------------------------------------//
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel usuario;
                try{
                    usuario = new UserModel(-1, et_email.getText().toString(),
                            et_password.getText().toString());
                    Toast.makeText(LoginActivity.this, usuario.toString(), Toast.LENGTH_SHORT).show();
                } catch( Exception e){
                    Toast.makeText(LoginActivity.this, "Es necesario rellenar todos los campos",
                            Toast.LENGTH_SHORT).show();
                    usuario = new UserModel(-1, "error", "error");
                }
                DataBaseHealthec dataBaseHealthec = new DataBaseHealthec(LoginActivity.this);

                boolean exist = dataBaseHealthec.addOne(usuario);

                if( exist ){
                    //codigo para entrar al menu principal
                } else {
                    //codigo para representar que el usuario no existe
                }
            }
        });
        //----------------------------------------------------------------------------------------//
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserModel usuario;
                try{
                     usuario = new UserModel(-1, et_email.getText().toString(),
                            et_password.getText().toString());
                    Toast.makeText(LoginActivity.this, usuario.toString(), Toast.LENGTH_SHORT).show();
                } catch( Exception e){
                    Toast.makeText(LoginActivity.this, "Es necesario rellenar todos los campos",
                            Toast.LENGTH_SHORT).show();
                    usuario = new UserModel(-1, "error", "error");
                }
                DataBaseHealthec dataBaseHealthec = new DataBaseHealthec(LoginActivity.this);

                boolean success = dataBaseHealthec.addOne(usuario);

                Toast.makeText(LoginActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();

            }
        });
        //----------------------------------------------------------------------------------------//
        btn_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}