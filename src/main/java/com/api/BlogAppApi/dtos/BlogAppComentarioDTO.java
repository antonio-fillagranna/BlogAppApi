package com.api.BlogAppApi.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record BlogAppComentarioDTO(
        UUID id,
        String texto,
        LocalDate data
) {}
