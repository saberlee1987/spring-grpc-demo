package com.saber.spring_grpc_server.services;


import com.saber.spring_grpc_server.constants.Type;
import com.saber.spring_grpc_server.proto.BookRequest;
import com.saber.spring_grpc_server.proto.BookResponse;
import com.saber.spring_grpc_server.proto.BookServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class BookService extends BookServiceGrpc.BookServiceImplBase {

    @Override
    public void getBook(BookRequest request, StreamObserver<BookResponse> responseObserver) {
        String bookId = request.getBookId();

        log.info("request for bookId ===> {}",bookId);
        BookResponse bookResponse = BookResponse.newBuilder()
                .setBookId(bookId)
                .setName("spring boot 3")
                .setType(Type.AUTOBIOGRAPHY)
                .build();
        log.info("response for bookId ===> {} ===> {}",bookId,bookResponse);
        responseObserver.onNext(bookResponse);
        responseObserver.onCompleted();
    }
}
