package alpha_team.myvodafone_alpha_team.helper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

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

            //String urlQuery = "http://"+URL+":2480/query/vf/sql/select RATED_FLAT_AMOUNT_EURO from rtxh where (START_D_T >= '" + dateStart + "' AND (START_D_T <= '" + dateEnd + "') AND (CALL_TYPE = 1) AND (SNCODE = 1)";
            String urlQuery = "http://"+URL+":2480/query/vf/sql/select RATED_FLAT_AMOUNT_EURO from rtxh where (START_D_T >= '" + dateStart + "' AND (START_D_T <= '" + dateEnd + "') AND (CALL_TYPE = 1) AND (SNCODE = 1)";

            try {
                s = HelperHttp.downloadUrl(urlQuery);
            } catch (Exception e) {
                System.err.println("errore");
            }


        } else if (service == 1) { //sms
            String urlQuery = "http://"+URL+":2480/query/vf/sql/select%20RATED_FLAT_AMOUNT_EURO%20from%20rtxh%20where%20(START_D_T%20>=%20'" + dateStart + "')%20AND%20(START_D_T%20<=%20'" + dateEnd + "')%20and%20(CALL_TYPE%20=%201)%20and%20(SNCODE%20=%2014)";
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

        } else if (service == 2) { //data
            String urlQuery = "http://"+URL+":2480/query/vf/sql/select RATED_FLAT_AMOUNT_EURO from rtxh where (START_D_T >= " + dateStart + "' AND (START_D_T <= '" + dateEnd + "') and (CALL_TYPE = 10) and (SNCODE = 508)";
            try {
                s = HelperHttp.downloadUrl(urlQuery);
            } catch (Exception e) {
                System.err.println("errore");
            }
        } else if (service == 3) { //extra VAR
            String urlQuery = "http://"+URL+":2480/query/vf/sql/select RATED_FLAT_AMOUNT_EURO from rtxh_ch where (START_D_T >= " + dateStart + "' AND (START_D_T <= '" + dateEnd + "')";
            try {
                s = HelperHttp.downloadUrl(urlQuery);
            } catch (Exception e) {
                System.err.println("errore");
            }
        }

    return 0;
    }
}
