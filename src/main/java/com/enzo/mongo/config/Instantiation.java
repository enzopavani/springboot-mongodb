package com.enzo.mongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.enzo.mongo.domain.Post;
import com.enzo.mongo.domain.User;
import com.enzo.mongo.dto.AuthorDTO;
import com.enzo.mongo.dto.CommentDTO;
import com.enzo.mongo.repositories.PostRepository;
import com.enzo.mongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("03/05/2025"), "Lady Gaga concert", "Going to the beach in Rio de Janeiro with over 2 million people.", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("08/05/2025"), "SOAD concert", "Not even a week since the last concert, my throat is sore. ;-;", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Have a nice trip!", sdf.parse("03/05/2025"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Hope you enjoy it!", sdf.parse("04/05/2025"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("I bet it was amazing", sdf.parse("10/05/2025"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
	
}
