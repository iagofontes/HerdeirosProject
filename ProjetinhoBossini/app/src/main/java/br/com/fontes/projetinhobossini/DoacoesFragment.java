package br.com.fontes.projetinhobossini;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DoacoesFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;
    private List<Donate> donates = new ArrayList<>();
    protected TextView returna;
//    public List<Bitmap> ;
    public ImageView img1;
    public TextView txt1;
    private List<Bitmap> imagesBit = new ArrayList<Bitmap>();
    public ImageView foto1=null;

    public DoacoesFragment() {
        // Required empty public constructor
    }


    public static DoacoesFragment newInstance(String param1, String param2) {
        DoacoesFragment fragment = new DoacoesFragment();
/*        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_doacoes, container, false);
//        returna = (TextView) view.findViewById(R.id.returnAsync);
        img1 = (ImageView) view.findViewById(R.id.foto1);
        txt1 = (TextView) view.findViewById(R.id.textView);


        URL url = null;

        try{

//            url = new URL("http://localhost:3000/donates");
//            url = new URL("http://172.20.10.7:3000/donates");
            url = new URL("http://10.67.172.170:3000/donates");

        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        if (url != null){
//            dismissKeyboard (locationEditText);
            GetDonates getDonates =
                    new GetDonates();
            getDonates.execute(url);
//            Toast.makeText(getContext(), "Requisição enviada.", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getContext(), "Cannot create a URL.", Toast.LENGTH_LONG).show();
        }
        //Como exibir na tela essas informações?
        return view;
    }

    private class GetDonates extends AsyncTask<URL, Void, JSONObject> {
        protected JSONObject doInBackground(URL... params) {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) params[0].openConnection();
                int response = connection.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK){
                    StringBuilder builder = new StringBuilder ();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))){
                        String line;
                        while ((line = reader.readLine()) != null){
                            builder.append(line);
                        }
                    }
                    catch (IOException e){
//                        Snackbar.make(findViewById(R.id.coordinatorLayout), R.string.read_error, Snackbar.LENGTH_LONG).show();
                        Toast.makeText(getContext(), "Errors while trying to get the donates.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    return new JSONObject(builder.toString());
                }
            }
            catch (Exception e){
//                Snackbar.make(findViewById(R.id.coordinatorLayout), R.string.connect_error, Snackbar.LENGTH_LONG).show();
                Toast.makeText(getContext(), "Errors while trying to connect with server of donates.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            finally{
                if (connection != null){
                    connection.disconnect();
                }
            }
            return null;
        }

        private void convertJSONToArrayList (JSONObject forecast){
            donates.clear();
            try{
                JSONArray list = forecast.getJSONArray("donatezinhas");
                for (int i = 0; i < list.length(); i++){
                    JSONObject line = list.getJSONObject(i);
                    String nome = line.optString("name");
                    /*if(returna != null){
                        returna.setText(nome);
                    }else{
                        Toast.makeText(getContext(), "Texto nulo.", Toast.LENGTH_LONG).show();
                    }*/
                    donates.add(new Donate(line.getString("name"), line.getString("pathImage")));
                }
                Log.d("ConvertJSONToArray", donates.toString());
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

        protected void onPostExecute(JSONObject don) {

            convertJSONToArrayList (don);
            txt1.setText(donates.get(0).getNome());
            //Chamar a segunda requisição para baixar as imagens aqui.
            if(donates.size() > 0){
                baixarImagensDonates(donates);
            }
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;

//        public DownloadImageTask(ImageView bmImage) {
        public DownloadImageTask() {
//            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {

            Bitmap bitmap = null;
            HttpURLConnection connection = null;
            try{
                URL url = new URL(urls[0]);
//                URL url = new URL("http://10.67.172.170:3000/getImage");
                Log.d("url", urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                try(InputStream inputStream = connection.getInputStream ()){
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    if(bitmap != null){
                        imagesBit.add(bitmap);
                    }
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
            //Adicionar no array aqui ?
//            DoacoesFragment.this.imagesBit.add(result);
            Log.d("imgs", imagesBit.toString());
            /*switch(){

            }*/
            img1.setImageBitmap(imagesBit.get(0));
//            bmImage.setImageBitmap(result);
        }
    }

    public void baixarImagensDonates(List<Donate> don){
        //Criar um array de image view para adicionar os bitmaps dentro do array
        Integer i=0;
        for(i=0; i<don.size(); i++){
            DownloadImageTask dit = new DownloadImageTask();
            dit.execute(don.get(i).getPathImage(), i.toString());
        }
    }


}
