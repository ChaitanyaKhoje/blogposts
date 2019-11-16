package com.blogposts.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Blogpost {

    @JsonProperty("author")
    private String author;
    @JsonProperty("authorId")
    private int authorId;
    @JsonProperty("id")
    private int id;
    @JsonProperty("likes")
    private int likes;
    @JsonProperty("popularity")
    private float popularity;
    @JsonProperty("reads")
    private long reads;
    @JsonProperty("tags")
    private List<String> tags;

    public Blogpost() { }

    public Blogpost(String author, int authorId, int id, int likes, float popularity, long reads, List<String> tags) {
        this.author = author;
        this.authorId = authorId;
        this.id = id;
        this.likes = likes;
        this.popularity = popularity;
        this.reads = reads;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public long getReads() {
        return reads;
    }

    public void setReads(long reads) {
        this.reads = reads;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Blogpost{" +
                "author='" + author + '\'' +
                ", authorId=" + authorId +
                ", likes=" + likes +
                ", popularity=" + popularity +
                ", reads=" + reads +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blogpost blogpost = (Blogpost) o;
        return authorId == blogpost.authorId &&
                id == blogpost.id &&
                likes == blogpost.likes &&
                Float.compare(blogpost.popularity, popularity) == 0 &&
                reads == blogpost.reads &&
                Objects.equals(author, blogpost.author) &&
                Objects.equals(tags, blogpost.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, authorId, id, likes, popularity, reads, tags);
    }
}
