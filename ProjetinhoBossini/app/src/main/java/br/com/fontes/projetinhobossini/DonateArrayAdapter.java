package br.com.fontes.projetinhobossini;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by root on 06/06/17.
 */

public class DonateArrayAdapter extends ArrayAdapter<Donate> {


    private static class ViewHolder{
        ImageView imgDoacao;
        TextView descrTextView;
    }

    public DonateArrayAdapter (Context context, List<Donate> forecast){
        super (context, -1, forecast);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Donate done = getItem (position);
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.donate_item, parent, false);
            viewHolder.descrTextView =  (TextView) convertView.findViewById(R.id.descrDonate);
            viewHolder.imgDoacao =  (ImageView) convertView.findViewById(R.id.imgDonate);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
//        Context context = getContext();
        viewHolder.descrTextView.setText(done.getNome());
        //baixar a imagem aqui
        if((done.getPathImage() != null) && (done.getPathImage() != "")){
            DownloadImageTask dit = new DownloadImageTask(viewHolder.imgDoacao);
            dit.execute(done.getPathImage());
//        viewHolder.imgDoacao.setImageBitmap();
        }
        return convertView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView imagem;

        public DownloadImageTask(ImageView img) {
            this.imagem = img;
        }

        protected Bitmap doInBackground(String... urls) {

            Bitmap bitmap = null;
            HttpURLConnection connection = null;
            try{
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                try(InputStream inputStream = connection.getInputStream ()){
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    /*if(bitmap != null){
                        imagesBit.add(bitmap);
                    }*/
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally{
                connection.disconnect();
            }
            return bitmap;

        }

        protected void onPostExecute(Bitmap result) {
            imagem.setImageBitmap(result);
        }
    }



}
