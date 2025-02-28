package com.api.BlogAppApi.controller;

import com.api.BlogAppApi.dtos.BlogAppComentarioDTO;
import com.api.BlogAppApi.dtos.BlogAppPostWithCommentsDTO;
import com.api.BlogAppApi.dtos.BlogAppRecordPostComentarioDTO;
import com.api.BlogAppApi.model.BlogAppPostModel;
import com.api.BlogAppApi.model.PostsComentarioModel;
import com.api.BlogAppApi.repository.BlogAppPostRepository;
import com.api.BlogAppApi.service.BlogAppPostService;
import com.api.BlogAppApi.service.BlogAppPostServiceComentarios;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BlogAppController {

    @Autowired
    private BlogAppPostService blogAppPostService;
    @Autowired
    private BlogAppPostRepository blogAppPostRepository;
    @Autowired
    private BlogAppPostServiceComentarios blogAppPostServiceComentarios;

    @GetMapping(value = "/posts")
    public ResponseEntity<List<BlogAppPostModel>> getAllPosts() {
        List<BlogAppPostModel> posts = blogAppPostService.findAll();

        if (posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(posts);
        }

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<Object> getPostDetails(@PathVariable("id") UUID id ) {
        Optional<BlogAppPostModel> blogAppModelOptional = blogAppPostService.findById(id);
        //BlogAppPostModel post = blogAppModelOptional.get();
        if (!blogAppModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("blog not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(blogAppModelOptional.get());
    }

    @PostMapping(value = "/newpost")
    public ResponseEntity<Object> savePost(@RequestBody @Valid BlogAppPostModel blogAppPostModel) {
        var postModel = new BlogAppPostModel();
        BeanUtils.copyProperties(blogAppPostModel, postModel);
        //postModel.setData(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(blogAppPostService.save(postModel));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable("id") UUID id) {
        Optional<BlogAppPostModel> blogAppModelOptional = blogAppPostRepository.findById(id);
        if (!blogAppModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("blog not found.");
        }
        blogAppPostRepository.delete(blogAppModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("blog deleted.");
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable("id") UUID id,
                                             @RequestBody @Valid BlogAppPostModel updatedPost) {
        Optional<BlogAppPostModel> existingPostOptional = blogAppPostService.findById(id);

        if (existingPostOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog post not found.");
        }

        BlogAppPostModel existingPost = existingPostOptional.get();

        existingPost.setAutor(updatedPost.getAutor());
        existingPost.setTitulo(updatedPost.getTitulo());
        existingPost.setTexto(updatedPost.getTexto());
        existingPost.setData(LocalDate.now());

        return ResponseEntity.ok(blogAppPostService.save(existingPost));
    }

    @PostMapping(value = "/posts/{id}")
    public ResponseEntity<Object> saveComentarioPost(
            @PathVariable("id") UUID id,
            @RequestBody @Valid BlogAppRecordPostComentarioDTO blogAppRecordPostComentarioDTO) {

        // DEBUG: Verificando se o DTO recebeu o texto corretamente
        System.out.println("Recebido: " + blogAppRecordPostComentarioDTO);

        Optional<BlogAppPostModel> blogAppModelOptional = blogAppPostService.findById(id);
        if (blogAppModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado.");
        }

        var postComentario = new PostsComentarioModel();
        BlogAppPostModel post = blogAppModelOptional.get();

        // Copiar os valores corretamente
        BeanUtils.copyProperties(blogAppRecordPostComentarioDTO, postComentario);

        // Verificar se o texto foi realmente copiado
        System.out.println("Texto do comentário: " + postComentario.getTexto());

        postComentario.setPostModel(post);
        postComentario.setData(LocalDate.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(blogAppPostServiceComentarios.saveComentario(postComentario));
    }

    @GetMapping("/posts-with-comments")
    public ResponseEntity<List<BlogAppPostWithCommentsDTO>> getPostsWithComments() {
        List<BlogAppPostModel> posts = blogAppPostService.findAll();

        List<BlogAppPostWithCommentsDTO> postsWithComments = posts.stream().map(post ->
                new BlogAppPostWithCommentsDTO(
                        post.getId(),
                        post.getAutor(),
                        post.getData(),
                        post.getTitulo(),
                        post.getTexto(),
                        post.getComentarios().stream()
                                .map(comentario -> new BlogAppComentarioDTO(
                                        comentario.getId(),
                                        comentario.getTexto(),
                                        comentario.getData()
                                )).toList()
                )
        ).toList();

        return ResponseEntity.ok(postsWithComments);
    }

}
