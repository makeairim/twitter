package com.code.challenge.twitter.domain;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

@AllArgsConstructor
class AuthorDatabase {

    private final JdbcTemplate jdbcTemplate;

    Post saveNew(Post post, LocalDateTime creationDate) {
        jdbcTemplate.update("" +
                        "INSERT INTO post " +
                        "(id, post_id, author_id, content,creation_date) VALUES " +
                        "(post_seq.nextval, ?, ?, ?, ?)",
                post.getPostId().getPostId(), post.getAuthor().getUserId(),
                post.getMessageContent().getContent(), creationDate);
        return post;
    }

    Author saveNew(Author author, LocalDateTime creationDate) {
        jdbcTemplate.update("" +
                        "INSERT INTO author " +
                        "(id, author_id, name,creation_date) VALUES " +
                        "(author_seq.nextval, ?, ?, ?)",
                author.getAuthorId().getUserId(), author.getAuthorName().getName(),
                creationDate);
        return author;
    }

    Option<Author> findBy(AuthorName authorName) {
        try {
            return Option.of(
                    jdbcTemplate.queryForObject(
                            "SELECT a.* FROM author a WHERE a.name = ?",
                            new BeanPropertyRowMapper<>(AuthorDatabaseRow.class),
                            authorName.getName())
                            .toAuthor());
        } catch (EmptyResultDataAccessException e) {
            return Option.none();
        }
    }

    Option<Author> findBy(AuthorId authorId) {
        try {
            return Option.of(
                    jdbcTemplate.queryForObject(
                            "SELECT a.* FROM author a WHERE a.author_id = ?",
                            new BeanPropertyRowMapper<>(AuthorDatabaseRow.class),
                            authorId.getUserId())
                            .toAuthor());
        } catch (EmptyResultDataAccessException e) {
            return Option.none();
        }
    }

    Option<Boolean> findBy(FavouriteAuthor favouriteAuthor) {
        try {
            return Option.of(
                    jdbcTemplate.queryForObject(
                            "SELECT count(*)>0 FROM favourite_author fa " +
                                    "WHERE fa.follower_id = ? AND fa.followed_id = ?",
                            Boolean.class,
                            favouriteAuthor.getFollowerId().getUserId(), favouriteAuthor.getFollowedId().getUserId()));
        } catch (EmptyResultDataAccessException e) {
            return Option.none();
        }
    }

    FavouriteAuthor saveNew(FavouriteAuthor favouriteAuthor) {
        jdbcTemplate.update("" +
                        "INSERT INTO favourite_author " +
                        "(id, follower_id, followed_id) VALUES " +
                        "(favourite_author_seq.nextval, ?, ?)",
                favouriteAuthor.getFollowerId().getUserId().toString(), favouriteAuthor.getFollowedId().getUserId().toString());
        return favouriteAuthor;
    }

    FavouriteAuthor delete(FavouriteAuthor favouriteAuthor) {
        jdbcTemplate.update("" +
                        "DELETE FROM favourite_author fa " +
                        "WHERE " +
                        "fa.follower_id = ? or fa.followed_id = ?",
                favouriteAuthor.getFollowerId().getUserId(), favouriteAuthor.getFollowedId().getUserId());
        return favouriteAuthor;
    }

}

@Data
@NoArgsConstructor
class AuthorDatabaseRow {
    String authorId;
    String name;

    Author toAuthor() {
        return new Author(new AuthorName(name), new AuthorId(authorId));
    }
}