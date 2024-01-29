package com.ankur.bloggingtut.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.bloggingtut.payloads.CategoryDto;
import com.ankur.bloggingtut.payloads.CustomApiResponse;
import com.ankur.bloggingtut.services.impl.CategoryServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {
	
	private CategoryServiceImp service;
	
	public CategoryResource(CategoryServiceImp service) {
		super();
		this.service = service;
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
		CategoryDto category = service.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto> (category, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		List<CategoryDto> categories = service.getAllCategory();
		return new ResponseEntity<List<CategoryDto>> (categories, HttpStatus.OK);
	}


	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto category_details) {
		CategoryDto category = service.addCategory(category_details);
		return new ResponseEntity<CategoryDto> (category, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<CustomApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		service.deleteCategoryById(categoryId);
		return new ResponseEntity<CustomApiResponse> (new CustomApiResponse(LocalDateTime.now(), Arrays.asList("Resource deleted"), true), HttpStatus.OK);	
	}
}
