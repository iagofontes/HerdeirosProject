package br.com.fontes.projetinhobossini;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EventosFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;
    private EditText memo;
    private List<Eventos> eventos = new ArrayList<>();

    public EventosFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EventosFragment newInstance(String param1, String param2) {
        EventosFragment fragment = new EventosFragment();
        /*Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_eventos, container, false);
        memo = (EditText) view.findViewById(R.id.nextEvents);
        String teste = "fubfnsdkfjsdfks 1\n" +
                "fdnajfbdkfsdbfhsdbfkdjbffniofjei 2\n" +
                "nfkldfndjksfnsjfsj 3";
        memo.setText(teste);


        URL url = null;

        try{

//            url = new URL("http://localhost:3000/donates");
            url = new URL("http://172.20.10.7:3000/events");

        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        if (url != null){
            GetEvents getEvents= new GetEvents();
            getEvents.execute(url);
        }
        else{
            Toast.makeText(getContext(), "Cannot create a URL.", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    private class GetEvents extends AsyncTask<URL, Void, JSONObject> {
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
                        Toast.makeText(getContext(), "Errors while trying to get the donates.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    return new JSONObject(builder.toString());
                }
            }
            catch (Exception e){
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
            EventosFragment.this.eventos.clear();
            try{
                JSONArray list = forecast.getJSONArray("eventos");
                for (int i = 0; i < list.length(); i++){
                    JSONObject line = list.getJSONObject(i);
//                    String nome = line.optString("name");
                    EventosFragment.this.eventos.add(new Eventos(line.getString("data"), line.getString("descricao"), line.getDouble("valor")));
                }
                Log.d("ConvertJSONToArray", EventosFragment.this.eventos.toString());
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

        protected void onPostExecute(JSONObject don) {

            convertJSONToArrayList (don);
            Log.d("Eventos Convertidos", eventos.toString());
            memo.setText("");
            memo.setText(formatarEventos(EventosFragment.this.eventos));
            //Chamar a segunda requisição para baixar as imagens aqui.
            /*if(donates.size() > 0){

            }*/
        }

        protected String formatarEventos(List<Eventos> eve){

            String ret="";

            if((eve != null) && (eve.size() > 0)){
                for(int i=0; i<eve.size(); i++){

                    ret += "Data: "+eve.get(i).getData()+"\n" +
                            "Descrição: "+eve.get(i).getDescricao()+"\n" +
                            "Valor: "+eve.get(i).getValor()+"\n" +
                            "===============================\n";
                }
            }
            return ret;
        }
    }
}
