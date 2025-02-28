package com.api.BlogAppApi.repository;

import com.api.BlogAppApi.model.PostsComentarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostComentarioRepository extends JpaRepository<PostsComentarioModel, UUID> {
}
