package com.juaracoding.carijodoh.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.carijodoh.entity.DataUser;
import com.juaracoding.carijodoh.repository.DataUserRepository;

@RestController
@RequestMapping("/user")
public class DataUserController {
	@Autowired
	DataUserRepository dataRepo;
	
	@GetMapping("/")
	public List<DataUser> getAll() {
		return dataRepo.findAll();
	}

	@GetMapping("/login/")
	public DataUser loginUser(@RequestParam("name")String name, @RequestParam("phone") String phone) {
		return dataRepo.findByLogin(name, phone);
	}


	@GetMapping("/searchby/{type}/{value}")
	public List<DataUser> getSearchBy(@PathVariable("type")String type, @PathVariable("value") String value) {
		return dataRepo.findBySearchBy(type, value);
	}

	@GetMapping("/name/{value}")
	public DataUser getByName(@PathVariable("value") String value) {
		return dataRepo.findByName(value);
	}

	@PostMapping("/register/")
	public String addUser(@RequestBody DataUser user) {
		dataRepo.save(user);
		return "Insert Berhasil";
	}
	
	@PostMapping("/register/{id}")
	public String updateUser(@PathVariable String id, @RequestBody DataUser user) {
		user.setId(Long.parseLong(id));
		dataRepo.save(user);
		return "Update Berhasil";
	}
	
	@GetMapping(value = "/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable String name) throws IOException {
	   final InputStream in = getClass().getResourceAsStream("/user-photo/"+name);
	   return IOUtils.toByteArray(in);
	}
}
