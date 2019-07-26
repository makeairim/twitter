package com.code.challenge.twitter.services.author.command;

import lombok.NonNull;
import lombok.Value;

@Value
public class AddPostCommand {
    @NonNull
    String message;
    @NonNull
    String nickName;
}
