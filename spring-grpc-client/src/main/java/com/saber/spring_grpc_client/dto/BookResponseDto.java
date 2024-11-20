package com.saber.spring_grpc_client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private String bookId;
    private String name;
    private String type;
}
