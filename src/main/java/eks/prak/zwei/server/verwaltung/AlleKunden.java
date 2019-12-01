package eks.prak.zwei.server.verwaltung;

import eks.prak.zwei.server.entities.Kunde;

import javax.ws.rs.POST;
import java.util.ArrayList;

public class AlleKunden {
    private static AlleKunden instance;
    private ArrayList<Kunde> alleKunden;

    private AlleKunden(){}

    public AlleKunden getInstance(){
        if(instance == null)
            return instance = new AlleKunden();
        return instance;
    }

    public void addKunde(Kunde kunde){
        alleKunden.add(kunde);
    }

    public Kunde getKundeByName(String name){
        Kunde kunde = null;
        for(int i = 0;i<alleKunden.size();i++){
            if(alleKunden.get(i).getName().equals(name)){
                kunde = alleKunden.get(i);
            }
        }
        return kunde;
    }

    public ArrayList<Kunde> getAlleKunden(){
        return alleKunden;
    }

}
