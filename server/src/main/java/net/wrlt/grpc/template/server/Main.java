package net.wrlt.grpc.template.server;

import java.io.IOException;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.stub.StreamObserver;
import net.wrlt.grpc.template.shared.proto.EchoGrpc;
import net.wrlt.grpc.template.shared.proto.EchoReply;
import net.wrlt.grpc.template.shared.proto.EchoRequest;

public class Main {
    private static final int PORT = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {
        var builder = Grpc.newServerBuilderForPort(PORT, InsecureServerCredentials.create());
        var server = builder.addService(new EchoService()).build();
        server.start();
        System.out.printf("Echo service listening on port %s...\n", PORT);
        server.awaitTermination();
    }

    private static class EchoService extends EchoGrpc.EchoImplBase {
        @Override
        public void echo(EchoRequest request, StreamObserver<EchoReply> responseObserver) {
            var reply = EchoReply.newBuilder().setContent(request.getContent()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
