package greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int PORT = 5051;
        GreetingServerImpl greetingServer = new GreetingServerImpl();
        Server server = ServerBuilder
                .forPort(PORT)
                .addService(greetingServer)
                .build();
        server.start();
        System.out.println("Server started");
        System.out.println("Listening to " + PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Receive shotdown request");
            server.shutdown();
            System.out.println("Server stopped");
        }));
        server.awaitTermination();
    }
}
