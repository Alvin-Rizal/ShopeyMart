package com.enigma.shopeymart.controller;

import com.enigma.shopeymart.entity.Posts;
import com.enigma.shopeymart.service.impl.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Posts>> getAllPosts() {
        return postService.getAllPost();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPostsById(@PathVariable  Long id) {
        return postService.getPostById(id);
    }

    @PostMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> Posts(@RequestBody Posts posts) {
        return postService.createPost(posts);
    }
}
