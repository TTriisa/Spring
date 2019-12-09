package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PostsController {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
			
	@GetMapping("/allposts")
	public List<Posts> getPosts(){
		List<Posts> postslist = namedParameterJdbcTemplate.query(
				"Select * from posts",
				BeanPropertyRowMapper.newInstance(Posts.class));
		return postslist;
	}
	
	
	
}
