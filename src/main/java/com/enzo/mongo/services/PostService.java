package com.enzo.mongo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enzo.mongo.domain.Post;
import com.enzo.mongo.repositories.PostRepository;
import com.enzo.mongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		Integer dayToMilliseconds = 24 * 60 * 60 * 1000;
		maxDate = new Date(maxDate.getTime() + dayToMilliseconds);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
