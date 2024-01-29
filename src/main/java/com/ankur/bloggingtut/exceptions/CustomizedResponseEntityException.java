package com.ankur.bloggingtut.exceptions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ankur.bloggingtut.payloads.CustomApiResponse;


@RestControllerAdvice
public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomApiResponse> handleAllException(Exception ex, WebRequest request) throws Exception {
		
		CustomApiResponse CustomApiResponse = new CustomApiResponse(LocalDateTime.now(), Arrays.asList(ex.getMessage()), false);
		return new ResponseEntity<CustomApiResponse>(CustomApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<CustomApiResponse> handleResourceNotFoundException(Exception ex, WebRequest req) throws Exception {
		
		CustomApiResponse CustomApiResponse = new CustomApiResponse(LocalDateTime.now(), Arrays.asList(ex.getMessage()), false);
		return new ResponseEntity<CustomApiResponse> (CustomApiResponse, HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		CustomApiResponse CustomApiResponse = new CustomApiResponse(LocalDateTime.now(), ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()), false);
		return new ResponseEntity<>(CustomApiResponse, HttpStatus.BAD_REQUEST);
	
	}

	
}
