package com.webclient.service;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.webclient.model.Post;

@SpringBootTest
public class PostRestClientTest {
	
	@Autowired
	private PostRestClient postRestClient;
	@MockBean
	private PostRestClient mockPostRestClient;
	
	
	@Test
	@DisplayName("Find all Post")
	public void getAll() {
		List<Post> posts = this.postRestClient.getAll();
		assertTrue(!posts.isEmpty());
	}
	
	@Test
	@DisplayName("Get Post by Id")
	public void getById() {
		Post post = this.postRestClient.getById(1L);
		assertTrue(post!=null);
	}
	
	@Test
	@DisplayName("Save Post")
	public void save() {
		Post post = new Post();
		assertTrue(this.postRestClient.save(post)!=null);
	}
	
	@Test
	@DisplayName("Update Post")
	public void update() {
		Post post = this.postRestClient.getById(1L);
		post.setBody("Body test");
		assertTrue(this.postRestClient.update(1L, post).getBody().equals("Body test"));
	}
	
	@Test
	@DisplayName("Delete Post")
	public void delete() {
		((PostRestClient) verify(this.mockPostRestClient, times(1))).delete(1L);
	}
}
