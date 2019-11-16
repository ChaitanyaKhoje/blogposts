package com.blogposts.service;

import com.blogposts.entity.Posts;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Service layer
 * Implements pinging and getting data from api
 * Has a cache that holds values for multiple requests
 */
@Service
public class BlogService {

    @Autowired
    @Qualifier("InMemory")
    private com.blogposts.repository.Client client;
    private LoadingCache<String, Posts> cache = CacheBuilder
            .newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .build(new CacheLoader<String, Posts>() {
                @Override
                public Posts load(String tag) throws Exception {
                    return client.getAllPostsByTag(tag);
                }
            });

    public Posts getAllPostsByTag(List<String> tags, String sortBy, String direction) throws ExecutionException {
        BlogpostCompareType compareType = (Strings.isEmpty(sortBy) ?
                BlogpostCompareType.ID : BlogpostCompareType.valueOf(sortBy.toUpperCase()));
        Posts posts = new Posts(new BlogpostComparator(compareType, Strings.isEmpty(direction) ? "asc" : direction));
        for (String tag : tags) {
            Posts currentPosts = cache.get(tag);
            posts.addBlogPosts(currentPosts.getBlogPosts());
        }
        return posts;
    }

    public String getStatus() {
        return this.client.getStatus();
    }

    public String getError() {
        return "{\n \"error\":\"Please check the specified URL\"\n}";
    }
}
