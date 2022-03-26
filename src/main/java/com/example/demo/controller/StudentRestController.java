/**
 * 
 */
package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.StudentRepository;
import com.example.demo.mapper.StrudentMapper;
import com.example.demo.vo.Student;

/**
 * @author soumy
 *
 */
@RestController
@RequestMapping("/student")
public class StudentRestController {
	//@Autowired
	//private StrudentMapper mapper;

	@Autowired
	private StudentRepository repository;

	@GetMapping
	private ResponseEntity<List<com.example.demo.entity.Student>> getAllStudents() {
		ResponseEntity<List<com.example.demo.entity.Student>> response = null;

		List<com.example.demo.entity.Student> result = repository.findAll();
		
		if (CollectionUtils.isEmpty(result)) {
			response = ResponseEntity.notFound().build();
		} else {
			
			//List<Student> res = result.stream().map(mapper::toVo).collect(Collectors.toList());
			response = ResponseEntity.ok(result);
		}
		return response;
	}

	@GetMapping("/{studentId}")
	private ResponseEntity<com.example.demo.entity.Student> getStudentById(@PathVariable Integer studentId) {
		ResponseEntity<com.example.demo.entity.Student> response = null;
		if (0 > studentId) {
			response = ResponseEntity.badRequest().build();
		} else {
			Optional<com.example.demo.entity.Student> result = repository.findById(studentId);
			if(result.isPresent()) {
				
				response = ResponseEntity.ok(result.get());
			} else {
				response = ResponseEntity.notFound().build();
			}		
		}
		return response;
	}

	@PostMapping
	private ResponseEntity<com.example.demo.entity.Student> addStudent(@RequestBody com.example.demo.entity.Student student) {
		ResponseEntity<com.example.demo.entity.Student> response = null;
		if(null != student) {
			
			// check if student is existing
			
			Optional<com.example.demo.entity.Student> result = repository.findById(student.getId());
			if (result.isPresent()) {
				response = ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
			} else {
				com.example.demo.entity.Student studentNew = repository.save(student);
				response = ResponseEntity.status(HttpStatus.CREATED).body(studentNew);
			}
		} else {
			response = ResponseEntity.badRequest().build();
		}
		return response;
	}

	@DeleteMapping("/{studentId}")
	private void deleteStudent(@PathVariable String studentId) {
		// TODO Auto-generated method stub

	}

	@PutMapping("/{studentId}")
	private void updateStudent(@PathVariable String studentId) {
		// TODO Auto-generated method stub

	}
}
