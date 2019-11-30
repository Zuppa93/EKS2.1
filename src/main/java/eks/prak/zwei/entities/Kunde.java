package eks.prak.zwei.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Kunde {
    @XmlElement
    private String name;

    @XmlElement
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
