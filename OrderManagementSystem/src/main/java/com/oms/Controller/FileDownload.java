package com.oms.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oms.Entity.ConfigProperties;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("download")
@Slf4j
public class FileDownload {

	@Value("${app.download.location}")
	private String d;
	
	@Autowired
	private ConfigProperties con;
	
	@GetMapping("fileconf/{filename}")
	public ResponseEntity<Resource> fileDownload(@PathVariable String filename){
		String foldername = d;
		String filepath = foldername+filename;
		
		FileSystemResource fsr = new FileSystemResource(filepath);
		
		HttpHeaders hh = new HttpHeaders();
		hh.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		hh.setContentDispositionFormData("attachment", filename);
		
		log.info("config properties   "+con.toString());
		
		return new ResponseEntity<Resource>(fsr, hh, HttpStatus.OK);
	}
}
