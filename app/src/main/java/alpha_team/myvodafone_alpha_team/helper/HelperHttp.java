package alpha_team.myvodafone_alpha_team.helper;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import alpha_team.myvodafone_alpha_team.model.Chiamate;
import alpha_team.myvodafone_alpha_team.model.chimateAdapter;

/**
 * Created by luca on 17/04/15.
 */
public class HelperHttp {

    public static void downloadSumFuoriSoglia(final Context context, final TextView text, final GridLayout grid, final int type,
                                              final String startDate, final String endDate) {
        new AsyncTask<Void, Void, String>() {


            @Override
            protected void onPreExecute() {
                //EventiListFragment.progressBar = true;
                //((Activity) context).invalidateOptionsMenu();
            }

            @Override
            protected String doInBackground(Void... params) {
                String string = null;
                return String.format( "€ %.2f", JSONParser.computeCost(type,startDate,endDate));
                //return String.valueOf(JSONParser.computeCost(type,startDate,endDate));
            }

            @Override
            protected void onPostExecute(String string) {
                Log.i("HTTP",string);
                text.setText(string);
                if (string.equals("€ 0,00")){
                    AlphaAnimation alpha = new AlphaAnimation(0.5F, 0.5F);
                    alpha.setDuration(0); // Make animation instant
                    alpha.setFillAfter(true); // Tell it to persist after the animation ends
                        // And then on your layout
                    grid.startAnimation(alpha);

                    Log.i("HELPERHTTP", "entrato");
                }else{
                    AlphaAnimation alpha = new AlphaAnimation(1F, 1F);
                    alpha.setDuration(0); // Make animation instant
                    alpha.setFillAfter(true); // Tell it to persist after the animation ends
                    // And then on your layout
                    grid.startAnimation(alpha);
                }

                /*
                EventiListFragment.progressBar = false;
                ((Activity) context).invalidateOptionsMenu();

                if (jsonArray != null) {
                    try {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        if (jsonArray.getJSONObject(0).getString("error").equals("serverOffline")) {
                            alertDialogBuilder.setMessage(context.getString(R.string.serverOffline));
                        } else {
                            alertDialogBuilder.setMessage(context.getString(R.string.connAssente));
                        }
                        alertDialogBuilder.setPositiveButton(context.getString(R.string.chiudi), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    } catch (JSONException e) {
                        saveJson(jsonArray, "eventi", context);
                        loadIntoEventiAdapter(jsonArray);
                    }
                }
                */
            }
        }.execute(null, null, null);
    }

    public static void downloadChiamate(final Context context, final ListView listView) {
        new AsyncTask<Void, Void, ArrayList<Chiamate>>() {


            @Override
            protected void onPreExecute() {
                //EventiListFragment.progressBar = true;
                //((Activity) context).invalidateOptionsMenu();
            }

            @Override
            protected ArrayList<Chiamate> doInBackground(Void... params) {
                String string = null;
                //return new ArrayList<Chiamate>();
                return JSONParser.getData(0, "17/01/2015", "17/04/2015");
            }

            @Override
            protected void onPostExecute(ArrayList<Chiamate> list) {
                listView.setAdapter(new chimateAdapter(context, list));

                /*
                EventiListFragment.progressBar = false;
                ((Activity) context).invalidateOptionsMenu();

                if (jsonArray != null) {
                    try {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        if (jsonArray.getJSONObject(0).getString("error").equals("serverOffline")) {
                            alertDialogBuilder.setMessage(context.getString(R.string.serverOffline));
                        } else {
                            alertDialogBuilder.setMessage(context.getString(R.string.connAssente));
                        }
                        alertDialogBuilder.setPositiveButton(context.getString(R.string.chiudi), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    } catch (JSONException e) {
                        saveJson(jsonArray, "eventi", context);
                        loadIntoEventiAdapter(jsonArray);
                    }
                }
                */
            }
        }.execute(null, null, null);
    }

    static String downloadUrl(String myurl) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Basic cm9vdDpvcmllbnRkYg==" );
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            //start
            conn.connect();
            int response = conn.getResponseCode();
            Log.e("The response is: ", "" + response);
            is = conn.getInputStream();
            //converte inputStream in stringa
            String contentAsString = readIt(is);
            return contentAsString;
        }catch(IOException e) {
            Log.e("HTTP", e.getMessage());
            return null;
        }finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        String ris=total.toString();
        return ris;
    }

    public static JSONArray stringToJsonArray(String jsonString) {

        try {
            JSONObject json_data = new JSONObject(jsonString);
            return json_data.getJSONArray("result");
        } catch (JSONException e) {
            Log.e("DataProvide-stringToJsonArray", "JSONException " + e);
            return null;
        }
    }
    public interface MethodSum{
        public double run();
    }

}
