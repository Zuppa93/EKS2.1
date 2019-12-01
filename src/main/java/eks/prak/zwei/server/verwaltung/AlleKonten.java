package eks.prak.zwei.server.verwaltung;

import eks.prak.zwei.server.entities.Konto;
import java.util.ArrayList;

public class AlleKonten {
    private static AlleKonten instance;
    private ArrayList<Konto> alleKonten;

    private AlleKonten(){}

    public AlleKonten getInstance(){
        if(instance == null)
            return instance = new AlleKonten();
        return instance;
    }

    public Konto getKontoByNummer(int nummer){
        Konto konto = null;

        for(int i = 0 ; i < alleKonten.size();i++){
            if(alleKonten.get(i).getNummer() == nummer){
                konto = alleKonten.get(i);
            }
        }
        return konto;

    }
}
