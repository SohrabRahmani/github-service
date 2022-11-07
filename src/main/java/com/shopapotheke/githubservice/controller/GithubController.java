package com.shopapotheke.githubservice.controller;

import com.shopapotheke.githubservice.model.Repository;
import com.shopapotheke.githubservice.service.impl.GithubServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("/api/github")
public class GithubController {
    @Autowired
    private GithubServiceImpl githubClient;

    public GithubController(GithubServiceImpl githubClient) {
        this.githubClient = githubClient;
    }

    @GetMapping("/repo")
    public Flux<Repository> githubRepositoriesList(
            @RequestParam(required = false) String created,
            @RequestParam(required = false) String language,
            @RequestParam(required = false, name = "per_page") Integer perPage,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order
    ) {
        return githubClient.listGithubRepositories(created, language, perPage, page, sort, order);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> responseExceptionHandler(WebClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }
}
