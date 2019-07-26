package com.code.challenge.twitter.services.posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Post {
    @Id
    Long id;
    String content;
    String authorName;
    String authorId;
}

