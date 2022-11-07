package com.shopapotheke.githubservice.service;

import com.shopapotheke.githubservice.model.Repository;
import reactor.core.publisher.Flux;

public abstract class GithubService {
    public abstract Flux<Repository> listGithubRepositories(String created, String language, Integer perPage, Integer page, String sort, String order);

    public String queryCreator(String created, String language) {
        StringBuilder populate = new StringBuilder();
        if (created != null) {
            populate.append("created:").append(created);
        }
        if (language != null) {
            if (populate.length() > 0) {
                populate.append("+");
            }
            populate.append("language:").append(language);
        }

        return populate.toString();
    }
}
