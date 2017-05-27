package br.com.fontes.cadastropessoasfatecipinoite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 26/05/17.
 */

public class PessoaDAO {

    private Context context;

    public PessoaDAO(Context context){
        this.context = context;
    }

    public void inserir(Pessoa pessoa){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NOME, pessoa.getNome());
        cv.put(DBHelper.COLUMN_ENDERECO, pessoa.getEndereco());
        cv.put(DBHelper.COLUMN_TELEFONE, pessoa.getTelefone());
        db.insert(DBHelper.TABLE_PESSOA, null, cv);
        db.close();
        helper.close();
    }

    public List<Pessoa> listar(){

        List<Pessoa> pessoas = new LinkedList<Pessoa>();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projecao = {DBHelper.COLUMN_NOME, DBHelper.COLUMN_ENDERECO, DBHelper.COLUMN_TELEFONE};
        Cursor cursor = db.query(DBHelper.TABLE_PESSOA, projecao, null, null, null, null, null);

        while(cursor.moveToNext()){

            String nome = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NOME));
            String telefone = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TELEFONE));
            String endereco = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ENDERECO));
            Pessoa pessoa = new Pessoa(nome, endereco, telefone);
            pessoas.add(pessoa);
        }
        db.close();
        helper.close();
        return pessoas;
    }

}
