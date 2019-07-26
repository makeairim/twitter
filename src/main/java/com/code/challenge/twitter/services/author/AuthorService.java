package com.code.challenge.twitter.services.author;

import com.code.challenge.twitter.commons.Result;
import com.code.challenge.twitter.domain.AuthorId;
import com.code.challenge.twitter.domain.PostId;
import com.code.challenge.twitter.domain.TwitterFacade;
import com.code.challenge.twitter.services.author.command.AddPostCommand;
import com.code.challenge.twitter.services.author.command.FollowAuthorCommand;
import com.code.challenge.twitter.services.author.command.UnfollowAuthorCommand;
import com.code.challenge.twitter.web.model.PostCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AuthorService {

    @Autowired
    TwitterFacade twitterFacade;

    public PostCreated addPost(AddPostCommand addPostCommand) {
        LocalDateTime creationDate = LocalDateTime.now();
        AuthorId authorId = twitterFacade.addAuthor(addPostCommand.getNickName(), creationDate)
                .getOrElseThrow(() -> new IllegalStateException("Cannot save post"));
        PostId postId = twitterFacade.addPost(addPostCommand.getMessage(), authorId, creationDate)
                .getOrElseThrow(() -> new IllegalStateException("Cannot save post"));
        return new PostCreated(postId.getPostId().toString(), authorId.getUserId().toString());
    }

    public void addFollower(FollowAuthorCommand followAuthorCommand) {
        AuthorId followedId = twitterFacade.findAuthorById(followAuthorCommand.getFollowedAuthorId())
                .getOrElseThrow(() -> new IllegalArgumentException("Author not found"));
        AuthorId followerId = twitterFacade.findAuthorById(followAuthorCommand.getFollowerAuthorId())
                .getOrElseThrow(() -> new IllegalArgumentException("Author not found"));
        boolean sameUser = followedId.getUserId().equals(followerId.getUserId());
        if (sameUser) {
            throw new IllegalArgumentException("Cannot follow same Author");
        }
        twitterFacade.addFollower(followerId, followedId)
                .filter(Result.Success::equals)
                .getOrElseThrow(() -> new IllegalStateException("Cannot follow Author"));
    }

    public void removeFollower(UnfollowAuthorCommand unfollowAuthorCommand) {
        AuthorId followedId = twitterFacade.findAuthorById(unfollowAuthorCommand.getFollowedAuthorId())
                .getOrElseThrow(() -> new IllegalArgumentException("Author not found"));
        AuthorId followerId = twitterFacade.findAuthorById(unfollowAuthorCommand.getFollowerAuthorId())
                .getOrElseThrow(() -> new IllegalArgumentException("Author not found"));
        twitterFacade.removeFollower(followerId, followedId)
                .filter(Result.Success::equals)
                .getOrElseThrow(() -> new IllegalStateException("Cannot unfollow author"));
    }
}
