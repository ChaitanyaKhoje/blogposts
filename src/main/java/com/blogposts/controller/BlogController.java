package com.blogposts.controller;

import com.blogposts.entity.Posts;
import com.blogposts.exception.ApiRequestException;
import com.blogposts.service.BlogService;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/")
public class BlogController {

    @Autowired
    private BlogService blogService;

    private final static String INVALID_PARAMETER = "parameter is invalid";

    /**
     * ROUTE for /api/posts
     * Gets all posts matching to the specified tag(s)
     * @param tags (required) The tags parameter is required in the terms of the project's requirement, but is passed in as an optional argument for the sake of exception handling.
     * @param sortBy (optional)
     * @param direction (optional)
     * @return Posts
     * @throws ExecutionException : Throws custom exception when tags is null
     */
    @RequestMapping(value = "api/posts", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody
    Posts getAllPostsByTag(@RequestParam(value = "tags", required = false) List<String> tags,
                           @RequestParam(value = "sortBy", required = false) String sortBy,
                           @RequestParam(value = "direction", required = false) String direction) throws ExecutionException {
        if (tags == null || tags.isEmpty()) {
            throw new ApiRequestException("Tags parameter is required");
        }
        List<String> blogpostCompareTypes = ImmutableList.of("id", "likes", "reads", "popularity");
        List<String> directions = ImmutableList.of("asc", "desc");
        if (sortBy != null) {
            if (!blogpostCompareTypes.contains(sortBy)) {
                handleException("sortBy " + INVALID_PARAMETER);
            }
        }
        if (direction != null) {
            if (!directions.contains(direction)) {
                handleException("direction " + INVALID_PARAMETER);
            }
        }
        return blogService.getAllPostsByTag(tags, sortBy, direction);
    }

    /**
     * ROUTE for /api/ping
     * Checks if the server is available
     * @return
     */
    @RequestMapping(value = "api/ping", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody
    String getStatus() {
        return blogService.getStatus();
    }

    /**
     * Mapping incorrect URL
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody String getError() {
        return blogService.getError();
    }

    /**
     * Helper for handling the exceptions
     * @param message
     */
    private void handleException(String message) {
        throw new ApiRequestException(message);
    }
}
