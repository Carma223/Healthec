package mx.GPS.healthec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHealthec extends SQLiteOpenHelper {

    public static final String TABLA_USUARIO = "TABLA_USUARIO";
    public static final String COLUMNA_USUARIO_CORREO = "USUARIO_CORREO";
    public static final String COLUMNA_USUARIO_CLAVE = "USUARIO_CLAVE";
    public static final String COLUMNA_USUARIO_ID = "USUARIO_ID";

    public static final String TABLA_RECORDATORIOS = "TABLA_RECORDATORIOS";
    public static final String COLUMNA_RECORDATORIOS_CLINICA = "COLUMNA_RECORDATORIOS_CLINICA";
    public static final String COLUMNA_RECORDATORIOS_MEDICO = "COLUMNA_RECORDATORIOS_MEDICOS";
    public static final String COLUMNA_RECORDATORIOS_FECHAHORA = "COLUMNA_RECORDATORIOS_FECHAHORA";
    public static final String COLUMNA_RECORDATORIOS_ID = "COLUMNA_RECORDATORIOS_ID";
    public static final String COLUMNA_HORARIOSUEÑO_ID = "COLUMNA_HORARIOSUEÑO_ID";
    public static final String TABLA_HORARIOSUEÑO = "TABLA_HORARIOSUEÑO ";
    public static final String COLUMNA_HORARIOSUEÑO_DIA = "COLUMNA_HORARIOSUEÑO_DIA";
    public static final String COLUMNA_HORARIOSUEÑO_HORA = "COLUMNA_HORARIOSUEÑO_HORA";
    public static final String TABLA_RECETARIOS = "TABLA_RECETARIOS";
    public static final String COLUMNA_RECETARIOS_ID = "COLUMNA_RECETARIOS_ID";
    public static final String COLUMNA_RECETARIOS_RECETA = "COLUMNA_RECETARIOS_RECETA";
    public static final String COLUMNA_RECETARIOS_PASOS = "COLUMNA_RECETARIOS_PASOS";
    public static final String TABLA_CONSEJOS = "TABLA_CONSEJOS";
    public static final String COLUMNA_CONSEJOS_ID = "COLUMNA_CONSEJOS_ID";
    public static final String COLUMNA_CONSEJOS_DESCRIPCION = "COLUMNA_CONSEJOS_DESCRIPCION";


    public DataBaseHealthec(@Nullable Context context) {
        super(context, "healthec.db", null, 1);
    }

    //Esto es llamado la primera vez que la base de datos es accedida. Aqui va el codigo para crear
    //una nueba db
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUsuario = "CREATE TABLE "+ TABLA_USUARIO + " (" + COLUMNA_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMNA_USUARIO_CORREO + " TEXT, " + COLUMNA_USUARIO_CLAVE + " TEXT)";
        String createTableRecordatorios =  "CREATE TABLE "+ TABLA_RECORDATORIOS + " (" + COLUMNA_RECORDATORIOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMNA_RECORDATORIOS_MEDICO + " TEXT, " + COLUMNA_RECORDATORIOS_FECHAHORA + " TEXT,"+COLUMNA_RECORDATORIOS_CLINICA+"TEXT)";
        String createTableRecetarios = "CREATE TABLE " + TABLA_RECETARIOS + " (" + COLUMNA_RECETARIOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMNA_RECETARIOS_RECETA + " TEXT, " + COLUMNA_RECETARIOS_PASOS + " TEXT)";
        String createTableHorarioSueño = "CREATE TABLE " + TABLA_HORARIOSUEÑO + "( " + COLUMNA_HORARIOSUEÑO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMNA_HORARIOSUEÑO_DIA + " TEXT, " + COLUMNA_HORARIOSUEÑO_HORA + " TEXT)";
        String createTableConsejos = "CREATE TABLE " + TABLA_CONSEJOS + " (" + COLUMNA_CONSEJOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMNA_CONSEJOS_DESCRIPCION + " TEXT)";
        db.execSQL(createTableUsuario);
        db.execSQL(createTableConsejos);
        db.execSQL(createTableRecetarios);
        db.execSQL(createTableRecordatorios);
        db.execSQL(createTableHorarioSueño);
    }
    //Este metodo es llamado si la version de la base de datos cambia. Previene que los usuarios que
    //tengan una version anterior de la bd creasheen cuando se hacen cambios a la db
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addOne (UserModel user ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMNA_USUARIO_CORREO, user.getEmail());
        cv.put(COLUMNA_USUARIO_CLAVE, user.getPassword());

        long insert = db.insert(TABLA_USUARIO, null , cv );
        if( insert == -1){
            return false;
        } else {
            return true;
        }

    }
    //Metodo para obtener todos los registros de una tabla en una Lista.
    public List<UserModel> getEveryone() {
        List<UserModel> returnList = new ArrayList<>();
        //Toma la información de la db
        String queryString = "SELECT * FROM " + TABLA_USUARIO;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null); //el cursor es el set de resultados

        if(cursor.moveToFirst()){
            //Se hace un bucle a traves del cursor y crea un nuevo objeto de UserModel los cuales se pondran en la lista que se retorna.
            do{
                int userID = cursor.getInt(0);
                String userEmail = cursor.getString(1);
                String userPassowrd = cursor.getString(2);

                UserModel user = new UserModel(userID, userEmail, userPassowrd);
                returnList.add(user);

            } while (cursor.moveToNext());
        } else {
            //Fallo, no anade nada a la lista
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean Exists( UserModel user ){
        String queryString = "SELECT " + COLUMNA_USUARIO_ID + " FROM " + TABLA_USUARIO + "WHERE " + COLUMNA_USUARIO_CORREO + " = " + user.getEmail() +
                " AND " + COLUMNA_USUARIO_CLAVE + " = " + user.getPassword();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        boolean exist;

        if( cursor.moveToFirst()){
            exist = true;
        } else {
            exist = false;
        }

        cursor.close();
        db.close();

        return exist;
    }
}
