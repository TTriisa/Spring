package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;



@Controller
public class PostHTMLController {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
			
	@GetMapping("/viewposts")
	public String getPosts(Model model){
		List<Posts> postslist = namedParameterJdbcTemplate.query(
				"Select * from posts",
				BeanPropertyRowMapper.newInstance(Posts.class));
		model.addAttribute("posts",postslist);
		return "posts";
	}
	
	@GetMapping("/viewposts/{user}")
	public String getPostsByName(@PathVariable String user, Model model){
		List<Posts> postslist = namedParameterJdbcTemplate.query(
				"Select * from posts where user= :user",
				new MapSqlParameterSource().addValue("user",user),
				BeanPropertyRowMapper.newInstance(Posts.class));
		model.addAttribute("posts",postslist);
		return "posts";
	}
	
	@GetMapping("/viewpost/{id}")
	public String getPostById(@PathVariable String id, Model model){
		List<Posts> postslist = namedParameterJdbcTemplate.query(
				"Select * from posts where id= :id",
				new MapSqlParameterSource().addValue("id",id),
				BeanPropertyRowMapper.newInstance(Posts.class));
		model.addAttribute("post",postslist.get(0));
		return "postView";
	}
	
	@GetMapping("/addpost")
	public String greetingForm(Model model) {
		model.addAttribute("post", new Posts());
		return "create";
	}

	@PostMapping("/addpost")
	public String postSubmit(@ModelAttribute Posts post) {
	
		String query = "INSERT INTO posts (user, headline, post) VALUES (:postUser,:postHeadline,:postPost)";
		
		Map namedParameters = new HashMap();
		namedParameters.put("postUser", post.getUser());
		namedParameters.put("postHeadline",post.getHeadline());
		namedParameters.put("postPost", post.getPost());
		
		namedParameterJdbcTemplate.update(query, namedParameters);
		return "redirect:/viewposts";
	}
	
}
