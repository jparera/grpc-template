package net.wrlt.grpc.template.client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.StatusRuntimeException;

import net.wrlt.grpc.template.shared.proto.EchoGrpc;
import net.wrlt.grpc.template.shared.proto.EchoRequest;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var channel = Grpc.newChannelBuilder("localhost:50051", InsecureChannelCredentials.create()).build();
        try {
            var stub = EchoGrpc.newBlockingStub(channel);
            try {
                var request = EchoRequest.newBuilder().setContent("Hello!").build();
                var reply = stub.echo(request);
                System.out.println(reply.getContent());
            } catch(StatusRuntimeException e) {
                e.printStackTrace();
            }
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
