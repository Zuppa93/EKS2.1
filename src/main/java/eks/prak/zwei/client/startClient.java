package eks.prak.zwei.client;

import eks.prak.zwei.entities.Kunde;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


public class startClient {
    public static void main(String [] args){

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:55554/bankservices");
        Kunde kunde1 = new Kunde("Peter","Bonn");
        Kunde kunde2 = new Kunde("Michael","Aachen");
        Kunde kunde3 = new Kunde("Klaus","Paderborn");

        // Keine Angaben in .request() weil keine Antwort erwartet wird
        target.path("/kunden").request().post(Entity.entity(kunde1,MediaType.APPLICATION_XML_TYPE));
        target.path("/kunden").request().post(Entity.entity(kunde2,MediaType.APPLICATION_XML_TYPE));
        target.path("/kunden").request().post(Entity.entity(kunde3,MediaType.APPLICATION_XML_TYPE));

        ArrayList<Kunde> response = target.path("/kunden").request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).get(ArrayList.class);
        System.out.println(response);
    }
}
