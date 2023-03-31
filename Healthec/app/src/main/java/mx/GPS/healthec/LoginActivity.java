package mx.GPS.healthec;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    //Referencia a los botones y otros controles en el layout
    Button btn_registrar, btn_ingresar;
    EditText edt_email, edt_password;
    ImageView google_login;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    //METODO ONCREATE, SE EJECUTA CUNADO LOGINACTIVITY SE INICIA
    @Override
    //Llama al método onCreate() de la clase base Activity utilizando el parámetro savedInstanceState.
    //este método establece el diseño de la actividad y busca los elementos de la interfaz de
    // usuario para poder interactuar con ellos posteriormente en la aplicación.

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Se le asigna a las variables el elemento creado por la computadora en el layout---------//
        btn_ingresar = findViewById(R.id.btn_ingresar);
        btn_registrar = findViewById(R.id.btn_registrar);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);

        google_login = findViewById(R.id.google);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);

        //Listeners de los botones----------------------------------------------------------------//
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SE DECLARA UNA INSTANCIA DE UserModel llamada usuario
                UserModel usuario;
                //Se intenta crear una nueva instancia de UserModel utilizando los valores ingresados
                // en los campos de correo electrónico y contraseña de la interfaz de usuario.
                // Si se produce una excepción, se muestra un mensaje de error y se crea una instancia de
                // UserModel con valores predeterminados ("error" y "-1").
                try{
                    usuario = new UserModel(-1, edt_email.getText().toString(),
                            edt_password.getText().toString());
                    Toast.makeText(LoginActivity.this, usuario.toString(), Toast.LENGTH_SHORT).show();
                } catch( Exception e){
                    Toast.makeText(LoginActivity.this, "Es necesario rellenar todos los campos",
                            Toast.LENGTH_SHORT).show();
                    usuario = new UserModel(-1, "error", "error");
                }

                //Se declara una instancia de DataBaseHealthec, que es una clase que maneja la base de datos
                // SQL utilizada por la aplicación.
                DataBaseHealthec dataBaseHealthec = new DataBaseHealthec(LoginActivity.this);


                //Se llama al método "addOne" de la instancia de DataBaseHealthec, pasando como argumento
                // la instancia de UserModel creada anteriormente. Este método intenta agregar el usuario a

                // la base de datos y devuelve un valor booleano que indica si la operación fue exitosa o no.
                try{
                boolean exist = dataBaseHealthec.exists(usuario);
                    if( exist ){
                        //codigo para entrar al menu principal donde se encuentran todas las opciones
                        finish();
                        startActivity( new Intent(LoginActivity.this, MenuActivity.class));
                    }
                } catch (Exception e){
                    //codigo para representar que el usuario no existe
                    Toast.makeText(LoginActivity.this, "No existe ninguna cuenta con esos datos",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        //----------------------------------------------------------------------------------------//
        btn_registrar.setOnClickListener(new View.OnClickListener() {

            //METODO ONCLICK este método se encarga de crear un nuevo usuario con los valores
            // ingresados en la interfaz de usuario y agregarlo a la base de datos de la aplicación.
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
        //----------------------------------------------------------------------------------------//
        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });

    }

    private void SignIn() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                HomeActivity();
            } catch (ApiException e) {
                Toast.makeText(this, "Error 1", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void HomeActivity() {
        finish();
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(intent);
    }
}