package com.code.challenge.twitter.web.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class AddPostWithAuthor {
    @NotBlank
    @Max(value = 140)
    String message;
    @NotBlank
    @Max(value = 20)
    String author;

}
