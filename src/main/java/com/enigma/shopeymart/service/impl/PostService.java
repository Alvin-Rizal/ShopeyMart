package com.enigma.shopeymart.service.impl;

import com.enigma.shopeymart.entity.Posts;
import com.enigma.shopeymart.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    @Value("${api.endpoint.url.post}")
    private String BASE_URL;

    private final RestTemplate restTemplate;
    private final PostRepository postRepository;

    public ResponseEntity<List<Posts>> getAllPost(){
        //Ambil post dari RestApi
        ResponseEntity<Posts[]> apiResponse = restTemplate.getForEntity(BASE_URL, Posts[].class);
        List<Posts> externalPosts = List.of(apiResponse.getBody());

        //Ambil Post dari Local database
        List<Posts> dbPost = postRepository.findAll();

        //Gabungin post db dan api rest
        dbPost.addAll(externalPosts);

        return ResponseEntity.ok(dbPost);
    }

    public ResponseEntity<String> getPostById(Long id){
        return responseMethod(restTemplate.getForEntity(BASE_URL +"/" + id,String.class),"Failed to Load Data");
    }

    private ResponseEntity<String> responseMethod(ResponseEntity<String> restTemplate,String message) {
        ResponseEntity<String> responseEntity = restTemplate;
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            String responseBody = responseEntity.getBody();
            return ResponseEntity.ok(responseBody);
        }
        return ResponseEntity.status(responseEntity.getStatusCode()).body(message);
    }

    public ResponseEntity<String> createPost(Posts posts) {
        //Mengatur header permintaan
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Membungkus data permintaan kedalam Httpentity
        HttpEntity<Posts> requestEntity = new HttpEntity<>(posts,headers);

        //Save Post to Database
        postRepository.save(posts);

        //response
        return responseMethod(restTemplate.postForEntity(BASE_URL,requestEntity,String.class),"Failed to Create data");
    }

}
