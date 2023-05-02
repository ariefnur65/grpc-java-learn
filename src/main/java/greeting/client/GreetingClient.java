package greeting.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
    public static void main(String[] args) {
        int PORT = 50051;
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", PORT)
                .usePlaintext()
                .build();

        System.out.println("Shutdown...");
        managedChannel.shutdown();
    }
}
