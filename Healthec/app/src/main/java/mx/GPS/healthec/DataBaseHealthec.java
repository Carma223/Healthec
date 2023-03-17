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

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_ID = "ID";

    public DataBaseHealthec(@Nullable Context context) {
        super(context, "healthec.db", null, 1);
    }

    //Esto es llamado la primera vez que la base de datos es accedida. Aqui va el codigo para crear
    //una nueba db
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE "+ USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT)";
        db.execSQL(createTableStatement);
    }
    //Este metodo es llamado si la version de la base de datos cambia. Previene que los usuarios que
    //tengan una version anterior de la bd creasheen cuando se hacen cambios a la db
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addOne (UserModel user ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_EMAIL, user.getEmail());
        cv.put(COLUMN_USER_PASSWORD, user.getPassword());

        long insert = db.insert(USER_TABLE, null , cv );
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
        String queryString = "SELECT * FROM " + USER_TABLE;

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
        String queryString = "SELECT " + COLUMN_ID + " FROM " + USER_TABLE + "WHERE " + COLUMN_USER_EMAIL + " = " + user.getEmail() +
                " AND " + COLUMN_USER_PASSWORD + " = " + user.getPassword();

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
