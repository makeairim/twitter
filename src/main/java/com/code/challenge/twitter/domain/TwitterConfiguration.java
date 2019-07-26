package com.code.challenge.twitter.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Import({TwitterDatabaseConfig.class})
public class TwitterConfiguration {

    @Bean
    TwitterFacade twitter(AuthorDatabase authorDatabase) {
        return new TwitterFacade(authorDatabase);
    }

    @Bean
    AuthorDatabase authorDatabase(JdbcTemplate jdbcTemplate) {
        return new AuthorDatabase(jdbcTemplate);
    }
}
