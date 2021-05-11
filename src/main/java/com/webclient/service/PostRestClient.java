package com.webclient.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.webclient.model.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostRestClient {
	
	private final String END_POINT = "https://jsonplaceholder.typicode.com/posts";
	private final WebClient webClient = WebClient.create(END_POINT);

	public List<Post> getAll(){
		return this.webClient.get().uri(this.END_POINT)
				.retrieve()
				.bodyToFlux(Post.class)
				.collectList()
				.block();
	}
	
	public Post getById(Long id) {
		try {
		return this.webClient.get().uri(this.END_POINT+"/{id}",id)
				.retrieve()
				.bodyToMono(Post.class)
				.block();
		}catch(WebClientException e) {
			log.error("Error to retrive post by Id: " + e.getMostSpecificCause().getMessage());
			throw e;
		}
	}
	

	public List<Post> findByName(String name){
		return null;
	}
	
	public Post save(Post post) {
		try {
		return this.webClient.post().uri(this.END_POINT)
			.bodyValue(post)
			.retrieve()
			.bodyToMono(Post.class)
			.block();
		}catch(WebClientException e) {
			log.error("Error savig post: " + e.getMostSpecificCause().getMessage());
			throw e;
		}
	}
	
	
	public Post update(Long id, Post post) {
		try {
			return this.webClient.put().uri(this.END_POINT+"/{id}",id)
				.bodyValue(post)
				.retrieve()
				.bodyToMono(Post.class)
				.block();
		}catch(WebClientException e) {
			log.error("Error updating: " + e.getMostSpecificCause().getMessage());
			throw e;
		}
	}
	
	public void delete(Long id) {
		try {
			 this.webClient.delete().uri(this.END_POINT+"/{id}",id)
				.retrieve()
				.bodyToMono(Post.class)
				.block();
		}catch(WebClientException e) {
			log.error("Error updating: " + e.getMostSpecificCause().getMessage());
			throw e;
		}
	}
	
}
