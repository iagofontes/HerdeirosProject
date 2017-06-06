package br.com.fontes.projetinhobossini;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 06/06/17.
 */

public class EventoArrayAdapter extends ArrayAdapter<Eventos> {

    private static class ViewHolder{
        TextView dataTextView;
        TextView descrTextView;
        TextView valTextView;
    }

    public EventoArrayAdapter (Context context, List<Eventos> forecast){
        super (context, -1, forecast);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Eventos eve = getItem (position);
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.event_item, parent, false);
            viewHolder.descrTextView =  (TextView) convertView.findViewById(R.id.eventoDescr);
            viewHolder.valTextView =  (TextView) convertView.findViewById(R.id.eventoValor);
            viewHolder.dataTextView = (TextView) convertView.findViewById(R.id.eventoData);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
//        Context context = getContext();
        viewHolder.descrTextView.setText(eve.getDescricao());
        viewHolder.valTextView.setText(eve.getValor().toString());
        viewHolder.dataTextView.setText(eve.getData());
        return convertView;
    }


}
