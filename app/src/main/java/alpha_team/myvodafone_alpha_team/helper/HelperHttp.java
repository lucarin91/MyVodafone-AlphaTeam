package alpha_team.myvodafone_alpha_team.helper;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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

/**
 * Created by luca on 17/04/15.
 */
public class HelperHttp {

    public static void downloadSumFuoriSoglia(final Context context, final TextView chiamate, final String url) {
        new AsyncTask<Void, Void, JSONArray>() {

            @Override
            protected void onPreExecute() {
                //EventiListFragment.progressBar = true;
                //((Activity) context).invalidateOptionsMenu();
            }

            @Override
            protected JSONArray doInBackground(Void... params) {
                String json_string = null;
                try {
                    json_string = downloadUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return stringToJsonArray(json_string);
                //return json_string;
            }

            @Override
            protected void onPostExecute(JSONArray json) {
                Log.i("HTTP",json.toString());
                try {
                    chiamate.setText(json.getJSONObject(0).getInt("sum"));
                } catch (JSONException e) {
                    e.printStackTrace();
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

    private static JSONArray stringToJsonArray(String jsonString) {

        try {
            JSONObject json_data = new JSONObject(jsonString);
            return json_data.getJSONArray("result");
        } catch (JSONException e) {
            Log.e("DataProvide-stringToJsonArray", "JSONException " + e);
            return null;
        }
    }
}
