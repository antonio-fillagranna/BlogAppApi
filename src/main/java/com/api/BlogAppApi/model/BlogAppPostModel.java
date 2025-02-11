package com.api.BlogAppApi.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_POST")
public class BlogAppPostModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 70)
    private String autor;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false, length = 70)
    private String titulo;

    @Lob
    @Column(columnDefinition = "text")
    private String texto;
}
