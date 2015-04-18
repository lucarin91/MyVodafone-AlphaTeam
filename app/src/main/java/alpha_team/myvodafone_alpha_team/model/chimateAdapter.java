package alpha_team.myvodafone_alpha_team.model;

import android.content.Context;
import android.graphics.Typeface;
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

    Typeface fontExb = Typeface.createFromAsset(getContext().getAssets(), "VODAFONEEXB.TTF");
    Typeface fontErg = Typeface.createFromAsset(getContext().getAssets(), "VODAFONERG_0.TTF");

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
        TextView costo = (TextView) convertView.findViewById(R.id.costo);
        TextView dataEora = (TextView) convertView.findViewById(R.id.dataEora);

        costo.setTypeface(fontExb);
        numero.setTypeface(fontErg);
        dataEora.setTypeface(fontErg);

        numero.setText(chiamate.num);
        costo.setText(String.format( "€ %.2f",chiamate.spesa));
        dataEora.setText(chiamate.date);

        return convertView;
    }

}
