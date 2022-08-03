package greeting.client;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class GreetingClient {
    public static void doGreet(ManagedChannel managedChannel) {
        System.out.println("Enter do Greet");
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(managedChannel);
        GreetingRequest request = GreetingRequest.newBuilder().setFirstName("Clement").build();
        GreetingResponse greetingResponse = stub.greet(request);
        System.out.println("Greeting: " + greetingResponse.getResult());
    }

    private static void doGreetManyTimes(ManagedChannel channel) {
        System.out.println("Enter do love many times, enter your ayang name:");
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        Scanner sc = new Scanner(System.in);
        String nameInputed = sc.nextLine();
        if (nameInputed.equals("Saras") || nameInputed.equals("Riska") || nameInputed.equals("Riska Saras") || nameInputed.equals("Riska Saraswati")) {
            GreetingRequest request = GreetingRequest.newBuilder().setFirstName(nameInputed).build();
            stub.greetManyTimes(request).forEachRemaining(greetingResponse -> System.out.println(greetingResponse.getResult()));
            return;
        }
        System.out.println("Bukan Ayangku!!!!");
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
        } else if ("greet_many_times".equals(args[0])) {
            doGreetManyTimes(managedChannel);
        } else {
            System.out.println("Invalid keyword" + args[0]);
        }

        System.out.println("Shutting down...");

        managedChannel.shutdown();
    }
}
