package com.ankur.bloggingtut.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ankur.bloggingtut.entities.Category;
import com.ankur.bloggingtut.exceptions.ResourceNotFoundException;
import com.ankur.bloggingtut.payloads.CategoryDto;
import com.ankur.bloggingtut.repositories.CategoryRepository;
import com.ankur.bloggingtut.services.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {

	private CategoryRepository categoryDao;
	private ModelMapper modelMapper;
	
	
	public CategoryServiceImp(CategoryRepository categoryDao, ModelMapper modelMapper) {
		super();
		this.categoryDao = categoryDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		
		Category category  = categoryDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", id));
		return modelMapper.map(category, CategoryDto.class);
	
	}

	@Override
	public CategoryDto addCategory(CategoryDto category_details) {
		
		Category category = modelMapper.map(category_details, Category.class);
		return modelMapper.map(categoryDao.save(category), CategoryDto.class);
		
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> categories = categoryDao.findAll();
		return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
	}

	@Override
	public void deleteCategoryById(Integer id) {
		
		Category category  = categoryDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", id));
		categoryDao.delete(category);
	}

}
