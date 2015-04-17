package alpha_team.myvodafone_alpha_team;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import alpha_team.myvodafone_alpha_team.helper.HelperHttp;


public class FuoriSoglia extends ActionBarActivity {
    String TAG = "FUORI_SOGLIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuori_soglia);

        final TextView chiamate = (TextView) findViewById(R.id.chiamate);
        //final TextView  = (TextView) findViewById(R.id.chiamate);
        //final TextView chiamate = (TextView) findViewById(R.id.chiamate);
        //final TextView chiamate = (TextView) findViewById(R.id.chiamate);
        //HelperHttp.downloadSumFuoriSoglia(getApplicationContext(), chiamate, "");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String data1 = "";
                String data2 = "";
                Calendar date = null;
                switch (pos){
                    case 0:
                        date = Calendar.getInstance();
                        data1 = date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR);
                        data2=data1;
                        break;
                    case 1:
                        date = Calendar.getInstance();
                        data1 = date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR);
                        date.add(Calendar.MONTH,-1);
                        data2 = date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR);
                        break;
                    case 2:
                        date = Calendar.getInstance();
                        date.add(Calendar.MONTH,-1);
                        data1 = date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR);
                        date.add(Calendar.MONTH,-2);
                        data2 = date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR);

                        break;
                }

                Log.i(TAG, data1.toString());
                Log.i(TAG, data2.toString());
                HelperHttp.downloadSumFuoriSoglia(getApplicationContext(),chiamate,new HelperHttp.MethodSum(){
                    @Override
                    public double run(){
                        return 1;
                    }
                });
                HelperHttp.downloadSumFuoriSoglia(getApplicationContext(),chiamate,new HelperHttp.MethodSum(){
                    @Override
                    public double run(){
                        return 1;
                    }
                });
                HelperHttp.downloadSumFuoriSoglia(getApplicationContext(),chiamate,new HelperHttp.MethodSum(){
                    @Override
                    public double run(){
                        return 1;
                    }
                });
                HelperHttp.downloadSumFuoriSoglia(getApplicationContext(),chiamate,new HelperHttp.MethodSum(){
                    @Override
                    public double run(){
                        return 1;
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fuori_soglia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
