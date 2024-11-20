package com.saber.spring_grpc_client.services;

import com.saber.spring_grpc_client.dto.BookResponseDto;
import com.saber.spring_grpc_server.proto.BookRequest;
import com.saber.spring_grpc_server.proto.BookResponse;
import com.saber.spring_grpc_server.proto.BookServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

    public BookResponseDto getBook(String bookId) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8090)
                .usePlaintext()
                .build();
        BookServiceGrpc.BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(channel);
        BookResponse bookResponse = stub.getBook(BookRequest.newBuilder().setBookId(bookId).build());
        BookResponseDto responseDto = BookResponseDto.builder()
                .bookId(bookResponse.getBookId())
                .name(bookResponse.getBookId())
                .type(bookResponse.getType().name())
                .build();
        channel.shutdown();
        log.info("response for bookId ==> {} ===> {}", bookId, responseDto);
        return responseDto;
    }
}
