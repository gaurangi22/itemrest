package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.ItemDao;
import com.model.Item;

//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponses;
//import io.swagger.annotations.ApiResponse;

@RestController
public class ItemRestController {

	@Autowired
	ItemDao dao;
	
	@GetMapping("/homeinfo")
	public String gethomeinfo() {
		return "home for itemrestcontroller! your api is working fine";
	}
	
	@PostMapping("/additem")
	//@ApiOperation(value="get message", notes="api message displayed", nickname="msg")
	//@ApiResponses(value= {@ApiResponse(code=200,message="object processed")})
	public String addItem(@RequestBody Item item) {
		dao.save(item);
		return "item added";
	}
	
//	@PostMapping("/additem")
//	public ResponseEntity addItem(@RequestBody Item item) {
//		dao.save(item);
//		return new ResponseEntity("item added", HttpStatus.OK);
//	}
	
	@GetMapping("/getallitem")
	public List<Item> getallitem(){
		return dao.findAll();
	}
	
	@PatchMapping("/updateitem")
	public ResponseEntity updateitem(@RequestBody Item item) {
		dao.saveAndFlush(item);
		return new ResponseEntity("item updated", HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteitem")
	public ResponseEntity deleteitem(@RequestBody Item item) {
		dao.delete(item);
		return new ResponseEntity("item deleted", HttpStatus.OK);
	}
	
	@GetMapping("/findbyid/{iid}")
	public Item finditem(@PathVariable int iid) {
		//Item item=dao.getById(id);
		//return item;
		return dao.findById(iid).get();
	}
	
}
