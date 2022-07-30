package greeting.server;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public  void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseStreamObserver){
        String greetingBandung = "Halo Bandung, Nama Saya " + request.getFirstName();
        GreetingResponse greetingResponse = GreetingResponse
                .newBuilder()
                .setResult(greetingBandung)
                .build();
        responseStreamObserver.onNext(greetingResponse);
        responseStreamObserver.onCompleted();
    }
}
