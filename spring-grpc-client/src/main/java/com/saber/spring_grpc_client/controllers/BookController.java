package com.saber.spring_grpc_client.controllers;

import com.saber.spring_grpc_client.dto.BookResponseDto;
import com.saber.spring_grpc_client.services.BookService;
import com.saber.spring_grpc_server.proto.BookResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "book api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Operation(summary = "getBook", description = "getBook", parameters = @Parameter(name = "bookId",
            in = ParameterIn.PATH, required = true, example = "789532642598")
            , responses = {
            @ApiResponse(responseCode = "200",description = "Success",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class)))
    })
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable String bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }
}
