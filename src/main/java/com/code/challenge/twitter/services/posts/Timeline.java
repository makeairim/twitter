package com.code.challenge.twitter.services.posts;

import lombok.Builder;

import java.util.List;

@Builder
public class Timeline {
    public List<Post>  followingPosts;
}