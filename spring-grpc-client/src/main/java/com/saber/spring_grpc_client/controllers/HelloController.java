package com.saber.spring_grpc_client.controllers;

import com.saber.spring_grpc_client.dto.BookResponseDto;
import com.saber.spring_grpc_client.dto.HelloResponseDto;
import com.saber.spring_grpc_client.services.BookService;
import com.saber.spring_grpc_client.services.HelloService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "hello api")
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @Operation(summary = "sayHello", description = "sayHello", parameters = {
            @Parameter(name = "firstName", in = ParameterIn.QUERY, required = true, example = "saber"),
            @Parameter(name = "lastName", in = ParameterIn.QUERY, required = true, example = "azizi")
    }
            , responses = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = HelloResponseDto.class)))
    })
    @GetMapping("/sayHello")
    public ResponseEntity<HelloResponseDto> getBook(@RequestParam(required = true)
                                                    String firstName,
                                                    @RequestParam(required = true)
                                                    String lastName) {
        return ResponseEntity.ok(helloService.sayHello(firstName, lastName));
    }
}
