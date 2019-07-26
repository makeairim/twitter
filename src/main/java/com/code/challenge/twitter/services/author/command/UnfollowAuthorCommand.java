package com.code.challenge.twitter.services.author.command;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class UnfollowAuthorCommand {
    @NonNull UUID followerAuthorId;
    @NonNull UUID followedAuthorId;
}
