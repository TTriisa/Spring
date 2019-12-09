package com.example.demo;

import lombok.Data;

@Data
public class Posts {
	private int id;
	private String user;
	private String headline;
	private String post;
		
	public String getUser()  {
		return user;
	}
	
	public String getHeadline()  {
		return headline;
	}
	
	public String getPost()  {
		return post;
	}
}

