package br.com.fontes.cadastropessoasfatecipinoite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private EditText nomeEditText, enderecoEditText, telefoneEditText;
    private ListView listview;
    private ArrayAdapter<Pessoa> adapter;
    private List<Pessoa> pessoas;
    private PessoaDAO dao;
    private static int REQUEST_ADD_PESSOA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new PessoaDAO(this);

//        nomeEditText = (EditText) findViewById(R.id.nomeEditText);
//        enderecoEditText= (EditText) findViewById(R.id.enderecoEditText);
//        telefoneEditText = (EditText) findViewById(R.id.telefoneEditText);
        listview = (ListView) findViewById(R.id.listView);

//        pessoas = new ArrayList<Pessoa>();
        pessoas = dao.listar();
        adapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoas);
        listview.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AdicionaPessoaActivity.class);
                startActivityForResult(intent, REQUEST_ADD_PESSOA);

                /*String nome = nomeEditText.getEditableText().toString();
                String endereco = enderecoEditText.getEditableText().toString();
                String telefone = telefoneEditText.getEditableText().toString();
                Pessoa pessoa = new Pessoa(nome, endereco, telefone);
                pessoas.add(pessoa);
                dao.inserir(pessoa);
                adapter.notifyDataSetChanged();
                Snackbar.make(view, getString(R.string.pessoa_adicionada, nome), Snackbar.LENGTH_LONG).show();
                limparCampos();
                nomeEditText.requestFocus();*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ADD_PESSOA && resultCode == Activity.RESULT_OK){
            pessoas.clear();
            pessoas.addAll(dao.listar());
            adapter.notifyDataSetChanged();
        }
    }

    /*public void limparCampos(){
        nomeEditText.setText("");
        telefoneEditText.setText("");
        enderecoEditText.setText("");
    }*/
}
