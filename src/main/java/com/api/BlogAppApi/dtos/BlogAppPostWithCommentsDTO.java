package com.api.BlogAppApi.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record BlogAppPostWithCommentsDTO(
        UUID id,
        String autor,
        LocalDate data,
        String titulo,
        String texto,
        List<BlogAppComentarioDTO> comentarios
) {}
