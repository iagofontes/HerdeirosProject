package br.com.fontes.projetinhobossini;

/**
 * Created by root on 26/05/17.
 */

public class Donate {

    public String nome;
    public String pathImage;

    public Donate(String nome, String pathImage){

        this.nome = nome;
        this.pathImage = pathImage;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
}
