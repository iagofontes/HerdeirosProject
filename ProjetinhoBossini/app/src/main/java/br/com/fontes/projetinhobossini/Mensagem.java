package br.com.fontes.projetinhobossini;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;

/**
 * Created by root on 26/05/17.
 */

public class Mensagem {

    private String nome="";
    private String email="";
    private String telefone="";
    private String celular="";
    private String empresa="";
    private String site="";
    private String mensagem="";
    private Context context = null;

    public Mensagem(String name, String email, String telefone, String celular, String empresa, String site, String mensagem,
                    Context context){

        this.nome = name;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.empresa = empresa;
        this.site = site;
        this.mensagem = mensagem;
        this.context = context;
    }

    //sobrecarga
    public Mensagem(){

    }

    public boolean sendByEmail(View view) {
        try {

//            String[] recipients = new String[]{this.email, "",};
            String myEmail = "iagofontes@hotmail.com";
            String[] recipients = new String[]{myEmail, "",};
            StringBuilder body = new StringBuilder();
            body.append("<p>Olá, meu nome é: " + this.nome + ".<br>" +
                    "Estas são minhas informações:<br>" +
                    "Email: " + this.email + "<br>" +
                    "Celular: " + this.celular + "<br>" +
                    "Telefone: " + this.telefone + "<br>" +
                    "Empresa: " + this.empresa + "<br>" +
                    "Site: " + this.site + "<br>" +
                    "Mensagem: " + this.mensagem + ".</p>");

            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("text/html");
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "assunto");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body.toString()));
//            ContextCompat.startActivity(context, emailIntent, "Enviar email....");
            context.startActivity(Intent.createChooser(emailIntent, "Enviar email...."));
            return true;
        } catch (Exception e) {

        }
        return false;
    }


    /*public void sendEmailByIntent(){
        Intent intent = null;
        Intent chooser = null;

        intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto: "));
        String[] to = {"iagofontes@hotmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello, email from android bitch.");
        intent.putExtra(Intent.EXTRA_TEXT, "A mensagem deve ficar aqui neste local.");
        intent.setType("message/rfc822");
        chooser = Intent.createChooser(intent, "Send Email");
        this.context.startActivity(chooser);
    }*/


    //getters e setters
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getEmpresa(){
        return this.empresa;
    }
    public void setEmpresa(String empresa){
        this.empresa = empresa;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getTelefone(){
        return this.telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getCelular(){
        return this.celular;
    }
    public void setCelular(String celular){
        this.celular = celular;
    }
    public String getSite(){
        return this.site;
    }
    public void setSite(String site){
        this.site = site;
    }
    public String getMensagem(){
        return this.mensagem;
    }
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
    public Context getContext(){
        return this.context;
    }
    public void setContext(Context context){
        this.context = context;
    }

        /*try{

//        String to = "teste@teste.com.br";
//        startActivity(Intent.createChooser(email, "E-mail"));
            String to = "iagofontes@hotmail.com";
            String subject = "Apadrinhamento de Herdeirinhos";

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
            email.putExtra(Intent.EXTRA_TEXT, this.mensagem);
            email.setType("message/rfc822");
            email.getAction();
            return true;
        }catch(Exception e){
//            system.println(e);
            return false;
//            Toast.make(view.getContext().getApplicationContext(), "Erro ao enviar email. Message: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }*/
    //}

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

}
