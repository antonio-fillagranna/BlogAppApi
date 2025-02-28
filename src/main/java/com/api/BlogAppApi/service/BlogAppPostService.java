package com.api.BlogAppApi.service;

import com.api.BlogAppApi.model.BlogAppPostModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlogAppPostService {
    List<BlogAppPostModel> findAll();
    Optional<BlogAppPostModel> findById(UUID id);
    BlogAppPostModel save(BlogAppPostModel post);
    void delete(BlogAppPostModel post);
}
