package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.document.Test;
import com.repository.TestRepository;

@CrossOrigin
@EnableWebMvc
@RestController
@RequestMapping(value = "/api/test-service", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {
	@Autowired
	private TestRepository tr;

	@PostMapping("/createTest")
	public Test saveTest(@RequestBody Test test) {

		return tr.save(test);
	}

	@PutMapping("/updateTest")
	public Test updateTest(@RequestBody Test test) {
		return tr.save(test);
	}

	@DeleteMapping("/deleteTest/{id}")
	public Map<Boolean, String> deleteTest(@PathVariable String id) {
		Map<Boolean, String> result = new HashMap<>();
		try {
			boolean found = tr.existsById(id);
			if (found) {
				tr.deleteById(id);
				result.put(true, "Cancellazione Test avvenuta con successo");
			} else {

				result.put(false, "Cancellazione Test fallita: Test non presente sul DB");
			}

		} catch (IllegalArgumentException iae) {
			result.put(false, "Cancellazione Test fallita: id e' null");
			iae.printStackTrace();
		}
		return result;
	}

	@GetMapping("/readAllTests")
	public List<Test> getAllTests() {
		return tr.findAll();
	}

}
