package com.blogposts.repository;

import com.blogposts.entity.Posts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Repository implementation, fetches data from the URL
 */
@Repository
@Qualifier("InMemory")
public class ClientImpl implements Client {

    private static final String GET_BY_TAG_URL = "https://hatchways.io/api/assessment/blog/posts?tag=%s";
    private final RestTemplate restTemplate;

    public ClientImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public Posts getAllPostsByTag(String tag) {

        String url = String.format(GET_BY_TAG_URL, tag);
        return restTemplate.getForObject(url, Posts.class);
    }

    @Override
    public String getStatus() {
        return "{\n \"success\":true\n}";
    }
}
