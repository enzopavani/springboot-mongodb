package com.enzo.mongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.enzo.mongo.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User enzo = new User("1", "Enzo", "enzo@mail.br");
		User lara = new User("2", "Lara", "lara@mail.br");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(enzo, lara));
		return ResponseEntity.ok().body(list);
	}
}
