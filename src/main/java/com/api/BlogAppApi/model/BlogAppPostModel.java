package com.api.BlogAppApi.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private LocalDate data;

    @Column(nullable = false, length = 70)
    private String titulo;

    @Lob
    @Column(columnDefinition = "text")
    private String texto;

    @OneToMany
    private List<PostsComentarioModel> postsComentario;

    @OneToMany(mappedBy = "postModel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostsComentarioModel> comentarios = new ArrayList<>();

    public List<PostsComentarioModel> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<PostsComentarioModel> comentarios) {
        this.comentarios = comentarios;
    }

    public List<PostsComentarioModel> getPostsComentario() {
        return postsComentario;
    }

    public void setPostsComentario(List<PostsComentarioModel> postsComentario) {
        this.postsComentario = postsComentario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
