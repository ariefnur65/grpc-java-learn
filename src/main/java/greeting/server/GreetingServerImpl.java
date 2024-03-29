package greeting.server;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;

public class GreetingServerImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        String resultGreeting = "Hello " + request.getFirstName();
        GreetingResponse response = GreetingResponse
                .newBuilder()
                .setResult(resultGreeting)
                .build();
        responseObserver.onNext(response);
    }
}
