package eks.prak.zwei.server.verwaltung;

import eks.prak.zwei.server.entities.Transaktion;
import java.util.ArrayList;

public class AlleTransaktionen {
    private static AlleTransaktionen instance;
    private ArrayList<Transaktion> alleTransaktionen;

    private AlleTransaktionen(){}
    public AlleTransaktionen getInstance(){
        if(instance == null){
            instance = new AlleTransaktionen();
            return instance;
        }else{
            return instance;
        }
    }
}
