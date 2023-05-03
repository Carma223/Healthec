package mx.GPS.healthec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

public class RegistroActivity extends AppCompatActivity {
    Button btn_registroAceptar;
    EditText edt_emailRegistro, edt_passwordRegistro, edt_nombreRegistro;

    FirebaseDatabase database;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btn_registroAceptar = findViewById(R.id.btn_registroAceptar);
        edt_emailRegistro = findViewById(R.id.edt_correoRegistro);
        edt_passwordRegistro = findViewById(R.id.edt_passwordRegistro);
        edt_nombreRegistro = findViewById(R.id.edt_nombreRegistro);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);

        //Bases de datos
        DataBaseHealthec dataBaseHealthec = new DataBaseHealthec(RegistroActivity.this);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        /*if( account != null ){
            UserModel usuario;
            try{
                usuario = new UserModel(-1, account.getEmail(), null, account.getDisplayName());
                Toast.makeText(RegistroActivity.this, usuario.toString(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(RegistroActivity.this, "Error con la cuenta de google",
                        Toast.LENGTH_SHORT).show();
                usuario = new UserModel(-1, "error", "error", "error");
            }

            boolean success = dataBaseHealthec.addOne(usuario);

            try{
                if(success){
                    finish();
                    startActivity( new Intent(RegistroActivity.this, MenuActivity.class));
                }
            }catch (Exception e){
                Toast.makeText(RegistroActivity.this, "No se pudo registrar correctamente",
                        Toast.LENGTH_LONG).show();
            }

        }*/

        btn_registroAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();//Se crea la instancia de la base de datos.
                DatabaseReference ref = database.getReference().child("usuarios"); //Se crea una referencia de la tabla usuarios
                ref.runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        Long currentId = mutableData.child("lastId").getValue(Long.class); // Se obtiene el dato del ultimo id en la tabla lastId
                        if (currentId == null) {
                            currentId = 0L;// Si no existe se le asigna un 0
                        }
                        currentId++; // se incrementa el id
                        //Se crea el usuario con los campos de texto del layout
                        UserModel usuario = new UserModel(currentId, edt_emailRegistro.getText().toString(),edt_passwordRegistro.getText().toString(), edt_nombreRegistro.getText().toString());

                        mutableData.child("lastId").setValue(currentId);//Se almacena en la tabla lasId el último id, en caso de que fuera el primero sería 1.
                        DatabaseReference userRef = ref.push(); //se utiliza el método push() para crear un nuevo nodo con una clave única dentro de la referencia ref.

                        userRef.child("id").setValue(currentId); //Finalmente, se asignan los valores correspondientes a las propiedades del nodo creado.
                        userRef.child("name").setValue(usuario.getNombre());
                        userRef.child("email").setValue(usuario.getEmail());
                        userRef.child("password").setValue(usuario.getPassword());

                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                        if (committed) {
                            Log.d("Firebase", "Transaction completed");
                            finish();
                            startActivity( new Intent(RegistroActivity.this, MenuActivity.class));
                        } else {
                            Log.d("Firebase", "Transaction aborted");
                            Toast.makeText(RegistroActivity.this, "No se pudo registrar correctamente",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

               /* try{
                boolean success = dataBaseHealthec.addOne(usuario);
                usuariosRef.child("usuario1").setValue(usuario);
                    if(success){
                        finish();
                        startActivity( new Intent(RegistroActivity.this, MenuActivity.class));
                    }
                }catch (Exception e){
                    Toast.makeText(RegistroActivity.this, "No se pudo registrar correctamente",
                            Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }

}