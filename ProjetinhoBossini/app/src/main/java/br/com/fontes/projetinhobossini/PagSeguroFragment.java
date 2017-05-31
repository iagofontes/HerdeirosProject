package br.com.fontes.projetinhobossini;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class PagSeguroFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    public PagSeguroFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PagSeguroFragment newInstance(String param1, String param2) {
        PagSeguroFragment fragment = new PagSeguroFragment();
    /*    Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pag_seguro, container, false);

        WebView wv = (WebView)view.findViewById(R.id.webView);
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
        wv.loadUrl("http://172.20.10.7:3000/pagseguro");

        return view;
    }

/*    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
