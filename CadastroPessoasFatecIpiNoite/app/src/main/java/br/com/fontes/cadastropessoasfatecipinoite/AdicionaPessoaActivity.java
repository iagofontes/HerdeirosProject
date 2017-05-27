package br.com.fontes.cadastropessoasfatecipinoite;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdicionaPessoaActivity extends AppCompatActivity {

    private EditText nomeEditText, enderecoEditText, telefoneEditText;
    private Button okButton;
    private PessoaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_pessoa);

        dao = new PessoaDAO(this);

        nomeEditText = (EditText) findViewById(R.id.nomeEditText);
        enderecoEditText= (EditText) findViewById(R.id.enderecoEditText);
        telefoneEditText = (EditText) findViewById(R.id.telefoneEditText);
        okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nome = nomeEditText.getEditableText().toString();
                String telefone = telefoneEditText.getEditableText().toString();
                String endereco = enderecoEditText.getEditableText().toString();
                Pessoa pessoa = new Pessoa(nome, telefone, endereco);
                dao.inserir(pessoa);
                Snackbar.make(v, getString(R.string.pessoa_adicionada), Snackbar.LENGTH_LONG).show();
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

    }
}
