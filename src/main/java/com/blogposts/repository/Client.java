package com.blogposts.repository;

import com.blogposts.entity.Posts;

/**
 * All different types of repository implementations must implement this interface.
 */
public interface Client {
    Posts getAllPostsByTag(String tag);
    String getStatus();
}
