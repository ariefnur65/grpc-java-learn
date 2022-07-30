package greeting.client;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
    public static void doGreet(ManagedChannel managedChannel) {
        System.out.println("Enter do Greet");
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(managedChannel);
        GreetingRequest request = GreetingRequest.newBuilder().setFirstName("Clement").build();
        GreetingResponse greetingResponse = stub.greet(request);
        System.out.println("Greeting: " + greetingResponse.getResult());
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Need more argument to work");
            return;
        }
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        if ("greet".equals(args[0])) {
            doGreet(managedChannel);
        } else {
            System.out.println("Invalid keyword" + args[0]);
        }

        System.out.println("Shutting down...");

        managedChannel.shutdown();
    }
}
