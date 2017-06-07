package br.com.fontes.projetinhobossini;

import android.widget.ImageView;

/**
 * Created by root on 26/05/17.
 */

public class Donate {

    public String nome;
    public String pathImage;
    public ImageView img;

    public Donate(String nome, String pathImage, ImageView img){

        this.nome = nome;
        this.pathImage = pathImage;
        this.img = img;
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

    public ImageView getImg(){return img;}

    public void setImg(ImageView img){this.img = img;}
}
