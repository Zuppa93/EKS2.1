package eks.prak.zwei.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class startClient {
    public static void main(String [] args){

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:55554/bankservices");
        //String response = target.path().request().accept().get()
    }
}
