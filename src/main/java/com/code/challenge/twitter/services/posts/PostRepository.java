package com.code.challenge.twitter.services.posts;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

interface PostRepository extends CrudRepository<Post, Long> {
    @Query("SELECT p.*,a.name as author_name FROM post p JOIN author a ON p.author_id = a.author_id " +
            "WHERE p.author_id = :authorId " +
            "ORDER BY p.creation_date DESC")
    List<Post> findByAuthorId(@Param("authorId") UUID authorId);

    @Query("SELECT p.*, a.name as author_name FROM favourite_author fa JOIN author a ON fa.followed_id = a.author_id " +
            "JOIN post p on p.author_id = fa.followed_id " +
            "WHERE fa.follower_id = :followerId  " +
            "ORDER BY p.creation_date DESC")
    List<Post> findByFollowerId(@Param("followerId") UUID authorId);

}
