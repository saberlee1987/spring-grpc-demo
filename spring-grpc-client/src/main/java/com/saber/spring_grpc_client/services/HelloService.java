package com.saber.spring_grpc_client.services;

import com.saber.spring_grpc_client.dto.HelloResponseDto;
import com.saber.spring_grpc_server.proto.HelloRequest;
import com.saber.spring_grpc_server.proto.HelloResponse;
import com.saber.spring_grpc_server.proto.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloService {

    public HelloResponseDto sayHello(String firstName , String lastName){
        log.info("request for sayHello firstName : {} , lastName : {}",firstName,lastName);
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress("localhost",8090)
                .usePlaintext()
                .build();
        HelloServiceGrpc.HelloServiceBlockingStub helloServiceStub = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = helloServiceStub.sayHello(HelloRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .build());
        channel.shutdown();

        HelloResponseDto responseDto = HelloResponseDto.builder()
                .message(helloResponse.getMessage())
                .build();
        log.info("response for sayHello firstName : {} , lastName : {} ===> {}"
                ,firstName,lastName,responseDto);
        return responseDto;
    }
}
