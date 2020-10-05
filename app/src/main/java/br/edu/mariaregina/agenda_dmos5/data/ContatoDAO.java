package br.edu.mariaregina.agenda_dmos5.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.edu.mariaregina.agenda_dmos5.model.Contato;


public class ContatoDAO {

    private static SQLiteDatabase mDatabase;
    private static SQLiteHelper mHelper;

    public ContatoDAO(Context context) {
        mHelper = new SQLiteHelper(context);
    }

    public static List<Contato> buscaTodos(){
        List<Contato> mLista = new ArrayList<>();
        Contato mContato = null;


        Cursor mCursor;


        mDatabase = mHelper.getReadableDatabase();


        String colunas[] = new String[] {
                SQLiteHelper.COLUMN_NOME,
                SQLiteHelper.COLUMN_SOBRENOME,
                SQLiteHelper.COLUMN_TELEFONEFIXO,
                SQLiteHelper.COLUMN_TELEFONECELULAR,
                SQLiteHelper.COLUMN_FAVORITO
        };


        mCursor = mDatabase.query(
                SQLiteHelper.TABLE_NAME,
                colunas,
                null,
                null,
                null,
                null,
                SQLiteHelper.COLUMN_NOME
        );



        while (mCursor.moveToNext()){
            mContato = new Contato(
                    mCursor.getString(0),
                    mCursor.getString(1)
            );
            if(mCursor.getInt(2) == 1)
                mContato.doFavotite();

            mLista.add(mContato);
        }

        mCursor.close();
        mDatabase.close();
        return mLista;
    }

    public static Collection<Contato> getInstante() {
        return null;
    }


    public void adiciona(Contato contato){
        mDatabase = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_NOME, contato.getNome());
        values.put(SQLiteHelper.COLUMN_SOBRENOME, contato.getSobrenome());
        values.put(SQLiteHelper.COLUMN_TELEFONEFIXO, contato.getTelefonefixo());
        values.put(SQLiteHelper.COLUMN_TELEFONECELULAR, contato.getTelefonecelular());
        if(contato.isFavorito()){
            values.put(SQLiteHelper.COLUMN_FAVORITO, 1);
        }else{
            values.put(SQLiteHelper.COLUMN_FAVORITO, 0);
        }

        mDatabase.insert(SQLiteHelper.TABLE_NAME, null, values);
        mDatabase.close();
    }


    public void atualiza(Contato contato){
        mDatabase = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_NOME, contato.getNome());
        values.put(SQLiteHelper.COLUMN_SOBRENOME, contato.getSobrenome());
        values.put(SQLiteHelper.COLUMN_TELEFONEFIXO, contato.getTelefonefixo());
        values.put(SQLiteHelper.COLUMN_TELEFONECELULAR, contato.getTelefonecelular());
        if(contato.isFavorito()){
            values.put(SQLiteHelper.COLUMN_FAVORITO, 1);
        }else{
            values.put(SQLiteHelper.COLUMN_FAVORITO, 0);
        }

        String where = SQLiteHelper.COLUMN_NOME + " = '" + contato.getNome() + "'";
        mDatabase.update(SQLiteHelper.TABLE_NAME, values, where, null);
        mDatabase.close();
    }
}
