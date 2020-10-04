package br.edu.mariaregina.agenda_dmos5.data;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;

import java.util.ArrayList;
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
        Contato mSite;

        // O Cursos funciona como um ResultSet, ele possui uma tabela temporária com os
        // dados que foram recuperados do banco. O ponteiro inicialmente aponta para null.
        Cursor mCursor;

        //Aqui é feita uma conexão com o banco de dados com direito de leitura.
        mDatabase = mHelper.getReadableDatabase();

        // Para definirmos quais as colunas que desejamos e a ordem de apresentação, cria-se um
        // vetor de Strings com o nome de cada coluna que será devolvida na consulta.
        String colunas[] = new String[] {
                SQLiteHelper.COLUMN_NOME,
                SQLiteHelper.COLUMN_SOBRENOME,
                SQLiteHelper.COLUMN_TELEFONEFIXO,
                SQLiteHelper.COLUMN_TELEFONECELULAR,
                SQLiteHelper.COLUMN_FAVORITO
        };

        // A consulta no banco de dados é retornada para um Cursor. A query passada já é formatada
        // para facilitar a programação. Nesse momento estou indicando apenas o nome da tabela (
        // TABLE_NAME), as colunas da consulta e a ordenação (COLUMN_TITULO). Os demais argumentos
        // serão trabalhados no fututo.
        mCursor = mDatabase.query(
                SQLiteHelper.TABLE_NAME,
                colunas,
                null,
                null,
                null,
                null,
                SQLiteHelper.COLUMN_NOME
        );


        // O cursos consegue recuperar o tipo específico do dado, porém é preciso informar
        // qual a ordem da coluna iniciando de zero.
        // Após recuperar o dado ele é inserido na lista e devolvido como argumento.
        while (mCursor.moveToNext()){
            mSite = new Contato(
                    mCursor.getString(0),
                    mCursor.getString(1)
            );
            if(mCursor.getInt(2) == 1)
                mSite.doFavotite();

            mLista.add(mSite);
        }

        mCursor.close();
        mDatabase.close();
        return mLista;
    }


    // Mais fácil que consultar é salvar um dado, basta indicar em um ContentValues ou seja, em
    // um objeto que armazena chave e valor e solicitar que os dados sejam salvos no banco de
    // dados. Atenção pois o nome das colunas deve ser sempre o mesmo.
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


    // A única diferença entre salva um novo dado e editar um dado existente é a configuração
    // da clausula where que é feita em uma string.
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
