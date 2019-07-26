package com.code.challenge.twitter.domain;

import com.code.challenge.twitter.commons.Result;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.code.challenge.twitter.commons.Result.Failure;
import static com.code.challenge.twitter.commons.Result.Success;

@AllArgsConstructor
public class TwitterFacade {

    private final AuthorDatabase database;

    public Try<PostId> addPost(String content, AuthorId authorId, LocalDateTime creationDate) {
        return Try.of(() -> {
            Post post = new Post(content, authorId);
            database.saveNew(post,creationDate);
            return post.getPostId();
        });
    }

    public Try<AuthorId> addAuthor(String name, LocalDateTime creationDate) {
        return Try.of(() -> database
                .findBy(new AuthorName(name))
                .map(Author::getAuthorId)
                .getOrElse(()->{
                    Author instance = new Author(name);
                    database.saveNew(instance, creationDate);
                    return instance.getAuthorId();
                }));
    }
    public Try<Result> addFollower(AuthorId follower, AuthorId followed) {
        return Try.of(() -> database
                .findBy(new FavouriteAuthor(follower, followed))
                .filter(Boolean.FALSE::equals)
                .map(i->database.saveNew(new FavouriteAuthor(follower, followed)))
                .map(savedInstance -> Success)
                .getOrElse(Failure));
    }
    public Try<Result> removeFollower(AuthorId follower, AuthorId followed) {
        return Try.of(() -> database
                .findBy(new FavouriteAuthor(follower, followed))
                .filter(Boolean.TRUE::equals)
                .map(i->database.delete(new FavouriteAuthor(follower, followed)))
                .map(savedInstance -> Success)
                .getOrElse(Failure));
    }

    public Option<AuthorId> findAuthorByName(String name) {
        return database.findBy(new AuthorName(name))
                .map(Author::getAuthorId);
    }
    public Option<AuthorId> findAuthorById(UUID authorId) {
        return database.findBy(new AuthorId(authorId))
                .map(Author::getAuthorId);
    }

}

