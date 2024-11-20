package com.saber.spring_grpc_server.services;

import com.saber.spring_grpc_server.proto.HelloRequest;
import com.saber.spring_grpc_server.proto.HelloResponse;
import com.saber.spring_grpc_server.proto.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        log.info("request sayHello with parameter  ===> {}",request);
        String message = String.format("Hello %s %s",firstName,lastName);
        HelloResponse helloResponse = HelloResponse.newBuilder()
                .setMessage(message)
                .build();
        log.info("response sayHello with parameter  ===> {} ==> {}"
                ,request, helloResponse);
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();



    }
}
