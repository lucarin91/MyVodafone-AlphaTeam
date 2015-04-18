package alpha_team.myvodafone_alpha_team.model;

import java.util.Date;

/**
 * Created by luca on 17/04/15.
 */
public class Chiamate {
    int num;
    //String dest;
    double spesa;
    String durata;
    String date;

   public Chiamate(int num, double spesa, String durata, String data){
        this.num=num;
        this.spesa=spesa;
        this.durata=durata;
        this.date=data;
    }
}
