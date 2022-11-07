package com.shopapotheke.githubservice.service.impl;

import com.shopapotheke.githubservice.model.Repository;
import com.shopapotheke.githubservice.service.GithubService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Slf4j
@Service
public class GithubServiceImpl extends GithubService {
    private static final String GITHUB_API_BASE_URL = "https://api.github.com";
    private final WebClient webClient;

    @Autowired
    public GithubServiceImpl() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        this.webClient = WebClient.builder()
                .exchangeStrategies(strategies)
                .baseUrl(GITHUB_API_BASE_URL)
                .build();
    }

    @SneakyThrows
    @Override
    public Flux<Repository> listGithubRepositories(String created, String language, Integer perPage, Integer page, String sort, String order) {
        String q = queryCreator(created, language);
        if (q.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "As a query, one of the created or programming_language " +
                            "request parameters should always be applied. " +
                            "The documentation provides more information: " +
                            "https://docs.github.com/v3/search ");
        }
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/repositories")
                        .queryParam("q", q)
                        .queryParamIfPresent("per_page", Optional.ofNullable(perPage))
                        .queryParamIfPresent("page", Optional.ofNullable(page))
                        .queryParamIfPresent("sort", Optional.ofNullable(sort))
                        .queryParamIfPresent("order", Optional.ofNullable(order))
                        .build())
                .retrieve()
                .bodyToFlux(Repository.class);
    }
}
