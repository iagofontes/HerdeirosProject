package br.com.fontes.cadastropessoasfatecipinoite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 26/05/17.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "pessoas.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_PESSOA = "pessoa";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_ENDERECO = "endereco";
    public static final String COLUMN_TELEFONE = "telefone";
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (_id INTEGER PRIMARY KEY autoincrement, " +
                "%s VARCHAR(200), %s VARCHAR(200), %s VARCHAR(200))", TABLE_PESSOA, COLUMN_NOME,
                COLUMN_TELEFONE, COLUMN_ENDERECO));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
