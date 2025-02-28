package com.api.BlogAppApi.service;

import com.api.BlogAppApi.model.PostsComentarioModel;
import com.api.BlogAppApi.repository.PostComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogAppServiceImplPostComentario implements BlogAppPostServiceComentarios{

    @Autowired
    PostComentarioRepository postComentarioRepository;

    @Override
    public PostsComentarioModel saveComentario(PostsComentarioModel postComentarioModel) {
        return postComentarioRepository.save(postComentarioModel);
    }
}
