package eks.prak.zwei.server.resource;


import eks.prak.zwei.server.entities.Konto;
import eks.prak.zwei.server.entities.Kunde;
import eks.prak.zwei.server.verwaltung.AlleKunden;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


// DEPRECATED

@Path("kunden")
public class  ResourceKunde {
    private AlleKunden alleKunden;

    public ResourceKunde(){
        alleKunden = alleKunden.getInstance();
    }

    // 1.
    // Neuen Kunden erzeugen
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public void createKunde(String name,String adresse){
        Kunde kunde = new Kunde(name,adresse);
        alleKunden.addKunde(kunde);
    }

    // 2.
    // Kunden anhand seines Namens suchen
    @GET
    @Path("/{name}")
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
    @Path("/{name}")
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

    /*
    * 5.
    * Alle Konten eines Kunden sollen ausgegeben werden
    * */
    @Path("/{name}/konten ")
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
    * 8.
    * Alle Kunden ausgeben
    * */
    @Path("")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Kunde> getAlleKunden(){
        return alleKunden.getAlleKunden();
    }


}
