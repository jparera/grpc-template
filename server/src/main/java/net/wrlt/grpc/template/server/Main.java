package net.wrlt.grpc.template.server;

import net.wrlt.grpc.template.shared.proto.Message;

public class Main {
    public static void main(String[] args) {
        var message = Message.newBuilder().setContent("Text").build();
        System.out.printf("[server] Message = {\n%s}\n", message);
    }
}
