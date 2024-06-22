package com.oms.Exception;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class OurOwnException {

	//private static final Logger logger = LoggerFactory.getLogger(OurOwnException.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> ArgumentNotValid(MethodArgumentNotValidException ex){
		log.error("Validation failed for an Arguement : "+ex.getMessage());
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> MethodNotSupported(HttpRequestMethodNotSupportedException ex){
		log.error("Given Method Not Supported : "+ex.getMessage());
		return new ResponseEntity<String>("Given Method Not Supported : "+ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<String> Query(SQLException ex){
		log.error("Sql Exception "+ex.getMessage());
		return new ResponseEntity<String>("SQL Exception : "+ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> EmptyData(NoSuchElementException ex){
		log.error(ex.getMessage());
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> EmptyData(EmptyResultDataAccessException ex){
		log.error("No data found : "+ex.getMessage());
		return new ResponseEntity<String>("No data found in database : "+ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}