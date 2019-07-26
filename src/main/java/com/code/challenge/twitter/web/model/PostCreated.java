package com.code.challenge.twitter.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class PostCreated {
    String postId;
    String authorId;
}
