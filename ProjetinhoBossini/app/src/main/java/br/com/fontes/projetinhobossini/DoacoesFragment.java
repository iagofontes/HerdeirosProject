package br.com.fontes.projetinhobossini;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DoacoesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private List<Donate> donates = new ArrayList<>();

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
        URL url = null;
        try{

            url = new URL("http://localhost:3000/donates");
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        if (url != null){
//            dismissKeyboard (locationEditText);
            GetDonates getDonates =
                    new GetDonates();
            getDonates.execute(url);
        }
        else{
            Toast.makeText(getContext(), "Cannot create a URL.", Toast.LENGTH_LONG).show();
        }

        //Como exibir na tela essas informações?

        return view;
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


    private class GetDonates extends AsyncTask<URL, Void, JSONObject> {
        protected JSONObject doInBackground(URL... params) {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) params[0].openConnection();
                int response = connection.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK){
                    StringBuilder builder = new StringBuilder ();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
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
                JSONArray list = forecast.getJSONArray("list");
                for (int i = 0; i < list.length(); i++){
                    JSONObject line = list.getJSONObject(i);
//                    JSONObject name = line.getJSONObject("nome");
//                    JSONObject path = line.getJSONObject("pathImage");
                    donates.add (new Donate(line.getString("nome"), line.getString("pathImage")));
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

        protected void onPostExecute(JSONObject don) {
            convertJSONToArrayList (don);
            /*weatherArrayAdapter.notifyDataSetChanged();
            weatherListView.smoothScrollToPosition(0);*/
        }
    }


}
