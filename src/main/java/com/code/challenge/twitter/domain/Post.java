package com.code.challenge.twitter.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Value
@EqualsAndHashCode(of = "postId")
@AllArgsConstructor
class Post {
    @NonNull
    private MessageContent messageContent;
    @NonNull
    private AuthorId author;
    @NonNull
    private PostId postId;

    Post(String content, AuthorId authorId) {
        this(new MessageContent(content), authorId, new PostId(UUID.randomUUID()));
    }
}

@Value
class MessageContent {
    @NonNull
    private String content;

    MessageContent(String content) {
        if (content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        this.content = content;
    }
}