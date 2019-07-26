package com.code.challenge.twitter.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class AuthorId {
    @NonNull UUID userId;
    AuthorId(String uuid){
        this.userId = UUID.fromString(uuid);
    }
}
