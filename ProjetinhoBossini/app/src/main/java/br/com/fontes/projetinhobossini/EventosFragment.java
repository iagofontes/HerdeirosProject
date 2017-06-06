package br.com.fontes.projetinhobossini;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
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
    private EventoArrayAdapter eventoArrayAdapter;
    private ListView eventoListView;

    public EventosFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EventosFragment newInstance(String param1, String param2) {
        EventosFragment fragment = new EventosFragment();
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
        View view = inflater.inflate(R.layout.fragment_eventos, container, false);
        /*memo = (EditText) view.findViewById(R.id.nextEvents);
        String teste = "fubfnsdkfjsdfks 1\n" +
                "fdnajfbdkfsdbfhsdbfkdjbffniofjei 2\n" +
                "nfkldfndjksfnsjfsj 3";
        memo.setText(teste);

*/
        eventoListView= (ListView) view.findViewById(R.id.listEvents);
        eventoArrayAdapter = new EventoArrayAdapter(getContext(), eventos);
        eventoListView.setAdapter(eventoArrayAdapter);
        URL url = null;

        try{

//            url = new URL("http://localhost:3000/donates");
//            url = new URL(R.string.ip_server+"/events");
            url = new URL("http://10.42.0.1:3000/events");

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
                    EventosFragment.this.eventos.add(
                            new Eventos(line.getString("data"), line.getString("descricao"), line.getDouble("valor")));
                }
//                Log.d("ConvertJSONToArray", EventosFragment.this.eventos.toString());
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

        protected void onPostExecute(JSONObject don) {

            convertJSONToArrayList (don);
            eventoArrayAdapter.notifyDataSetChanged();
            eventoListView.smoothScrollToPosition(0);
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
