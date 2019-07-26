package com.code.challenge.twitter.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class FavouriteDeletion {
    @NotBlank
    String followedAuthorId;
}
