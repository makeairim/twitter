package com.code.challenge.twitter.domain;

import lombok.*;

import java.util.UUID;

@Value
@EqualsAndHashCode
@AllArgsConstructor
@Getter
class FavouriteAuthor {
    @NonNull
    private AuthorId followerId;
    @NonNull
    private AuthorId followedId;

    FavouriteAuthor(UUID followedId, UUID followerId) {
        this(new AuthorId(followerId), new AuthorId(followedId));
    }
}

