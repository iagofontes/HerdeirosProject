package br.com.fontes.testeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//public class MainActivity extends AppCompatActivity {

    public Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                doClick();

                /*FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//                TesteFragment fragment = new TesteFragment();
                TesteFragment fragment = TesteFragment.newInstance("", "");
                fragmentTransaction.add(R.id.layoutTeste, fragment, null);
                fragmentTransaction.commit();*/
//                Toast.makeText(getApplicationContext(), "Texto Exemplo2 ", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void doClick(){
        Toast.makeText(getApplicationContext(), "Texto Exemplo2 ", Toast.LENGTH_SHORT).show();
        Intent callNext = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(callNext);
    }



}
