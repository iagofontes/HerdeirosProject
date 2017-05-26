package br.com.fontes.projetinhobossini;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EmailFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtTelefone;
    private EditText txtCelular;
    private EditText txtEmpresa;
    private EditText txtSite;
    private EditText txtMensagem;
    private FloatingActionButton fab;


    public EmailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EmailFragment newInstance(String param1, String param2) {
        EmailFragment fragment = new EmailFragment();
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
        View emailView = inflater.inflate(R.layout.fragment_email, container, false);
        txtNome = (EditText) emailView.findViewById(R.id.txtNome);
        txtEmail = (EditText) emailView.findViewById(R.id.txtEmail);
        txtTelefone = (EditText) emailView.findViewById(R.id.txtTelefone);
        txtCelular = (EditText) emailView.findViewById(R.id.txtCelular);
        txtEmpresa = (EditText) emailView.findViewById(R.id.txtEmpresa);
        txtSite = (EditText) emailView.findViewById(R.id.txtSite);
        txtMensagem = (EditText) emailView.findViewById(R.id.txtMessage);
        fab = (FloatingActionButton) emailView.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui deve-se criar a nova mensagem e realizar o envio.
            }
        });
        return emailView;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
    }
}
