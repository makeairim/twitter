package com.code.challenge.twitter.services.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostReadService {

    @Autowired
    PostRepository postRepository;

    public Wall getAuthorWall(UUID authorId){
        List<Post> posts = postRepository.findByAuthorId(authorId);
        return Wall.builder()
                .authorPosts(posts)
                .build();
    }
    public Timeline getFavouritesPosts(UUID authorId){
        List<Post> posts = postRepository.findByFollowerId(authorId);
        return Timeline.builder()
                .followingPosts(posts)
                .build();
    }
}
