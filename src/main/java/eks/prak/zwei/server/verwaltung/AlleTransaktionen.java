package eks.prak.zwei.verwaltung;

import eks.prak.zwei.entities.Transaktion;
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
