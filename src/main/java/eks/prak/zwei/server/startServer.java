package eks.prak.zwei.server;

import com.sun.net.httpserver.HttpServer;
import eks.prak.zwei.resource.ResourceKunde;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.*;
import java.net.URI;

public class startServer {
    public static void main(String[]args) throws Exception {
        // Server steht
        URI endpoint = new URI("http://localhost:55554/bankservices");
        ResourceConfig rc = new ResourceConfig(ResourceKunde.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(endpoint, rc);

    }
}
