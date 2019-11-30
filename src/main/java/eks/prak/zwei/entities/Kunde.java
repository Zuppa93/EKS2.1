package eks.prak.zwei.entities;

import java.util.ArrayList;

public class Kunde {
    private String name;
    private String adresse;
    private ArrayList<Konto> konten;

    public Kunde(String name,String adresse){
        this.name = name;
        this.adresse = adresse;
        konten = null;
    }

    public String getName(){
        return name;
    }

    public void addKonto(Konto konto){
        konten.add(konto);
    }

    public ArrayList<Konto> getKonten(){
        return konten;
    }
}
