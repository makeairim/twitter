package com.code.challenge.twitter.web;

import com.code.challenge.twitter.services.author.AuthorService;
import com.code.challenge.twitter.services.author.command.AddPostCommand;
import com.code.challenge.twitter.services.author.command.FollowAuthorCommand;
import com.code.challenge.twitter.services.author.command.UnfollowAuthorCommand;
import com.code.challenge.twitter.services.posts.PostReadService;
import com.code.challenge.twitter.services.posts.Timeline;
import com.code.challenge.twitter.services.posts.Wall;
import com.code.challenge.twitter.web.model.AddPostWithAuthor;
import com.code.challenge.twitter.web.model.FavouriteCreation;
import com.code.challenge.twitter.web.model.FavouriteDeletion;
import com.code.challenge.twitter.web.model.PostCreated;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @Autowired
    PostReadService postReadService;

    @PostMapping("/author/post")
    @ResponseStatus(value = HttpStatus.OK)
    PostCreated createPostByAuthorNick(@RequestBody AddPostWithAuthor addPost) {
        return authorService.addPost(new AddPostCommand(addPost.getMessage(), addPost.getAuthor()));
    }

    @GetMapping("/author/{authorId}/wall")
    @ResponseStatus(value = HttpStatus.OK)
    Wall getPostsOnWall(@PathVariable String authorId) {
        return postReadService.getAuthorWall(UUID.fromString(authorId));
    }

    @PostMapping("/author/{authorId}/favourites/creation")
    @ResponseStatus(value = HttpStatus.CREATED)
    void follow(@PathVariable String authorId, @RequestBody FavouriteCreation fovoouriteCreation) {
        authorService.addFollower(new FollowAuthorCommand(UUID.fromString(authorId), UUID.fromString(fovoouriteCreation.getFollowedAuthorId())));
    }

    @PostMapping("/author/{authorId}/favourites/deletion")
    @ResponseStatus(value = HttpStatus.CREATED)
    void unfollow(@PathVariable String authorId, @RequestBody FavouriteDeletion favouriteDeletion) {
        authorService.removeFollower(new UnfollowAuthorCommand(UUID.fromString(authorId), UUID.fromString(favouriteDeletion.getFollowedAuthorId())));
    }

    @GetMapping("/author/{authorId}/timeline")
    @ResponseStatus(value = HttpStatus.OK)
    Timeline getFavouriteAuthorPosts(@PathVariable String authorId) {
        return postReadService.getFavouritesPosts(UUID.fromString(authorId));
    }

}
