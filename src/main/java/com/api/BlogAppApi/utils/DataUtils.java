package com.api.BlogAppApi.utils;

import com.api.BlogAppApi.model.BlogAppPostModel;
import com.api.BlogAppApi.repository.BlogAppPostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataUtils {
    @Autowired
    BlogAppPostRepository blogAppPostRepository;

    //@PostConstruct
    public void savePosts() {

        List<BlogAppPostModel> postList = new ArrayList<>();
        BlogAppPostModel post1 = new BlogAppPostModel();
        post1.setAutor("Pokemon");
        post1.setData(LocalDate.now());
        post1.setTexto("Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                + " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
                + "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
                + "It has survived not only five centuries, but also the leap into electronic typesetting, "
                + "remaining essentially unchanged. It was popularised in the 1960s with the release of"
                + " Letraset sheets containing Lorem Ipsum passages, and more recently with desktop "
                + "publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        post1.setTitulo("Lorem Ipsum");

        postList.add(post1);

        for (BlogAppPostModel post : postList) {
            BlogAppPostModel postsave = blogAppPostRepository.save(post);
            System.out.println(postsave.getId());

    }
}
}
