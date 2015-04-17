package alpha_team.myvodafone_alpha_team.helper;

import org.json.JSONArray;

import java.util.Calendar;

/**
 * Created by Stefano on 17/04/2015.
 */
public class JSONParser {

    /**Take the date range, the type of service (calls, data, sms and extra services)
     * and returns the overall cost during the selected period.
     */

    public JSONParser(){
        helper = new HelperHttp();
    }

    public double computeCost(int service, String dateStart, String dateEnd){

        if (service == 0){ //calls

            String urlQuery = "http://localhost:2480/query/vf/sql/select RATED_FLAT_AMOUNT_EURO from rtxh where (START_D_T >= '" + dateStart + "' AND (START_D_T <= '" + dateEnd +"') AND (CALL_TYPE = 1) AND (SNCODE = 1)";

            try { String s = HelperHttp.downloadUrl(urlQuery); }
            catch (Exception e){
                System.err.println("errore");
            }

        }
        else if (service == 1){ //sms
            String urlQuery = "http://localhost:2480/query/vf/sql/select RATED_FLAT_AMOUNT_EURO from rtxh where (START_D_T >= " + dateStart + "' AND (START_D_T <= '" + dateEnd + "') and (CALL_TYPE = 1) and (SNCODE = 14)";
            try { String s = HelperHttp.downloadUrl(urlQuery); }
            catch (Exception e){
                System.err.println("errore");
            }
        }
        else if (service == 2){ //data
            String urlQuery = "http://localhost:2480/query/vf/sql/select RATED_FLAT_AMOUNT_EURO from rtxh where (START_D_T >= " + dateStart + "' AND (START_D_T <= '" + dateEnd +"') and (CALL_TYPE = 10) and (SNCODE = 508)";
            try { String s = HelperHttp.downloadUrl(urlQuery); }
            catch (Exception e){
                System.err.println("errore");
            }
        }
        else if (service == 3){ //extra/VAR
            String urlQuery = "http://localhost:2480/query/vf/sql/select RATED_FLAT_AMOUNT_EURO from rtxh_ch where (START_D_T >= " + dateStart + "' AND (START_D_T <= '" + dateEnd +"')";
            try { String s = HelperHttp.downloadUrl(urlQuery); }
            catch (Exception e){
                System.err.println("errore");
            }
        }





    }

}