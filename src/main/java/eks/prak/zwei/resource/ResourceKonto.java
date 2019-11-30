package eks.prak.zwei.resource;

import eks.prak.zwei.entities.Konto;
import eks.prak.zwei.verwaltung.AlleKonten;
import eks.prak.zwei.entities.Transaktion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

// DEPRECATED

@Path("/konten")
public class ResourceKonto {

    private AlleKonten alleKonten;

    public ResourceKonto(){
        alleKonten = alleKonten.getInstance();
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
    @Path("/{nummer}")
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
    * 6.
    * FÚr ein Konto mit gegebener NUmmer soll der Kontostand erfragt
    * und als String zurückgegeben werden
    * */
    @Path("/{nummer}")
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
    @Path("/{nummer}/transaktionen")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public ArrayList<Transaktion> getTransaktionenFromKonto(@PathParam("nummer") int nummer){
        Konto konto = alleKonten.getKontoByNummer(nummer);
        return konto.getTransaktionen();
    }
}
