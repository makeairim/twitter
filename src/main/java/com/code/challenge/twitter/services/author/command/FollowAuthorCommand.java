package com.code.challenge.twitter.services.author.command;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class FollowAuthorCommand {
    @NonNull UUID followerAuthorId;
    @NonNull UUID followedAuthorId;
}
