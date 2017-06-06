package br.com.fontes.projetinhobossini;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PagSeguroFragment extends Fragment {

    private Button btn=null;

    public PagSeguroFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PagSeguroFragment newInstance() {
        PagSeguroFragment fragment = new PagSeguroFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pag_seguro, container, false);
        btn = (Button) view.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(R.string.ip_server+"/pagseguro");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

//        WebView wv = (WebView)view.findViewById(R.id.webView);
        /*String html = "<!-- INICIO FORMULARIO BOTAO PAGSEGURO -->\n" +
                "<form action=\"https://pagseguro.uol.com.br/checkout/v2/donation.html\" method=\"post\">\n" +
                "<!-- NÃO EDITE OS COMANDOS DAS LINHAS ABAIXO -->\n" +
                "<input type=\"hidden\" name=\"currency\" value=\"BRL\" />\n" +
                "<input type=\"hidden\" name=\"receiverEmail\" value=\"iagofontes@hotmail.com\" />\n" +
                "<input type=\"hidden\" name=\"iot\" value=\"button\" />\n" +
                "<input type=\"image\" src=\"https://stc.pagseguro.uol.com.br/public/img/botoes/doacoes/120x53-doar-preto.gif\" name=\"submit\" alt=\"Pague com PagSeguro - é rápido, grátis e seguro!\" />\n" +
                "</form>\n" +
                "<!-- FINAL FORMULARIO BOTAO PAGSEGURO -->";
        wv.loadData(html, "text/html", null);*/
//        wv.loadUrl("http://10.67.172.170:3000/pagseguro");
//        Uri uri = Uri.parse("https://mario-apra.tk/mercado_pago");


        return view;
    }

}
