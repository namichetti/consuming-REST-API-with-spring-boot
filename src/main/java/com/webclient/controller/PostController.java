package com.webclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webclient.model.Post;
import com.webclient.service.PostRestClient;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostRestClient userRestClient;
	
	@GetMapping("/")
	public List<Post> getAll(){
		return this.userRestClient.getAll();
	}
	
	@GetMapping("/{id}")
	public Post getById(@PathVariable Long id){
		return this.userRestClient.getById(id);
	}
	
	@PostMapping("/")
	public Post save(@RequestBody Post post){
		return this.userRestClient.save(post);
	}
	
	@PutMapping("/{id}")
	public Post update(@PathVariable Long id, @RequestBody Post post){
		Post recoveredPost = this.userRestClient.getById(id); 
		recoveredPost.setTitle(post.getTitle());
		recoveredPost.setBody(post.getBody());
		return this.userRestClient.save(recoveredPost);
	}
	
	@DeleteMapping("/{id}")
	public void save(@PathVariable Long id){
		 this.userRestClient.delete(id);
	}
	
}
