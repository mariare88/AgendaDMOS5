package br.edu.mariaregina.agenda_dmos5.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {





    public static final String DATABASE_NAME = "agenda_db";
    public static final int DATABASE_VERSION = 1;


    public static final String TABLE_NAME = "contato";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_SOBRENOME= "sobrenome";
    public static final String COLUMN_TELEFONEFIXO= "telefonefixo";
    public static final String COLUMN_TELEFONECELULAR = "telefonecelular";
    public static final String COLUMN_FAVORITO = "favorito";



    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (";
        sql += COLUMN_NOME + " TEXT NOT NULL, ";
        sql += COLUMN_SOBRENOME + " TEXT NOT NULL, ";
        sql += COLUMN_TELEFONEFIXO+ " TEXT NOT NULL, ";
        sql += COLUMN_TELEFONECELULAR + " TEXT NOT NULL, ";
        sql += COLUMN_FAVORITO + " INTEGER NOT NULL CHECK (" + COLUMN_FAVORITO + " IN (0,1) ) ";
        sql += ")";

        sqLiteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
