package alpha_team.myvodafone_alpha_team.helper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import alpha_team.myvodafone_alpha_team.model.Chiamate;

/**
 * Created by Stefano on 17/04/2015.
 */
public class JSONParser {

    /**
     * Take the date range, the type of service (calls, data, sms and extra services)
     * and returns the overall cost during the selected period.
     */

    public static double computeCost(int service, String dateStart, String dateEnd) {
        final String URL = "192.168.43.228";
        String s = new String();
        JSONArray arr;
        double sum = 0;

        if (service == 0) { //calls

            String urlQuery = "http://" + URL + ":2480/query/vf/sql/select%20RATED_FLAT_AMOUNT_EURO%20from%20rtxh%20where%20(START_D_T%20>=%20'" + dateStart + "')%20AND%20(START_D_T%20<=%20'" + dateEnd + "')%20and%20(CALL_TYPE=01)%20and%20(SNCODE%20=%201)";

            try {
                s = HelperHttp.downloadUrl(urlQuery);
            } catch (Exception e) {
                System.err.println("errore");
            }

            if (!s.isEmpty()) {
                arr = (JSONArray) HelperHttp.stringToJsonArray(s);

                for (int i = 0; i < arr.length(); i++) {
                    try {
                        sum += (arr.getJSONObject(i).getDouble("RATED_FLAT_AMOUNT_EURO"));
                    } catch (Exception e) {

                    }
                }
                return sum;
            }
        } else if (service == 1) { //sms
            String urlQuery = "http://" + URL + ":2480/query/vf/sql/select%20RATED_FLAT_AMOUNT_EURO%20from%20rtxh%20where%20(START_D_T%20>=%20'" + dateStart + "')%20AND%20(START_D_T%20<=%20'" + dateEnd + "')%20and%20(CALL_TYPE=1)%20and%20(SNCODE=14)";
            Log.i("JSON",urlQuery);
            try {
                s = HelperHttp.downloadUrl(urlQuery);
            } catch (Exception e) {
                System.err.println("errore");
            }
            Log.i("JSON",s);
            if (!s.isEmpty()) {
                arr = (JSONArray) HelperHttp.stringToJsonArray(s);

                for (int i = 0; i < arr.length(); i++) {
                    try {
                        sum += (arr.getJSONObject(i).getDouble("RATED_FLAT_AMOUNT_EURO"));
                    } catch (Exception e) {

                    }
                }
                Log.i("JSON", String.valueOf(sum));
                return sum;
            }

        } else if (service == 2) { //data
            String urlQuery = "http://" + URL + ":2480/query/vf/sql/select%20RATED_FLAT_AMOUNT_EURO%20from%20rtxh%20where%20(START_D_T%20>=%20'" + dateStart + "')%20AND%20(START_D_T%20<=%20'" + dateEnd + "')%20and%20(CALL_TYPE%20=%2010)%20and%20(SNCODE%20=%20508)";
            try {
                s = HelperHttp.downloadUrl(urlQuery);
            } catch (Exception e) {
                System.err.println("errore");
            }

            if (!s.isEmpty()) {
                arr = (JSONArray) HelperHttp.stringToJsonArray(s);

                for (int i = 0; i < arr.length(); i++) {
                    try {
                        sum += (arr.getJSONObject(i).getDouble("RATED_FLAT_AMOUNT_EURO"));
                    } catch (Exception e) {

                    }
                }
                return sum;
            }

        } else if (service == 3) { //extra VAR
            String urlQuery = "http://" + URL + ":2480/query/vf/sql/select%20RATED_FLAT_AMOUNT_EURO%20from%20rtxh%20where%20(START_D_T%20>=%20'" + dateStart + "')%20AND%20(START_D_T%20<=%20'" + dateEnd + "')";
            try {
                s = HelperHttp.downloadUrl(urlQuery);
            } catch (Exception e) {
                System.err.println("errore");
            }

            if (!s.isEmpty()) {
                arr = (JSONArray) HelperHttp.stringToJsonArray(s);

                for (int i = 0; i < arr.length(); i++) {
                    try {
                        sum += (arr.getJSONObject(i).getDouble("RATED_FLAT_AMOUNT_EURO"));
                    } catch (Exception e) {

                    }
                }
                return sum;
            }

        }

        return 0;
    }

    public static ArrayList<Chiamate> getData(int service, String dateStart, String dateEnd) {
        final String URL = "192.168.43.228";
        String s = new String();
        JSONArray arr = new JSONArray();
        ArrayList<Chiamate> calls = new ArrayList<>();
        double sum = 0;

        if (service == 0) { //calls
            String urlQuery = "http://" + URL + ":2480/query/vf/sql/select%20RATED_FLAT_AMOUNT_EURO,O_P_NUMBER,START_D_T,ACTUAL_VOLUME%20from%20rtxh%20where%20(START_D_T%20>=%20'" + dateStart + "')%20AND%20(START_D_T%20<=%20'" + dateEnd + "')%20and%20(CALL_TYPE=01)%20and%20(SNCODE%20=%201)";
            try {
                s = HelperHttp.downloadUrl(urlQuery);
                Log.i("HTTP", s);
            } catch (Exception e) {
                System.err.println("errore");
            }
            if (!s.isEmpty()) {
                arr = (JSONArray) HelperHttp.stringToJsonArray(s);
                JSONObject o = new JSONObject();

                for(int i = 0; i < arr.length(); i++){
                    try {
                        o = (JSONObject) arr.getJSONObject(i);
                    } catch (Exception e){

                    }

                    try {
                        int x = o.getInt("ACTUAL_VOLUME");
                        String duration = gettimeFormat(x);
                        Chiamate c = new Chiamate(o.getString("O_P_NUMBER"), o.getDouble("RATED_FLAT_AMOUNT_EURO"), duration, o.getString("START_D_T"));
                        calls.add(c);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                return calls;
            }
        } else if (service == 1) { //sms
            return calls;
        } else if (service == 2) { //data
             return calls;
        } else if (service == 3) {
            return calls;
        }

        return calls;
    }

     private static String gettimeFormat(int noofseconds) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sdateFormat = new SimpleDateFormat("HH:mm:ss");
        sdateFormat.setTimeZone(timeZone);
        String formatedTime = sdateFormat.format(new Date(noofseconds));
        return formatedTime;
    }

}