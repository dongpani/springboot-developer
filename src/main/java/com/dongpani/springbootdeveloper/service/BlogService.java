package com.dongpani.springbootdeveloper.service;

import com.dongpani.springbootdeveloper.domain.Article;
import com.dongpani.springbootdeveloper.dto.AddArticleRequest;
import com.dongpani.springbootdeveloper.dto.UpdateArticleRequest;
import com.dongpani.springbootdeveloper.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("not found:" +id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("not found:" +id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }


}
