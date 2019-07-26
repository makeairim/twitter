package com.code.challenge.twitter.services.author.command;

import lombok.NonNull;
import lombok.Value;

@Value
public class AddAuthorCommand {
    @NonNull
    String nickName;
}
