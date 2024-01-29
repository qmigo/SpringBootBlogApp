package com.ankur.bloggingtut.services;

import java.util.List;

import com.ankur.bloggingtut.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto getCategoryById(Integer id);
	CategoryDto addCategory(CategoryDto category);
	List<CategoryDto> getAllCategory();
	void deleteCategoryById(Integer id);
	
}
