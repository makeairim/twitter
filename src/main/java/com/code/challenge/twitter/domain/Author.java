package com.code.challenge.twitter.domain;

import lombok.*;

import java.util.UUID;

@Value
@EqualsAndHashCode(of = "authorId")
@AllArgsConstructor
@Getter
class Author {
    @NonNull
    private AuthorName authorName;
    @NonNull
    private AuthorId authorId;

    Author(String userName) {
        this(new AuthorName(userName), new AuthorId(UUID.randomUUID()));
    }
}

@Value
class AuthorName {
    @NonNull
    private String name;

    AuthorName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.trim();
    }
}