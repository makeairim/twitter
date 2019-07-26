package com.code.challenge.twitter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class PostId {
    @NonNull UUID postId;
    PostId(String uuid){
        this.postId = UUID.fromString(uuid);
    }
}
