package br.com.fontes.projetinhobossini;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtTelefone;
    private EditText txtCelular;
    private EditText txtEmpresa;
    private EditText txtSite;
    private EditText txtMensagem;
    private Context context;
    private FloatingActionButton fab;

    private Session session = null;
    private ProgressDialog pDialog = null;

    private Mensagem msg;
    private String to;



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
        context = null;
        fab = (FloatingActionButton) emailView.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui deve-se criar a nova mensagem e realizar o envio.
//                Snackbar.make(v, "Clique aceito", Snackbar.LENGTH_SHORT).show();
                sendEmail();
            }
        });
        return emailView;
    }

    private void sendEmail(){
        msg = new Mensagem(txtNome.getText().toString(), txtEmail.getText().toString(), txtTelefone.getText().toString(),
                txtCelular.getText().toString(), txtEmpresa.getText().toString(),
                txtSite.getText().toString(), txtMensagem.getText().toString(), getContext());

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
//                return super.getPasswordAuthentication("exemplo@gmail.com", "contraseñaExemplo");
                return new PasswordAuthentication("azaza06.zazaaz@gmail.com", "iagoti2014");
            }
        });

//        pDialog = ProgressDialog.show(context, "", "Sending Email...", true);

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... param){
            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("azaza06.zazaaz@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("iago@urgtec.com.br"));
                message.setSubject("Herdeiros do futuro");
                message.setContent(msg.getMensagem(), "text/html; charset=utf-8");
                Transport.send(message);
                //checar porque o aplicativo crasha neste momento
                return "true";
            }catch(MessagingException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            pDialog.dismiss();
            Toast.makeText(context, "Message sent", Toast.LENGTH_LONG).show();
        }

    }

    /*public void sendEmail() throws EmailException {
        //Baixar e anexar as bibliotecas
        SimpleEmail email = new SimpleEmail();
        //Utilize o hostname do seu provedor de email
        System.out.println("alterando hostname...");
        email.setHostName("smtp.gmail.com");
        //Quando a porta utilizada não é a padrão (gmail = 465)
        email.setSmtpPort(465);
        //Adicione os destinatários
        email.addTo("xxx@xxx.com", "Jose");
        //Configure o seu email do qual enviará
        email.setFrom("seuemail@seuprovedor.com", "Seu nome");
        //Adicione um assunto
        email.setSubject("Test message");
        //Adicione a mensagem do email
        email.setMsg("This is a simple test of commons-email");
        //Para autenticar no servidor é necessário chamar os dois métodos abaixo
        System.out.println("autenticando...");
        email.setSSL(true);
        email.setAuthentication("username", "senha");
        System.out.println("enviando...");
        email.send();
        System.out.println("Email enviado!");
    }*/

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
