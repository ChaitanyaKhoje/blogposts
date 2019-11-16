package com.blogposts.entity;

import com.blogposts.service.BlogpostComparator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Posts {

    @JsonProperty("posts")
    private Set<Blogpost> blogPosts;

    public Posts() {
        blogPosts = new LinkedHashSet<>();
    }

    public Posts(BlogpostComparator blogpostComparator) {
        this.blogPosts = new TreeSet<>(blogpostComparator);
    }

    public Set<Blogpost> getBlogPosts() {
        return blogPosts;
    }

    public void addBlogPosts(Set<Blogpost> blogPosts) {
        this.blogPosts.addAll(blogPosts);
    }

    @Override
    public String toString() {
        return "Post{" +
                "blogposts=" + blogPosts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posts posts = (Posts) o;
        return blogPosts.equals(posts.blogPosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogPosts);
    }
}
