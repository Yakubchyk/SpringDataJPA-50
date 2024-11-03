package com.springboot.springdata50jpa.controller;

import com.springboot.springdata50jpa.entity.Post;
import com.springboot.springdata50jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post save = postRepository.save(post);
        return ResponseEntity.ok(save);
    }
}
