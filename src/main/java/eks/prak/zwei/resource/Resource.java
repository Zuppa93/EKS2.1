package eks.prak.zwei.resource;

import eks.prak.zwei.entities.Konto;
import eks.prak.zwei.entities.Kunde;
import eks.prak.zwei.entities.Transaktion;
import eks.prak.zwei.verwaltung.AlleKonten;
import eks.prak.zwei.verwaltung.AlleKunden;
import eks.prak.zwei.verwaltung.AlleTransaktionen;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/")
public class Resource {
    private AlleKunden alleKunden;
    private AlleKonten alleKonten;
    private AlleTransaktionen alleTransaktionen;

    public Resource(){
        alleKunden = alleKunden.getInstance();
        alleKonten = alleKonten.getInstance();
        alleTransaktionen = alleTransaktionen.getInstance();
    }

    // 1.
    // Neuen Kunden erzeugen
    @POST
    @Path("/kunden")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public void createKunde(String name,String adresse){
        Kunde kunde = new Kunde(name,adresse);
        alleKunden.addKunde(kunde);
    }

    // 2.
    // Kunden anhand seines Namens suchen
    @GET
    @Path("/kunden/{name}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Kunde getKundeByName(@PathParam("name") String name){
        return alleKunden.getKundeByName(name);
    }

    // 3.
    /*
     * Kunde mit gegebenem Namen soll ein neues Konto erstellen.
     * Der neue gewünschte Kontostand wird übergeben
     * Der Dienst liefert die neue Kontonummer als String zurück
     * Ein Kunde kann mehrere Konten besitzen
     * Kontonummer muss eindeutig sein
     * */
    @POST
    @Path("/kunden/{name}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public String addKonto(@PathParam("name") String name ,String stand){
        int kStand = Integer.parseInt(stand);

        Kunde kunde = alleKunden.getKundeByName(name);
        if(kunde == null ){
            // Angegebener Kunde wurde nicht gefunden
            // TODO geeignete Fehlerausgabe einrichten
            return null;
        }

        Konto konto = new Konto(kStand,kunde);
        kunde.addKonto(konto);

        return Integer.toString(konto.getNummer());
    }

    // 4.
    /* Für ein Konto mit gegebener Nummer
     *  @Path /konten/{nummer}
     * Neue Transaktion anlegen
     * @Post
     *
     * Antwort Name des Kunden, neuer Kontostand
     *
     *
     * */
    @Path("/konten/{nummer}")
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public String addTransaktion(@PathParam("nummer") String nummer, String transaktionsTyp, int betrag){

        Konto konto = alleKonten.getKontoByNummer(Integer.parseInt(nummer));
        Transaktion transaktion;

        if(konto == null){
            return "Konto not found";
        }

        if(transaktionsTyp.equals("EINZAHLUNG")){
            transaktion = new Transaktion(Transaktion.Transaktionstyp.EINZAHLUNG,betrag,konto);
        } else if (transaktionsTyp.equals("AUSZAHLUNG")){
            transaktion = new Transaktion(Transaktion.Transaktionstyp.AUSZAHLUNG,betrag,konto);
        }else{
            return "Ambivalenter TransaktionsTyp";
        }

        konto.addTransaktion(transaktion);

        return konto.getBesitzer().getName() + " " + Integer.toString(konto.getStand());
    }

    /*
     * 5.
     * Alle Konten eines Kunden sollen ausgegeben werden
     * */
    @Path("/kunden/{name}/konten ")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public ArrayList<Konto> getKontenFromKunde(@PathParam("name") String name){
        Kunde kunde = alleKunden.getKundeByName(name);
        if(kunde == null){
            return null;
        }
        ArrayList<Konto> konten = kunde.getKonten();
        return konten;
    }

    /*
     * 6.
     * FÚr ein Konto mit gegebener NUmmer soll der Kontostand erfragt
     * und als String zurückgegeben werden
     * */
    @Path("konten/{nummer}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public String getStand(@PathParam("nummer") int nummer){
        Konto konto = alleKonten.getKontoByNummer(nummer);
        return Integer.toString(konto.getStand());
    }

    /*
     * 7.
     * Alle Transaktionen eines Kontos mit gegebener Nummer
     * zurückgeben
     */
    @Path("konten/{nummer}/transaktionen")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public ArrayList<Transaktion> getTransaktionenFromKonto(@PathParam("nummer") int nummer){
        Konto konto = alleKonten.getKontoByNummer(nummer);
        return konto.getTransaktionen();
    }

    /*
     * 8.
     * Alle Kunden ausgeben
     * */
    @Path("/kunden")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Kunde> getAlleKunden(){
        return alleKunden.getAlleKunden();
    }

}
