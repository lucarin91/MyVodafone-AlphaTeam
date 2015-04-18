package alpha_team.myvodafone_alpha_team.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import alpha_team.myvodafone_alpha_team.R;

/**
 * Created by luca on 17/04/15.
 */public class chimateAdapter extends ArrayAdapter<Chiamate> {


    public chimateAdapter(Context context, ArrayList<Chiamate> Evento) {
        super(context, R.layout.chiamate_row, Evento);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Chiamate chiamate = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chiamate_row, parent, false);
        }

        TextView numero = (TextView) convertView.findViewById(R.id.numero);
        TextView destinazione = (TextView) convertView.findViewById(R.id.destinazione);
        TextView durata = (TextView) convertView.findViewById(R.id.durata);
        TextView dataEora = (TextView) convertView.findViewById(R.id.dataEora);

        numero.setText(String.valueOf(chiamate.num));
        destinazione.setText(chiamate.dest);
        durata.setText(chiamate.durata);
        dataEora.setText(chiamate.date);

        return convertView;
    }

}
