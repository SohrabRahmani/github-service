package com.shopapotheke.githubservice;

import com.shopapotheke.githubservice.model.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrateTestApplicationTests {
    private static final String API_BASE_URL = "/api/github/repo";
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void github_general_IntegrationTest() {
        webTestClient.get().uri(
                        uriBuilder -> uriBuilder
                                .path(API_BASE_URL)
                                .queryParam("created", ">2019-01-10")
                                .queryParam("sort", "star")
                                .queryParam("order", "desc")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Repository.class);
    }

    @Test
    public void github_sortByStar_IntegrationTest() {
        webTestClient.get().uri(
                        uriBuilder -> uriBuilder
                                .path(API_BASE_URL)
                                .queryParam("created", ">1970-01-01")
                                .queryParam("sort", "star")
                                .queryParam("order", "desc")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Repository.class);
    }

    @Test
    public void github_top10_IntegrationTest() {
        webTestClient.get().uri(
                        uriBuilder -> uriBuilder
                                .path(API_BASE_URL)
                                .queryParam("created", ">1970-01-01")
                                .queryParam("sort", "star")
                                .queryParam("order", "desc")
                                .queryParam("per_page", "10")
                                .queryParam("page", "1")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Repository.class);
    }

    @Test
    public void github_top50_IntegrationTest() {
        webTestClient.get().uri(
                        uriBuilder -> uriBuilder
                                .path(API_BASE_URL)
                                .queryParam("created", ">1970-01-01")
                                .queryParam("sort", "star")
                                .queryParam("order", "desc")
                                .queryParam("per_page", "50")
                                .queryParam("page", "1")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Repository.class);
    }

    @Test
    public void github_createdFilter_IntegrationTest() {
        webTestClient.get().uri(
                        uriBuilder -> uriBuilder
                                .path(API_BASE_URL)
                                .queryParam("created", ">2022-01-01")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Repository.class);
    }

    @Test
    public void github_programingLanguageFilter_IntegrationTest() {
        webTestClient.get().uri(
                        uriBuilder -> uriBuilder
                                .path(API_BASE_URL)
                                .queryParam("language", "java")
                                .queryParam("per_page", "10")
                                .queryParam("page", "1")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Repository.class);
    }

    @Test
    public void github_badRequest_IntegrationTest() {
        webTestClient.get().uri(
                        uriBuilder -> uriBuilder
                                .path(API_BASE_URL)
                                .queryParam("per_page", "10")
                                .queryParam("page", "1")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Repository.class);
    }
}
