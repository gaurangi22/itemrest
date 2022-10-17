package com.itemrestapp.itemrestapp;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dao.ItemDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Item;

@SpringBootTest
class ItemTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Autowired 
	ItemDao itemdao;
	@Test
	void test() {
		//fail("Not yet implemented");
		Item i=new Item();
		i.setItemName("coffee");
		i.setPrice(30);
		i.setQuantity(20);
		itemdao.save(i);
		Item i2=itemdao.findById(i.getItemId()).get();
		assertEquals(i.getItemName(),i2.getItemName());
	}
	
//	@Test
//	void countbyname() {
//		int count=itemdao.countByItemName("coffee");
//		assertEquals(count,1);
//	}
	@Test
	void test1() throws URISyntaxException, JsonProcessingException {
		  RestTemplate template=new RestTemplate();
	        final String url="http://localhost:8182/findbyid/1";
	        URI uri=new URI(url);
	        
	        ResponseEntity<String> res=template.getForEntity(uri,String.class);
	        Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
	}
	@Test
	void test2() throws URISyntaxException, JsonProcessingException {
		  RestTemplate template=new RestTemplate();
	        final String url="http://localhost:8182/getallitem";
	        URI uri=new URI(url);
	        
	        ResponseEntity<String> res=template.getForEntity(uri,String.class);
	        Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
	}
	@Test
    void testAddRest() throws URISyntaxException, JsonProcessingException {
      RestTemplate template=new RestTemplate();
      final String url="http://localhost:8182/additem";
      Item i=new Item();
      i.setItemName("tea");
		i.setPrice(30);
		i.setQuantity(20);
      URI uri=new URI(url);
      HttpHeaders headers = new HttpHeaders();      
      HttpEntity<Item> request = new HttpEntity<>(i, headers);
      ResponseEntity<String> res=template.postForEntity(uri,request,String.class);
      Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
      
  }
}
