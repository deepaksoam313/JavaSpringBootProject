package com.deepak.JobListing.repository;

import com.deepak.JobListing.model.Post;

import java.util.List;

public interface SearchPostRepository {
    List<Post> findByText(String text);
}
