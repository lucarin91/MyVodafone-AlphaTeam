package alpha_team.myvodafone_alpha_team.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import alpha_team.myvodafone_alpha_team.R;

/**
 * Created by luca on 17/04/15.
 */
public class menuAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public menuAdapter(Context context, String[] values) {
            super(context, R.layout.row_fuori_soglia, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.row_fuori_soglia, parent, false);
            /*TextView textView = (TextView) rowView.findViewById(R.id.label);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            textView.setText(values[position]);
            // change the icon for Windows and iPhone
            String s = values[position];
            if (s.startsWith("iPhone")) {
                imageView.setImageResource(R.drawable.no);
            } else {
                imageView.setImageResource(R.drawable.ok);
            }
*/
            return rowView;
        }
}
